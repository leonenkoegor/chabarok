package codes.purple.chabarok.services;

import codes.purple.chabarok.models.OrderDishes;
import codes.purple.chabarok.repositories.OrderDishRepository;
import codes.purple.chabarok.repositories.OrderDishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderDishesService {
    @Autowired
    private OrderDishRepository orderDishRepository;

    @Autowired
    private OrderDishesRepository orderDishesRepository;

    public List<OrderDishes> findAll() {
        return orderDishesRepository.findAll();
    }

    public List<OrderDishes> findAllByDate(Date from) {
        Date till = (Date) from.clone();
        Calendar c = Calendar.getInstance();
        c.setTime(till);
        c.add(Calendar.DATE, 1);
        till = c.getTime();
        return orderDishesRepository.findAllByDateBetween(from, till);
    }

    public void createOrder(OrderDishes orderDishes) {
        orderDishes.getOrderDishList().forEach(orderDish -> {
            orderDishRepository.save(orderDish);
        });
        orderDishesRepository.save(orderDishes);
    }
}
