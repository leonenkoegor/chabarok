package codes.purple.chabarok.controllers.cook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookController {

    @GetMapping("cook")
    public String cookPage() {
        return "cook";
    }

}
