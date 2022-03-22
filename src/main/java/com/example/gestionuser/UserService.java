package com.example.gestionuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }

   public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }


    public User addUser(User newUser) {
        userRepository.save(newUser);
        return newUser;
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public User getUserById(String id) throws Exception{
        Optional<User> possibleUser = userRepository.findById(Long.parseLong(id));
        if (possibleUser.isPresent()){
            return possibleUser.get();
        }
        throw new Exception("We don't have an user with that id");
    }

    public User updateUser(User updateUser,String id) throws Exception {
        User userWithId = getUserById(id);
        userWithId.setPassword(updateUser.getPassword());
        userWithId.setEmail(updateUser.getEmail());
        userWithId.setUsername(updateUser.getUsername());
        userWithId.setGender(updateUser.getGender());
        userRepository.save(userWithId);
        return userWithId;
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }
}
