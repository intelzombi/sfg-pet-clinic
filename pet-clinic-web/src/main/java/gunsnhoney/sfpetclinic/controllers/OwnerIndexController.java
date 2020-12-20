package gunsnhoney.sfpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerIndexController {

    @RequestMapping({"/Owners", "/Owners/index", "/Owners/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("puppyParty", "Lets Train a Frog");
        return "owners/index";
    }
}
