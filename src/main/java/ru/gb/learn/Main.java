package ru.gb.learn;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные пользователя");
        String[] input = scanner.nextLine().split(" ");
        Parser parser = new Parser();
        Account acc = null;
        try {
            acc = parser.parse(input);
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
            return;
        }
        FileWriter fw = new FileWriter();
        fw.write(acc);
    }
}