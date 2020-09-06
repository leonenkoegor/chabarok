package codes.purple.chabarok.dtos;

import codes.purple.chabarok.models.Dish;
import lombok.Data;

@Data
public class DishDTO {
    private String name;
    private String description;
    private Boolean allowToOrder;
    private Double weight;
    private Double cost;

    public DishDTO(Dish dish) {
        this.name = dish.getName();
        this.description = dish.getDescription();
        this.allowToOrder = dish.getAllowToOrder();
        this.weight = dish.getWeight();
        this.cost = dish.getCost();
    }

    public DishDTO() {
    }
}
