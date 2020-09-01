package codes.purple.chabarok.repositories;

import codes.purple.chabarok.models.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDish, Long> {
}
