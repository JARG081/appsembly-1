package appsembly.appsembly.services.models.dtos;

import lombok.Data;

@Data
public class UserDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private String personalCode;
}
