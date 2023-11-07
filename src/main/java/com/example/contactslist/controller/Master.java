package com.example.contactslist.controller;

import com.example.contactslist.entity.Contacts;
import com.example.contactslist.service.InDBService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Positive;

@Slf4j
@Controller
@Primary
@Validated
@RequiredArgsConstructor
public class Master {

    private final InDBService service;

    @GetMapping("/")
    public String mainPage(Model model) {

        model.addAttribute("listOfContacts", service.getAll());
        return "index";
    }

    @GetMapping("/contacts/create")
    public String showCreateContact(Model model) {

        model.addAttribute("contact", new Contacts());
        return "create";
    }

    @PostMapping("/contacts/create")
    public String createContact(@ModelAttribute Contacts contact) {

        service.createContact(contact);
        return "redirect:/";
    }

    @GetMapping("/contacts/edit/{id}")
    public String editContact(@Positive @PathVariable Long id, Model model) {

        Contacts contact = service.findById(id);

        if (contact != null) {
            model.addAttribute("contact", contact);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/contacts/edit")
    public String editContact(@ModelAttribute Contacts contact) {

        service.editContact(contact);
        return "redirect:/";
    }

    @GetMapping("/contacts/delete/{id}")
    public String deleteContact(@Positive @PathVariable Long id) {

        service.deleteContactById(id);
        return "redirect:/";
    }
}
