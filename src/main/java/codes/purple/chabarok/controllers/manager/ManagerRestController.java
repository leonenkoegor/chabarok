package codes.purple.chabarok.controllers.manager;

import codes.purple.chabarok.dtos.OrderedTableDTO;
import codes.purple.chabarok.models.OrderedTable;
import codes.purple.chabarok.services.OrderedTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class ManagerRestController {
    @Autowired
    private OrderedTableService orderedTableService;

    @GetMapping("/manager/ordered/tables/get")
    public List<OrderedTable> getOrderedTables(@RequestParam Date date) {
        return orderedTableService.getByDate(date);
    }

    @GetMapping("/manager/ordered/table/delete")
    public void deleteOrderedTable(@RequestParam Long id) {
        orderedTableService.deleteOrderedTableById(id);
    }

    @GetMapping("/manager/ordered/table/add")
    public void addOrderedTable(OrderedTableDTO orderedTableDTO) {
        orderedTableService.addOrderedTable(orderedTableDTO);
    }
}
