package codes.purple.chabarok.controllers.cooker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookerController {

    @GetMapping("cooker")
    public String cookerPage() {
        return "cooker";
    }

}
