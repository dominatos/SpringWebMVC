package com.example.SpringWebMVC.services;

import com.example.SpringWebMVC.model.User;
import com.example.SpringWebMVC.repository.UserDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAORepository db;

    public User checkLogin(String email, String password){
       return db.findUserByemailAndPassword(email, password);
    }
    public void registerUser(User user) {
        db.save(user);
    }
    public void findUserById(Long id){

    }
    public void editUser(User user){
        db.save(user);
    }
    public void deleteUser(User user){
        db.delete(user);
    }
    public List<User> getAllUsers(){

        return db.findAll();
    }
    public String userStamp(User user){
        return user.toString();
    }
    public User getUserById(Long id) {
        return db.findById(id)
                .orElseThrow(() -> new RuntimeException("User con ID " + id + " non e trovato."));
    }
}
