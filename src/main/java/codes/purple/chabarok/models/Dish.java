package codes.purple.chabarok.models;

import codes.purple.chabarok.dtos.DishDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Data
@EqualsAndHashCode(exclude = {"categories", "image", "id"})
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double weight;
    private Double cost;
    private Boolean allowToOrder;
    @ManyToMany(mappedBy = "dishes", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Category> categories;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageId", referencedColumnName = "id")
    @JsonIgnore
    private ImageFile image;

    public Dish(DishDTO dishDTO) {
        this.name = dishDTO.getName();
        this.description = dishDTO.getDescription();
        this.allowToOrder = dishDTO.getAllowToOrder();
        this.weight = dishDTO.getWeight();
        this.cost = dishDTO.getCost();
    }

    @Tolerate
    public Dish() {
    }
}
