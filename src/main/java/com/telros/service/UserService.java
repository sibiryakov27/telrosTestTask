package com.telros.service;

import com.telros.data.dto.UserContactInfo;
import com.telros.data.dto.UserDTO;
import com.telros.data.dto.UserDetailedInformationRequest;
import com.telros.data.entity.UserEntity;
import com.telros.exception.NotFoundException;
import com.telros.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO saveUser(UserDTO user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity = userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserDTO deleteUser(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new NotFoundException("User with such id cannot be found [id = " + id + "]");
        }
        userRepository.delete(userEntity.get());
        return modelMapper.map(userEntity.get(), UserDTO.class);
    }

    public UserDTO updateUserInfo(Integer id, UserDetailedInformationRequest userInfo) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new NotFoundException("User with such id cannot be found [id = " + id + "]");
        }
        UserEntity updatedUser = userEntity.get();
        updatedUser.setFirstName(userInfo.getFirstName())
                .setSecondName(userInfo.getSecondName())
                .setPatronymic(userInfo.getPatronymic())
                .setBirthDate(userInfo.getBirthDate());
        updatedUser = userRepository.save(updatedUser);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    public UserDTO updateUserContacts(Integer id, UserContactInfo userContacts) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new NotFoundException("User with such id cannot be found [id = " + id + "]");
        }
        UserEntity updatedUser = userEntity.get();
        updatedUser.setEmail(userContacts.getEmail())
                .setPhoneNumber(userContacts.getPhoneNumber());
        updatedUser = userRepository.save(updatedUser);
        return modelMapper.map(updatedUser, UserDTO.class);
    }
}
