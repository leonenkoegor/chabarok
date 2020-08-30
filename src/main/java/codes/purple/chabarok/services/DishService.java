package codes.purple.chabarok.services;

import codes.purple.chabarok.dtos.DishDTO;
import codes.purple.chabarok.models.Dish;
import codes.purple.chabarok.models.ImageFile;
import codes.purple.chabarok.repositories.DishRepository;
import codes.purple.chabarok.services.exceptions.DishNotFoundException;
import codes.purple.chabarok.services.exceptions.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public Dish findById(Long id) throws DishNotFoundException {
        return dishRepository.findById(id).orElseThrow(DishNotFoundException::new);
    }

    public Dish findByName(String name) throws DishNotFoundException {
        return dishRepository.findByName(name).orElseThrow(DishNotFoundException::new);
    }

    public void addDish(DishDTO dishDTO) {
        try {
            findByName(dishDTO.getName());
        } catch (DishNotFoundException e) {
            dishRepository.save(new Dish(dishDTO));
        }
    }

    public void deleteDishById(Long id) {
        dishRepository.deleteById(id);
    }

    public void saveImage(Long dishId, MultipartFile image) throws DishNotFoundException, IOException {
        Dish dish = findById(dishId);
        ImageFile dishImage = dish.getImage();
        dishImage.setName(image.getName());
        dishImage.setType(image.getContentType());
        dishImage.setData(image.getBytes());
        dishRepository.save(dish);
    }

    public ImageFile getDishImage(Long dishId) throws DishNotFoundException, ImageNotFoundException {
        ImageFile dishImage = findById(dishId).getImage();
        if(dishImage.getData() == null) {
            throw new ImageNotFoundException();
        }
        return dishImage;
    }
}
