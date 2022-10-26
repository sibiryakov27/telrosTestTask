package com.telros.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String secondName;
    @NotNull
    private String firstName;
    private String patronymic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date birthDate;
    @Email
    private String email;
    @Pattern(regexp = "\\d{10}")
    private String phoneNumber;
}
