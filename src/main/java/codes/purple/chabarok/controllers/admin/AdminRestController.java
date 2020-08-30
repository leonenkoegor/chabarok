package codes.purple.chabarok.controllers.admin;

import codes.purple.chabarok.controllers.responses.DefaultResponse;
import codes.purple.chabarok.controllers.responses.Status;
import codes.purple.chabarok.dtos.CategoryDTO;
import codes.purple.chabarok.dtos.DishDTO;
import codes.purple.chabarok.dtos.UserDTO;
import codes.purple.chabarok.services.CategoryService;
import codes.purple.chabarok.services.DishService;
import codes.purple.chabarok.services.UserService;
import codes.purple.chabarok.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AdminRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @GetMapping("/admin/create/user")
    public DefaultResponse createUser(UserDTO userDTO) {
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

    @GetMapping("/admin/menu/category/add")
    public DefaultResponse addCategory(CategoryDTO categoryDTO) {
        try {
            categoryService.createCategory(categoryDTO);
            return new DefaultResponse(Status.SUCCESS, "Category created!");
        } catch (CategoryAlreadyExistsException e) {
            return new DefaultResponse(Status.FAIL, "Category already exists!");
        }
    }

    //TODO Rewrite addDish REST
    @PostMapping("/admin/menu/dish/add")
    public DefaultResponse addDish(@RequestParam MultipartFile image, DishDTO dishDTO) throws DishNotFoundException {
        try {
            dishService.addDish(dishDTO);
            Long dishId = dishService.findByName(dishDTO.getName()).getId();
            dishService.saveImage(dishId, image);
            return new DefaultResponse(Status.SUCCESS, "Dish created!");
        } catch (IOException e) {
            return new DefaultResponse(Status.FAIL, "IOException");
        }
    }
}
