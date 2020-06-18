package codes.purple.chabarok.models;

import codes.purple.chabarok.dtos.UserDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String firstName;
    private String secondName;
    private String lastName;
    private Integer phoneNumber;

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.enabled = userDTO.getEnabled();
        this.role = userDTO.getRole();
        this.firstName = userDTO.getFirstName();
        this.secondName = userDTO.getSecondName();
        this.lastName = userDTO.getLastName();
        this.phoneNumber = userDTO.getPhoneNumber();
    }
}
