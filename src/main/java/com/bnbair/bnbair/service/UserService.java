package com.bnbair.bnbair.service;

import com.bnbair.bnbair.domain.User;
import com.bnbair.bnbair.exception.UserNotFoundException;
import com.bnbair.bnbair.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) throws UserNotFoundException {
        User user = getUserById(id);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());

//        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
//            user.setPassword(userDetails.getPassword());
//        }
        return userRepository.save(user);
    }


    public void deleteUser(Long id) throws UserNotFoundException {
        User user = getUserById(id);
        userRepository.delete(user);
    }
    public List<User> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByFirstAndLastName(String firstName, String lastName) {
        return userRepository.findDistinctByFirstNameAndLastName(firstName, lastName);
    }
}
