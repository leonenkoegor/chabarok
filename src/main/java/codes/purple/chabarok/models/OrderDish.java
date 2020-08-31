package codes.purple.chabarok.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orderDish")
@Data
public class OrderDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordereDishesId")
    private OrderDishes orderDishes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dishId")
    private Dish dish;
    private Long amount;
}
