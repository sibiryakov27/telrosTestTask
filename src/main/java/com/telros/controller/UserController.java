package com.telros.controller;

import com.telros.data.dto.UserContactInfo;
import com.telros.data.dto.UserDTO;
import com.telros.data.dto.UserDetailedInformationRequest;
import com.telros.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/telros")
public class UserController {

    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> addNewUser(@Valid @RequestBody UserDTO user) {
        UserDTO addedUser = userService.saveUser(user);
        return ResponseEntity.ok(addedUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Integer id) {
        UserDTO deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }

    @PutMapping("/user/detailed-info/{id}")
    public ResponseEntity<UserDTO> updateUserInfo(
            @PathVariable Integer id,
            @Valid @RequestBody UserDetailedInformationRequest userInfo
            ) {
        UserDTO updatedUser = userService.updateUserInfo(id, userInfo);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/user/contact-info/{id}")
    public ResponseEntity<UserDTO> updateUserContacts(
            @PathVariable Integer id,
            @Valid @RequestBody UserContactInfo userContacts
    ) {
        UserDTO updatedUser = userService.updateUserContacts(id, userContacts);
        return ResponseEntity.ok(updatedUser);
    }
}
