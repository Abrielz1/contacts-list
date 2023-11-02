package com.example.contactslist.controller;

import com.example.contactslist.entity.Contacts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class Master {
    /*
     чтение get() getALL()
     создание post()
     изменение patch()
     удаление delete()
    */

    //private final ContactsService service;

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
}
