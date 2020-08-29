package codes.purple.chabarok.models;

import codes.purple.chabarok.dtos.DishDTO;
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
    @ManyToMany(mappedBy = "dishes")
    private Set<Category> categories;
    @OneToOne
    @JoinColumn(name = "imageId", referencedColumnName = "id")
    private ImageFile image = new ImageFile();
    
    public Dish(DishDTO dishDTO) {
        this.name = dishDTO.getName();
        this.description = dishDTO.getDescription();
    }

    @Tolerate
    public Dish() {
    }
}
