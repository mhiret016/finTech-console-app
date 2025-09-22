package org.example.FinTech.services;

import org.example.FinTech.entities.User;
import org.example.FinTech.repository.UserRepositoryDB;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepositoryDB userRepository;

    public UserService() {
        this.userRepository = new UserRepositoryDB();
    }

    public void createUser(String name, String email, double balance) {
        User user = new User(0, name, email, balance);
        userRepository.addUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }
}
