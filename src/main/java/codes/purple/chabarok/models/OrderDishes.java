package codes.purple.chabarok.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orderedDishes")
@Data
public class OrderDishes {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderDishes")
    private List<OrderDish> orderDishList;
    private Double totalPrice;
    private String name;
    private String address;
    private String phoneNumber;
    private Boolean isToDeliver;
    private Boolean isToPayByCard;
    private Boolean isDone;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date date;
}
