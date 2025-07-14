package br.com.ecommerce.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api.dto.UserRegisterDTO;
import br.com.ecommerce.api.model.User;
import br.com.ecommerce.api.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegisterDTO registerDTO){
        userRepository.findByEmail(registerDTO.getEmail()).ifPresent(user -> {
            throw new RuntimeException("Email already registered");
        });

        User newUser = new User();
        newUser.setName(registerDTO.getName());
        newUser.setEmail(registerDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        return userRepository.save(newUser);
    }
}
