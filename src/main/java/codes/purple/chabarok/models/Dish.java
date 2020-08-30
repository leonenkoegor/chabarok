package codes.purple.chabarok.models;

import codes.purple.chabarok.dtos.DishDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double weight;
    private Double cost;
    @ManyToMany(mappedBy = "dishes")
    @JsonIgnore
    private Set<Category> categories;
    @OneToOne
    @JoinColumn(name = "imageId", referencedColumnName = "id")
    @JsonIgnore
    private ImageFile image;

    public Dish(DishDTO dishDTO) {
        this.name = dishDTO.getName();
        this.description = dishDTO.getDescription();
        this.weight = dishDTO.getWeight();
        this.cost = dishDTO.getCost();
    }

    @Tolerate
    public Dish() {
    }
}
