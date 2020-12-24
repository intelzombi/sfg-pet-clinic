package gunsnhoney.sfpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/oups")
    public String oups(Model model) {
        return "notimplemented";
    }
}
