package com.telros.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailedInformationRequest {
    @NotNull
    private String secondName;
    @NotNull
    private String firstName;
    @NotNull
    private String patronymic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date birthDate;
}
