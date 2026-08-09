package com.tanveer.bookmyshow.Service;

import com.tanveer.bookmyshow.Entity.User;
import com.tanveer.bookmyshow.Exception.UserNotFoundException;
import com.tanveer.bookmyshow.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    // inject repository

    @Autowired
    private UserRepository repository;

    @Override
    public User createUser(User user) {

        return repository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("User not found")
        );
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                ()->new EntityNotFoundException("User not found")
        );
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
       User user = repository.findById(id).orElseThrow(
               ()->new UserNotFoundException("User Not Found")
       );
       user.setFirstName(updatedUser.getFirstName());
       user.setLastName(updatedUser.getLastName());

       return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
