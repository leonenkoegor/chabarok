package codes.purple.chabarok.controllers;

import codes.purple.chabarok.dtos.ImageFileDTO;
import codes.purple.chabarok.models.ImageFile;
import codes.purple.chabarok.services.DishService;
import codes.purple.chabarok.services.exceptions.DishNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GuestController {
    @Autowired
    private DishService dishService;

    @GetMapping("/menu/dishes/image/get")
    public void getDishImage(@RequestParam Long dishId, HttpServletResponse response) throws DishNotFoundException, IOException {
        ImageFile image = dishService.findById(dishId).getImage();
        response.setContentType(image.getType());
        response.getOutputStream().write(image.getData());
        response.getOutputStream().close();
    }
}
