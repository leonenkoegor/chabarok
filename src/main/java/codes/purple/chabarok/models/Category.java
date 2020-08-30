package codes.purple.chabarok.models;

import codes.purple.chabarok.dtos.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mainCategoryName;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "categoryDish",
            joinColumns = @JoinColumn(name = "categoryId"),
            inverseJoinColumns = @JoinColumn(name = "dishId")
    )
    @JsonIgnore
    private Set<Dish> dishes;

    public Category(CategoryDTO categoryDTO) {
        this.mainCategoryName = categoryDTO.getMainCategoryName();
        this.name = categoryDTO.getName();
        this.dishes = categoryDTO.getDishes();
    }

    @Tolerate
    public Category() {
    }
}
