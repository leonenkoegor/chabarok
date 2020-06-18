package codes.purple.chabarok.services;

import codes.purple.chabarok.dtos.UserDTO;
import codes.purple.chabarok.models.User;
import codes.purple.chabarok.repositories.UserRepository;
import codes.purple.chabarok.services.exceptions.ShortPasswordException;
import codes.purple.chabarok.services.exceptions.ShortUsernameException;
import codes.purple.chabarok.services.exceptions.UserAlreadyExistsException;
import codes.purple.chabarok.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UserNotFoundException();
        return user.get();
    }

    public void create(UserDTO userDTO) throws ShortUsernameException, ShortPasswordException, UserAlreadyExistsException {
        if (userDTO.getUsername().length() < 6) {
            throw new ShortUsernameException();
        }
        if (userDTO.getPassword().length() < 8) {
            throw new ShortPasswordException();
        }

        try {
            findByUsername(userDTO.getUsername());
            throw new UserAlreadyExistsException();
        } catch (UserNotFoundException e) {
            userRepository.save(new User(userDTO));
        }
    }

}
