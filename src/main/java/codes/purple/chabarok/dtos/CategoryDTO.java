package codes.purple.chabarok.dtos;

import codes.purple.chabarok.models.Category;
import codes.purple.chabarok.models.Dish;
import lombok.Data;

import java.util.Set;

@Data
public class CategoryDTO {
    private String mainCategoryName;
    private String name;
    private Set<Dish> dishes;

    public CategoryDTO(Category category) {
        this.mainCategoryName = category.getMainCategoryName();
        this.name = category.getName();
        this.dishes = category.getDishes();
    }

    public CategoryDTO() {
    }
}
