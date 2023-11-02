package com.example.contactslist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
}
