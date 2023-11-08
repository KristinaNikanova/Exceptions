package ru.gb.learn;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.StringJoiner;

@AllArgsConstructor
public class Account {
    @Getter
    private String surname;
    private String name;
    private String patronymic;
    private String birthDate;
    private String phone;
    private String sex;

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ", "", "\n");
        joiner.add(surname)
                .add(name)
                .add(patronymic)
                .add(birthDate)
                .add(phone)
                .add(sex);
        return joiner.toString();
    }
}
