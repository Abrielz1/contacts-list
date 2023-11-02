package com.example.contactslist.controller;

import com.example.contactslist.service.InDSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class Master {
    /*
     чтение get()
     создание post()
     изменение patch()
     удаление delete()
    */

    private final InDSService service;

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("getAllTasks", service.getAll());
        return "index";
    }
}
