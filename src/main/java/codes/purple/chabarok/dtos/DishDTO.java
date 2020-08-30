package codes.purple.chabarok.dtos;

import codes.purple.chabarok.models.Dish;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class DishDTO {
    private Long id;
    private String name;
    private String description;
    private Double weight;
    private Double cost;

    public DishDTO(Dish dish) {
        this.name = dish.getName();
        this.description = dish.getDescription();
        this.weight = dish.getWeight();
        this.cost = dish.getCost();
    }

    public DishDTO() {
    }
}
