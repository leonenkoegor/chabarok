package codes.purple.chabarok.services;

import codes.purple.chabarok.dtos.OrderedTableDTO;
import codes.purple.chabarok.models.OrderedTable;
import codes.purple.chabarok.repositories.OrderedTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class OrderedTableService {
    @Autowired
    private OrderedTableRepository orderedTableRepository;

    public List<OrderedTable> getByDate(Date date) {
        return orderedTableRepository.findByOrderedDate(date);
    }

    public void addOrderedTable(OrderedTableDTO orderedTableDTO) {
        orderedTableRepository.save(new OrderedTable(orderedTableDTO));
    }

    public void deleteOrderedTableById(Long id) {
        orderedTableRepository.deleteById(id);
    }
}
