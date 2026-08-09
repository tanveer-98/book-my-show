package com.tanveer.bookmyshow.Repository;


import com.tanveer.bookmyshow.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User>{

    Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);
}
