package com.telros.controller;

import com.telros.data.dto.UserContactInfo;
import com.telros.data.dto.UserDTO;
import com.telros.data.dto.UserDetailedInformationRequest;
import com.telros.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
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
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/telros")
public class UserController {

    private UserService userService;

    @GetMapping("/user")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getUserImage(@PathVariable Integer id) {
        return userService.getUserImage(id);
    }

    @PostMapping("/user")
    public UserDTO addNewUser(@Valid @RequestBody UserDTO user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/user/{id}")
    public UserDTO deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @DeleteMapping(value = "/user/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] deleteUserImage(@PathVariable Integer id) {
        return userService.deleteImage(id);
    }

    @PutMapping("/user/detailed-info/{id}")
    public UserDTO updateUserInfo(
            @PathVariable Integer id,
            @Valid @RequestBody UserDetailedInformationRequest userInfo
            ) {
        return userService.updateUserInfo(id, userInfo);
    }

    @PutMapping("/user/contact-info/{id}")
    public UserDTO updateUserContacts(
            @PathVariable Integer id,
            @Valid @RequestBody UserContactInfo userContacts
    ) {
        return userService.updateUserContacts(id, userContacts);
    }

    @PutMapping(
            value = "/user/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public byte[] updateUserImage(
            @PathVariable Integer id,
            @RequestParam MultipartFile file
    ) {
        return userService.updateUserImage(id, file);
    }
}
