package codes.purple.chabarok.controllers.cashier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CashierController {

    @GetMapping("/cashier")
    public String cashierPage() {
        return "cashier";
    }

}
