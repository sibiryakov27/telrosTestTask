package com.telros.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserContactInfo {
    private Integer id;
    @Email
    private String email;
    @Pattern(regexp = "\\d{10}")
    private String phoneNumber;
}
