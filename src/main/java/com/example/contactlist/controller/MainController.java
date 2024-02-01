package com.example.contactlist.controller;

import com.example.contactlist.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ContactService contactService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "index";
    }

    @GetMapping("contact/edit/{id}")
    public String edit(@PathVariable Long id,  Model model) {

        contactService.deleteById(id);
        return "edit";
    }



    @GetMapping("contact/delete/{id}")
    public String delete(@PathVariable Long id) {
        contactService.deleteById(id);
        return "redirect:/";
    }


}
