package com.example.bookbook.service;

import com.example.bookbook.user.User;
import com.example.bookbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private UserRepository userRepository;

    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {

        User userAlreadyExist = userRepository.findUserByEmail(user.getEmail());

        if (userAlreadyExist != null) {

            return null;
        } else {

            User newUser = new User(user.getName(), user.getBirthday(), user.getAddress(), user.getPhone(), user.getEmail());

            userRepository.save(newUser);

            return newUser;
        }
    }

    public User findUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {

            return null;
        } else {
            User user = optionalUser.get();

            return user;
        }
    }

    public User updateUser(long id, User newUserInformation) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            if (!newUserInformation.getName().contains(existingUser.getName()) && newUserInformation.getName() != null) {

                existingUser.setName(newUserInformation.getName());
            }
            if (!newUserInformation.getBirthday().contains(existingUser.getBirthday()) && newUserInformation.getBirthday() != null) {

                existingUser.setBirthday(newUserInformation.getBirthday());
            }
            if (!newUserInformation.getAddress().contains(existingUser.getAddress()) && newUserInformation.getAddress() != null) {

                existingUser.setAddress(newUserInformation.getAddress());
            }
            if (!newUserInformation.getPhone().contains(existingUser.getPhone()) && newUserInformation.getPhone() != null) {

                existingUser.setPhone(newUserInformation.getPhone());
            }
            if (!newUserInformation.getEmail().contains(existingUser.getEmail()) && newUserInformation.getEmail() != null) {

                existingUser.setEmail(newUserInformation.getEmail());
            }
            if (newUserInformation.hasActiveBookings()) {

                existingUser.setActiveBookings(true);
            } else {

                existingUser.setActiveBookings(false);
            }

            userRepository.save(existingUser);

            return existingUser;
        } else {

            return null;
        }
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public String deleteUser(long id) {
        User userToDelete = findUserById(id);

        if (userToDelete != null) {

            if (!userToDelete.hasActiveBookings()) {

                userRepository.delete(userToDelete);
            } else {

                return "User has active bookings and cant be deleted";
            }
        } else {

            return "Could not find User with provided ID";
        }

        return "User deleted";
    }

}
