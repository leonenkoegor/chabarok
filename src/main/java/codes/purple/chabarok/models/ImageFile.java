package codes.purple.chabarok.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@EqualsAndHashCode(exclude = {"dish", "id"})
public class ImageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "image")
    private Dish dish;
    private String name;
    private String type;
    @Lob
    private byte[] data;
}
