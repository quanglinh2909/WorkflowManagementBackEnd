package com.example.workflowmanagementbackend.service;

import com.example.workflowmanagementbackend.dto.user.UserCreateDTO;
import com.example.workflowmanagementbackend.dto.user.UserLogin;
import com.example.workflowmanagementbackend.dto.user.UserUpdate;
import com.example.workflowmanagementbackend.exception.ExitsException;
import com.example.workflowmanagementbackend.model.AuthenticationResponse;
import com.example.workflowmanagementbackend.model.Response;
import com.example.workflowmanagementbackend.model.User;
import com.example.workflowmanagementbackend.repository.UserRepository;
import com.example.workflowmanagementbackend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AuthenticationResponse singUp(UserCreateDTO user) throws ExitsException {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ExitsException("Username is already taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ExitsException("Email is already taken");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);
        return jwtService.generateToken(newUser);
    }

    public AuthenticationResponse login(UserLogin request) throws ExitsException {
        String password = request.getPassword();
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            throw new ExitsException("User not found");
        }
        //so sanh password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ExitsException("Password is not correct");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), password));


        if (!user.isActivate()) {
            throw new ExitsException("User is not enabled");
        }

        return jwtService.generateToken(user);
    }

    public Response update(UserUpdate user) throws ExitsException {
        Optional<User> user1 = userRepository.findById(user.getId());
        if (user1.isEmpty()) {
            throw new ExitsException("User not found");
        }
        User user2 = user1.get();
        if (user.getUsername() != null) {
            user2.setUsername(user.getUsername());
        }
        if (user.getEmail() != null) {
            user2.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            user2.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user2);

        return new Response(200,"Update success",null);
    }

    public Response setActivate(Long id) throws ExitsException {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()) {
            throw new ExitsException("User not found");
        }
        User user2 = user1.get();
        user2.setActivate(true);
        userRepository.save(user2);
        return new Response(200,"Update success",null);
    }

    public Response setIdSocket(Long id, String idSocket) throws ExitsException {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()) {
            throw new ExitsException("User not found");
        }
        User user2 = user1.get();
        user2.setIdSocket(idSocket);
        userRepository.save(user2);
        return new Response(200,"Update success",null);
    }

    @Transactional
    public Response deleteIdSocketOnIdSocket(String idSocket)  {
        userRepository.setIdSocketNull(idSocket);
        return new Response(200,"Update success",null);
    }

    public User getById(Long id) throws ExitsException {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()) {
            throw new ExitsException("User not found");
        }
        return user1.get();
    }

    public User getByUsername(String username) throws ExitsException {
        User user1 = userRepository.findByUsername(username);
        if (user1 == null) {
            throw new ExitsException("User not found");
        }
        return user1;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


}
