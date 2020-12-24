package gunsnhoney.sfpetclinic.controllers;

import gunsnhoney.sfpetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerIndexController {

    private final OwnerService ownerService;

    public OwnerIndexController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/", "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping({"/find", "/find/index", "/find/index.html"})
    public String findOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "notimplemented";
    }
}
