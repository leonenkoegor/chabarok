package codes.purple.chabarok.dtos;

import codes.purple.chabarok.models.Dish;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class DishDTO {
    private Long id;
    private String name;
    private String description;

    public DishDTO(Dish dish) {
        this.name = dish.getName();
        this.description = dish.getDescription();
    }

    @Tolerate
    public DishDTO() {
    }
}
