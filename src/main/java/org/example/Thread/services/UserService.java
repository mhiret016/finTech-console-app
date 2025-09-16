package org.example.Thread.services;

import org.example.Thread.entities.User;
import org.example.Thread.repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepo = new UserRepository();

    public void createUser(int id, String name, String email, double balance){
        User user = new User(id, name, email, balance);
        userRepo.addUser(user);
        System.out.println("User created: " + user);
    }

    public User getUserById(int id){
        return userRepo.getUserById(id);
    }

    public List<User> getAllUsers(){
        return userRepo.getAllUsers();
    }
    public boolean updateUser(User updatedUser){
        return userRepo.updateUser(updatedUser);
    }

    public boolean deleteUser(int id){
        return userRepo.deleteUser(id);
    }
}

