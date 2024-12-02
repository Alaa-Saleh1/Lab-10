package com.example.lab10.Service;

import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id, User user) {
        User oldUser = userRepository.getById(id);
        if (oldUser == null) {
            return false;
        }
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());
        userRepository.save(oldUser);
        return true;
    }
    public Boolean deleteUser(Integer id) {
        User oldUser = userRepository.getById(id);
        if (oldUser == null) {
            return false;
        }
        userRepository.delete(oldUser);
        return true;
    }
}
