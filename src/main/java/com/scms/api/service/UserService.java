package com.scms.api.service;

import com.scms.api.dto.request.CreateUserRequest;
import com.scms.api.dto.response.CreateUserResponse;
import com.scms.api.dto.response.GetUsersResponse;
import com.scms.api.entity.Role;
import com.scms.api.entity.User;
import com.scms.api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        User createdUser = userRepository.save(user);

        return new CreateUserResponse(
                createdUser.getId(),
                createdUser.getName(),
                createdUser.getEmail(),
                createdUser.getRole()
        );
    }

    public List<GetUsersResponse> getUsersByRole(Role role) {
        List<User> users = userRepository.findByRole(role);

        return users.stream()
                .map(user -> new GetUsersResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();
    }

    public List<GetUsersResponse> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new GetUsersResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
