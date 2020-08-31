package codes.purple.chabarok.repositories;

import codes.purple.chabarok.models.OrderedTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderedTableRepository extends JpaRepository<OrderedTable, Long> {
    List<OrderedTable> findByOrderedDate(Date orderedDate);
}
