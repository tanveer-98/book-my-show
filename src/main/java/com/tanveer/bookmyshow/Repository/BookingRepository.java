package com.tanveer.bookmyshow.Repository;

import com.tanveer.bookmyshow.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends BaseRepository<Booking> {
}
