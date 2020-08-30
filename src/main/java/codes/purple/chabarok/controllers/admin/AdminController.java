package codes.purple.chabarok.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/admin/menu")
    public String adminMenuPage() {
        return "admin-menu";
    }

    //TODO NAhui udalit\'
    @GetMapping("/admin/get/fragment/addUser")
    public String adminAddUserFragment() {
        return "addUser";
    }

}
