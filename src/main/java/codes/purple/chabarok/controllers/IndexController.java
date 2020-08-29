package codes.purple.chabarok.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/menu")
    public String menuPage() {
        return "menu";
    }

    @GetMapping("/booking")
    public String bookingPage() {
        return "booking";
    }

    @GetMapping("/burak")
    public String burakPage() {
        return "burak";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/deliver")
    public String deliverPage() {
        return "deliver";
    }

    @GetMapping("/contacts")
    public String contactsPage() {
        return "contacts";
    }
}
