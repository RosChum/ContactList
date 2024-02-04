package com.example.contactlist.controller;

import com.example.contactlist.model.Contact;
import com.example.contactlist.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final ContactService contactService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "index";
    }

    @PostMapping("/")
    public String addContact(Contact contact){
        contactService.save(contact);
        return "redirect:/";
    }

    @GetMapping("contact/edit/{id}")
    public String getEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("contact", contactService.findById(id));
        return "edit";
    }

    @PostMapping("contact/edit")
    public String edit(Contact contact) {
        log.info(" edit " + contact);
        contactService.update(contact);
        return "redirect:/";
    }


    @GetMapping("contact/delete/{id}")
    public String delete(@PathVariable Long id) {
        contactService.deleteById(id);
        return "redirect:/";
    }


}
