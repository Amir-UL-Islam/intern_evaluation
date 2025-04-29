package org.spring.intro.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MUserDTO {
    @JsonProperty(value = "name", required = true, defaultValue = "AUser")
    private String name;

    @JsonProperty(value = "phone_number", required = true, defaultValue = "017066")
    private String phoneNumber;
    @NonNull
    private String username;
    @NonNull
    @Hidden
    private String password;
    @JsonProperty(namespace = "email", required = true, defaultValue = "example@gmail.com")
    private @Email String email;
}
