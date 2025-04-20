package org.spring.intro;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class MUserDTO {
    @JsonProperty(value = "name", required = true, defaultValue = "AUser")
    private String name;
    @JsonProperty(value = "phone_number", required = true, defaultValue = "017066")
    private @Email String phoneNumber;
    @JsonProperty(namespace = "email", required = true, defaultValue = "example@gmail.com")
    private String email;
}
