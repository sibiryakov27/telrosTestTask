package com.telros.controller;

import com.telros.data.dto.UserContactInfo;
import com.telros.data.dto.UserDTO;
import com.telros.data.dto.UserDetailedInformationRequest;
import com.telros.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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

    @GetMapping("/user/{id}/image")
    public @ResponseBody ResponseEntity<byte[]> getUserImage(@PathVariable Integer id) {
        byte[] image = userService.getUserImage(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
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

    @DeleteMapping("/user/{id}/image")
    public ResponseEntity<byte[]> deleteUserImage(@PathVariable Integer id) {
        byte[] image = userService.deleteImage(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
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

    @PutMapping(value = "/user/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateUserImage(
            @PathVariable Integer id,
            @RequestParam MultipartFile file
    ) {
        byte[] image = userService.updateUserImage(id, file);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
}
