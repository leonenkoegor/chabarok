package codes.purple.chabarok.services;

import codes.purple.chabarok.models.OrderDishes;
import codes.purple.chabarok.repositories.OrderDishRepository;
import codes.purple.chabarok.repositories.OrderDishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderDishesService {
    @Autowired
    private OrderDishRepository orderDishRepository;

    @Autowired
    private OrderDishesRepository orderDishesRepository;

    private List<OrderDishes> findAll() {
        return orderDishesRepository.findAll();
    }

    private List<OrderDishes> findAllByDate(Date date) {
        return orderDishesRepository.findAllByDate(date);
    }

    public void createOrder(OrderDishes orderDishes) {
        orderDishes.getOrderDishList().forEach(orderDish -> {
            orderDishRepository.save(orderDish);
        });
        orderDishesRepository.save(orderDishes);
    }
}
