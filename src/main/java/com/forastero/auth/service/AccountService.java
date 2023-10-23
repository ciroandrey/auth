package com.forastero.auth.service;

import com.forastero.auth.config.jwt.JwtTokenUtil;
import com.forastero.auth.dto.CreateUserDTO;
import com.forastero.auth.dto.LoginDTO;
import com.forastero.auth.exception.UserAlreadyExistsException;
import com.forastero.auth.model.User;
import com.forastero.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public AccountService(UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder,
                          JwtTokenUtil jwtTokenUtil,
                          CustomUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    public User saveUser(CreateUserDTO newUser) {
        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setRoles(newUser.getRoles());
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User with this username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean doLogin(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getLogin());
        if (user != null) {
            return passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        }
        return false;
    }

    public String authenticate(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getLogin());
        if (user != null &&
                passwordEncoder.matches(loginDTO.getPassword(),
                        user.getPassword())) {
            return generateToken(loginDTO.getLogin());
        } else return "";
    }

    private String generateToken(String username){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

}
