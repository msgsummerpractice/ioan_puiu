package com.example.DBdemo.Service;

import com.example.DBdemo.Repository.UserRepository;
import com.example.DBdemo.dto.UserRequest;
import com.example.DBdemo.dto.UserPatchRequest;
import com.example.DBdemo.dto.UserResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import com.example.DBdemo.Model.User;
import org.modelmapper.ModelMapper;
import com.example.DBdemo.Exception.UserNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        
    }

    public List<UserResponse> findAllUsers() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        List<User> users = userRepository.findAll();
        
        return users.stream().map(user -> {
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            return userResponse;
        }).toList();
    }


    

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        User user = userRepository.findById(id).orElseThrow(() 
        -> new UserNotFoundException("User not found"));

        modelMapper.map(userRequest, user);

        UserResponse userResponse = modelMapper.map(userRepository.save(user), UserResponse.class);
        return userResponse;
    }

    public Page<UserResponse> findUsersPaged(int page, int size) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.map(user -> modelMapper.map(user, UserResponse.class));
    }

    public UserResponse partialUpdateUser(Long id, UserPatchRequest userPatchRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        User user = userRepository.findById(id).orElseThrow(() 
        -> new UserNotFoundException("User not found"));

        modelMapper.map(userPatchRequest, user);

        UserResponse userResponse = modelMapper.map(userRepository.save(user), UserResponse.class);
        return userResponse;

    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public UserResponse createUser(UserRequest userRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        
        User user = modelMapper.map(userRequest, User.class);

        UserResponse userResponse = modelMapper.map(userRepository.save(user), UserResponse.class);
        return userResponse;
        
    }

    public User removeUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        this.removeUser(user);
        return user;
    }

    public User removeUser(User user) {
        if(!userRepository.findById(user.getId()).isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.delete(user);
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> searchUsersByUsername(String username) {
        return userRepository.findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(username);
    }

    public Integer countUsers() {
        return userRepository.countUsers();
    }

}
