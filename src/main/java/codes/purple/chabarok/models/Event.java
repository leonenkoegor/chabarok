package codes.purple.chabarok.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageId", referencedColumnName = "id")
    @JsonIgnore
    private ImageFile image;
}
