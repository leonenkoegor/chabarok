package codes.purple.chabarok.dtos;

import codes.purple.chabarok.models.Role;
import lombok.Data;

@Data
public class UserDTO {

    private String username;
    private String password;
    private Boolean enabled;
    private Role role;
    private String firstName;
    private String secondName;
    private String lastName;
    private Long phoneNumber;

}
