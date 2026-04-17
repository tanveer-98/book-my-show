package com.tanveer.bookmyshow.Repository;

import com.tanveer.bookmyshow.Entity.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowSeatRepository extends BaseRepository<ShowSeat> {
//    @Lock(LockModeType.PESSIMISTIC_WRITE) // if it's locked then the other cannot read or  // BAD APPROACH it locks the whole table if one user tries to book one seat
    List<ShowSeat> findByShowId(Long showId);

    // STEP 2 : lock the seat (ATOMIC AND CONCURRENCY SAFE)

    @Modifying
    @Query("""
        UPDATE ShowSeat s
        SET s.status = 'LOCKED',
            s.lockedAt = :now
        WHERE s.id IN :seatIds
        AND (
            s.status = 'AVAILABLE'
            OR (s.status = 'LOCKED' AND s.lockedAt < :expiryTime)
        )
    """)
    int lockSeats(
            @Param("seatIds") List<Long> seatIds,
            @Param("now") LocalDateTime now,
            @Param("expiryTime")LocalDateTime expiryTime
            );
    /// Note : int returns the updated count of the rows


    // Query to unlock the seats if

    @Modifying
    @Query("""
        UPDATE ShowSeat s 
        SET s.status = 'AVAILABLE'
        WHERE s.status = 'LOCKED' AND 
        s.lockedAt < :expiryTime
    """)
    int releaseExpiredSeats(@Param("expiryTime") LocalDateTime expiryTime);


    // 💳 STEP 4: Fetch seats for booking confirmation
    List<ShowSeat> findAllByIdIn(List<Long> seatIds);


    // 🔍 Optional: Get seats by status (useful for analytics / UI)
    List<ShowSeat> findByShowIdAndStatus(Long showId, ShowSeat.ShowSeatStatus status);


    // 🔍 Optional: Validate all seats belong to same show
    @Query("""
        SELECT COUNT(DISTINCT s.show.id)
        FROM ShowSeat s
        WHERE s.id IN :seatIds
    """)
    int countDistinctShowIds(@Param("seatIds") List<Long> seatIds);



}
