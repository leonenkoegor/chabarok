package codes.purple.chabarok.repositories;

import codes.purple.chabarok.models.OrderDishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderDishesRepository extends JpaRepository<OrderDishes, Long> {
    List<OrderDishes> findAllByDate(Date date);
}
