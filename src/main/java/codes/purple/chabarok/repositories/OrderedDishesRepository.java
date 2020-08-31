package codes.purple.chabarok.repositories;

import codes.purple.chabarok.models.OrderDishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedDishesRepository extends JpaRepository<OrderDishes, Long> {
}
