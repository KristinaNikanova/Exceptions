package ru.gb.learn;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Account {
    private String fio;
    private String birthDate;
    private String phone;
    private String sex;
}
