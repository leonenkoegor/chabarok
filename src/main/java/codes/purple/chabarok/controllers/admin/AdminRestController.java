package codes.purple.chabarok.controllers.admin;

import codes.purple.chabarok.controllers.responses.DefaultResponse;
import codes.purple.chabarok.controllers.responses.Status;
import codes.purple.chabarok.dtos.UserDTO;
import codes.purple.chabarok.services.UserService;
import codes.purple.chabarok.services.exceptions.ShortPasswordException;
import codes.purple.chabarok.services.exceptions.ShortUsernameException;
import codes.purple.chabarok.services.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/admin/create/user")
    public DefaultResponse createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.create(userDTO);
        } catch (ShortUsernameException e) {
            return new DefaultResponse(Status.FAIL, "short username");
        } catch (ShortPasswordException e) {
            return new DefaultResponse(Status.FAIL, "short password");
        } catch (UserAlreadyExistsException e) {
            return new DefaultResponse(Status.FAIL, "user already exists");
        }

        return new DefaultResponse(Status.SUCCESS, "user created");
    }

}
