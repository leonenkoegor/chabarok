package codes.purple.chabarok.controllers;

import codes.purple.chabarok.controllers.responses.DataResponse;
import codes.purple.chabarok.controllers.responses.DefaultResponse;
import codes.purple.chabarok.controllers.responses.Status;
import codes.purple.chabarok.dtos.OrderedTableDTO;
import codes.purple.chabarok.models.Dish;
import codes.purple.chabarok.models.OrderDish;
import codes.purple.chabarok.models.OrderDishes;
import codes.purple.chabarok.models.OrderedTable;
import codes.purple.chabarok.services.CategoryService;
import codes.purple.chabarok.services.DishService;
import codes.purple.chabarok.services.OrderDishesService;
import codes.purple.chabarok.services.OrderedTableService;
import codes.purple.chabarok.services.exceptions.CategoryNotFoundException;
import codes.purple.chabarok.services.exceptions.DishNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
public class GuestRestController {

    @Autowired
    private OrderedTableService orderedTableService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @Autowired
    private OrderDishesService orderDishesService;

    //TODO rewrite getOrderedTables
    @GetMapping("/ordered/tables/get")
    public List<OrderedTableDTO> getOrderedTables(@RequestParam Date date) {
        List<OrderedTableDTO> orderedTableDTOS = new LinkedList<>();
        List<OrderedTable> orderedTables = orderedTableService.getByDate(date);
        for (OrderedTable orderedTable : orderedTables) {
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

    @GetMapping("/cart/dishes/get")
    public DataResponse getDishes(@RequestParam List<Long> dishes) {
        List<Dish> dishesList = new LinkedList<>();
        for (Long dish : dishes) {
            try {
                dishesList.add(dishService.findById(dish));
            } catch (DishNotFoundException e) {
                return new DataResponse(Status.FAIL, "Dish not found", null);
            }
        }
        return new DataResponse(Status.SUCCESS, "Dishes by id", dishesList);
    }

    @GetMapping("/cart/dishes/order")
    public DefaultResponse orderDishes(OrderDishes orderDishes, @RequestParam String[] dishAndAmount) {
        if(dishAndAmount.length == 0) {
            return new DefaultResponse(Status.FAIL, "Dish order is not created");
        }
        try {
            List<OrderDish> orderDishList = new LinkedList<>();
            for (String dishAndAmountEntry : dishAndAmount) {
                String[] res = dishAndAmountEntry.split("A");
                OrderDish orderDish = new OrderDish();
                orderDish.setDish(dishService.findById(Long.valueOf(res[0])));
                orderDish.setAmount(Long.valueOf(res[1]));
                orderDishList.add(orderDish);
            }
            orderDishes.setOrderDishList(orderDishList);
            orderDishesService.createOrder(orderDishes);
            return new DefaultResponse(Status.SUCCESS, "Dish order is created");
        } catch (DishNotFoundException e) {
            return new DefaultResponse(Status.FAIL, "Dish order is not created");
        }
    }
}
