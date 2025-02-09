package codes.purple.chabarok.controllers.admin;

import codes.purple.chabarok.controllers.responses.DataResponse;
import codes.purple.chabarok.controllers.responses.DefaultResponse;
import codes.purple.chabarok.controllers.responses.Status;
import codes.purple.chabarok.dtos.CategoryDTO;
import codes.purple.chabarok.dtos.DishDTO;
import codes.purple.chabarok.dtos.UserDTO;
import codes.purple.chabarok.models.Dish;
import codes.purple.chabarok.models.Event;
import codes.purple.chabarok.services.*;
import codes.purple.chabarok.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
public class AdminRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @Autowired
    private OrderedTableService orderedTableService;

    @Autowired
    private OrderDishesService orderDishesService;

    @Autowired
    private EventService eventService;

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

    @GetMapping("/admin/booking/get")
    public DataResponse getBooking(@RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") Date date) {
        return new DataResponse(Status.SUCCESS, null, orderedTableService.getByDate(date));
    }

    //TODO Rewrite addDish REST
    @PostMapping("/admin/menu/dish/add")
    public DefaultResponse addDish(@RequestParam MultipartFile image, DishDTO dishDTO, @RequestParam Long categoryId) throws DishNotFoundException {
        try {
            dishService.addDish(dishDTO);
            Dish dish = dishService.findByName(dishDTO.getName());
            categoryService.addDishToCategory(dish, categoryId);
            dishService.saveImage(dish.getId(), image);
            return new DefaultResponse(Status.SUCCESS, "Dish created!");
        } catch (IOException e) {
            return new DefaultResponse(Status.FAIL, "IOException");
        } catch (CantAddDishToCategoryException e) {
            return new DefaultResponse(Status.FAIL, "Cant add dish to category");
        }
    }

    @PostMapping("/admin/menu/dish/toggle")
    public DefaultResponse disableDish(@RequestParam Long dishId) {
        try {
            dishService.toggleDish(dishId);
            return new DefaultResponse(Status.SUCCESS, "Dish toggled");
        } catch (DishNotFoundException e) {
            return new DefaultResponse(Status.FAIL, "Dish not toggled");
        }
    }

    @PostMapping("/admin/menu/dish/update")
    public DefaultResponse updateDish(@RequestParam Long dishId, DishDTO dishDTO) {
        try {
            dishService.updateDish(dishId, dishDTO);
            return new DefaultResponse(Status.SUCCESS, "Dish updated");
        } catch (DishNotFoundException e) {
            return new DefaultResponse(Status.FAIL, "Dish not updated");
        }
    }

    @GetMapping("/admin/orders/get")
    public DataResponse getOrders(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) {
        return new DataResponse(Status.SUCCESS, "Orders by date", orderDishesService.findAllByDate(date));
    }

    @GetMapping("/admin/ordered/tables/get")
    public DataResponse getOrderedTables(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) {
        return new DataResponse(Status.SUCCESS, "Odered tables by date", orderedTableService.getByDate(date));
    }

    @PostMapping("/admin/event/update")
    public DefaultResponse updateEvent(Event event, @RequestParam MultipartFile imageFile) {
        try {
            eventService.updateEvent(event);
            eventService.saveImage(event.getId(), imageFile);
        } catch (IOException | EventNotFoundException e) {
            return new DefaultResponse(Status.FAIL, "Event not updated");
        }
        return new DefaultResponse(Status.SUCCESS, "Event updated");
    }

    @GetMapping("/admin/events/get")
    public DataResponse getEvents() {
        return new DataResponse(Status.SUCCESS, "Get all events", eventService.findAllEvent());
    }

    @PostMapping("/admin/menu/dish/updateImage")
    public DefaultResponse updateDishImage(@RequestParam MultipartFile image, @RequestParam Long dishId) throws DishNotFoundException {
        try {
            dishService.saveImage(dishId, image);
            return new DefaultResponse(Status.SUCCESS, "Dish image updated!");
        } catch (IOException e) {
            return new DefaultResponse(Status.FAIL, "Dish image not updated!");
        }
    }
}
