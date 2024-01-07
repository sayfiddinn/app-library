package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.payload.ResultMessage;
import com.example.demo.payload.SignDTO;
import com.example.demo.payload.SignUpDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.entity.enums.RoleTypeEnum;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.filter.JwtProvider;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;

    @Override
    public ResultMessage signUp(SignUpDTO signUpDTO) {
        if (userRepository.existsByEmail(signUpDTO.getEmail())) {
            return new ResultMessage(false,
                    "User exist with email " + signUpDTO.getEmail());
        }
        User user = getUserFromDTO(new User(), signUpDTO);
        Role role = roleRepository.findByRoleType(RoleTypeEnum.USER)
                .orElseThrow(() -> new ConflictException("Some wrong"));
        user.setRole(role);
        userRepository.save(user);
        return new ResultMessage(true, "User saved successfully");
    }


    private User getUserFromDTO(User user, SignUpDTO signUpDTO) {
        String password = signUpDTO.getPassword();
        if (password.isBlank() || !Objects.equals(password, signUpDTO.getRePassword())) {
            throw new BadRequestException("Re password wrong");
        }
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(signUpDTO.getFirstName());
        user.setLastName(signUpDTO.getLastName());
        return user;
    }

    @Override
    public ResultMessage signIn(SignDTO signDTO) {
        User user = userRepository.findByEmail(signDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("This email not found"));
        if (user.isDeleted()) throw new NotFoundException("This user not found");
        if (!passwordEncoder.matches(signDTO.getPassword(), user.getPassword())) {
            return new ResultMessage(false, "Passwords are not equals");
        }
        String token = jwtProvider.generateJWT(user.getEmail());
        return new ResultMessage(true, Utils.BEARER + token);
    }

}
