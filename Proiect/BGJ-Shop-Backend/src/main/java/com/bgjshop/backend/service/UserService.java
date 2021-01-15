package com.bgjshop.backend.service;

import com.bgjshop.backend.domain.User;
import com.bgjshop.backend.dto.UserDto;
import com.bgjshop.backend.dto.UserLoginDto;
import com.bgjshop.backend.exceptions.EntityNotFoundException;
import com.bgjshop.backend.mapper.UserMapper;
import com.bgjshop.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.UUID;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Autowired
    private Environment env;
    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDto get(String id) {
        return userRepository.get(id).orElseThrow(()-> new EntityNotFoundException("User"));
    }

    @Transactional(rollbackFor = Exception.class)
    public void register(UserLoginDto request) {
        User user = userMapper.toEntity(request);
        user.setRoleType(1);

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        user.setPasswordSalt(salt);
        KeySpec spec = new PBEKeySpec(request.getPassword().toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            user.setPasswordHash(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        userRepository.register(user);
    }

    public UserDto login(UserLoginDto request) {
        UserDto user = userRepository.login(request).orElseThrow(()-> new EntityNotFoundException("User"));
        KeySpec spec = new PBEKeySpec(request.getPassword().toCharArray(), user.getPasswordSalt(), 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            if(Arrays.equals(hash, user.getPasswordHash()))
                return user;
            else return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDto loginAdmin(UserLoginDto request) {
        UserDto user = new UserDto(UUID.randomUUID(), request.getEmail(), "Admin", null, null, null, null, null, 1, null, null);

        if(request.getEmail().equals(env.getProperty("dashboard.admin.username")) &&
                request.getPassword().equals(env.getProperty("dashboard.admin.password")))
            return user;
        else return null;
    }

    public void update(UserDto request) {
        User user = userMapper.toEntity(request);
        userRepository.update(user);
    }

    public void delete(UUID id) {
        userRepository.delete(id);
    }
}
