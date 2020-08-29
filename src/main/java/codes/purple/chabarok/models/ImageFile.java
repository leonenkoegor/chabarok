package codes.purple.chabarok.models;

import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class ImageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "image")
    private Dish dish;
    private String name;
    private String type;
    private byte[] data;
}
