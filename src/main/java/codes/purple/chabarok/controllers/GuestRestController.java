package codes.purple.chabarok.controllers;

import codes.purple.chabarok.controllers.responses.DataResponse;
import codes.purple.chabarok.controllers.responses.Status;
import codes.purple.chabarok.dtos.OrderedTableDTO;
import codes.purple.chabarok.models.OrderedTable;
import codes.purple.chabarok.services.CategoryService;
import codes.purple.chabarok.services.OrderedTableService;
import codes.purple.chabarok.services.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
public class GuestRestController {
    @Autowired
    private OrderedTableService orderedTableService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/ordered/tables/get")
    public List<OrderedTableDTO> getOrderedTables(@RequestParam Date date) {
        List<OrderedTableDTO> orderedTableDTOS = new LinkedList<>();
        List<OrderedTable> orderedTables = orderedTableService.getByDate(date);
        for(OrderedTable orderedTable : orderedTables){
            orderedTableDTOS.add(new OrderedTableDTO(orderedTable));
        }
        return orderedTableDTOS;
    }

    @GetMapping("/ordered/table/add")
    public void addOrderedTable(OrderedTableDTO orderedTableDTO) {
        orderedTableService.addOrderedTable(orderedTableDTO);
    }

    @GetMapping("/menu/categories/get")
    public DataResponse getAllCategories() {
        return new DataResponse(Status.SUCCESS, "All categories!", categoryService.getAllCategories());
    }

    @GetMapping("/menu/dishes/get")
    public DataResponse getDishesFromCategory(@RequestParam Long categoryId) {
        try {
            return new DataResponse(Status.SUCCESS, "All dishes from category!", categoryService.findById(categoryId).getDishes());
        } catch (CategoryNotFoundException e) {
            return new DataResponse(Status.FAIL, "Category not found!", null);
        }
    }
}
