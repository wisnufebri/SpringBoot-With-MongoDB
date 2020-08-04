package com.gresstenan.wisnu.controller;

import com.gresstenan.wisnu.models.*;
import com.gresstenan.wisnu.payload.response.MessageResponse;
import com.gresstenan.wisnu.repository.RoleRepository;
import com.gresstenan.wisnu.repository.UserRepository;
import com.gresstenan.wisnu.service.UserDetailsServiceImpl;
import com.gresstenan.wisnu.utility.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping
    public List<User> getAll(@RequestParam(value = "page", defaultValue = "0") Integer pageNo,
                             @RequestParam(value = "sortKey", defaultValue = "username") String sortKey) {
        return userDetailsService.getAllUser(pageNo, sortKey);
    }

    @PostMapping("/insert")
    public ResponseEntity<MessageResponse> createUser(@RequestBody User user) {
        User user1 = new User(user.getUsername(),
                user.getEmail(),
                user.getUserDetail(),
                encoder.encode(user.getPassword()));

        Set<Role> strRoles = user.getRoles();
        Set<Role> roles = new HashSet<>();
        UserDetail usrdtl = new UserDetail();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else if ("mod".equals(role)) {
                    Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user1.setRoles(roles);
        user.setUserDetail(usrdtl);
        userRepository.save(user1);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

//    @PutMapping("/update")
//    public ResponseEntity<MessageResponse> updateUser(@RequestBody User user) {
//        User user1 = new User(user.getId(),
//                user.getEmail(),
//                user.getUserDetail(),
//                encoder.encode(user.getPassword()));
//
//        Set<Role> strRoles = user.getRoles();
//        Set<Role> roles = new HashSet<>();
//        UserDetail usrdtl = new UserDetail();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                if ("admin".equals(role)) {
//                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                    roles.add(adminRole);
//                } else if ("mod".equals(role)) {
//                    Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                    roles.add(modRole);
//                } else {
//                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                    roles.add(userRole);
//                }
//            });
//        }
//
//        user1.setRoles(roles);
//        user.setUserDetail(usrdtl);
//        userRepository.save(user1);
//
//        return ResponseEntity.ok(new MessageResponse("User update successfully!"));
//    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody User body) {
        Map<String, Object> result = new HashMap<>();
        if (userDetailsService.updateUser(body)) {
            result.put("Status", true);
            result.put("Massage", "Berhasil Update data");
        } else {
            result.put("Status", false);
            result.put("Massage", "Gagal Update");
        }
        return result;
    }
}