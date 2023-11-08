package ru.gb.learn;

import java.text.SimpleDateFormat;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class Parser {
    public Account parse(String[] inputArray) throws ParseException {
        if (inputArray.length != 6)
            throw new ParseException("Введено неверное количество данных: " + inputArray.length);
        String fio = parseName(inputArray);
        String birthDate = parseDate(inputArray);
        String phone = parsePhone(inputArray);
        String sex = parseSex(inputArray);
        return new Account(fio, birthDate, phone, sex);
    }


    private String parseName(String[] inputArray) throws ParseException {
        Pattern pattern = Pattern.compile("\\p{IsAlphabetic}+");

        StringJoiner joiner = new StringJoiner(" ");
        int count = 0;
        for (int i = 0; i < inputArray.length && count < 3; i++) {
            String current = inputArray[i];
            if (count == 0) {
                if (i > inputArray.length - 3) throw new ParseException("не удалось распознать информацию о ФИО");
                if (current != null && pattern.matcher(current).matches()) {
                    inputArray[i] = null;
                    joiner.add(current);
                    count++;
                }
            } else {
                if (current == null || !pattern.matcher(current).matches())
                    throw new ParseException("не удалось распознать полную информацию о ФИО");
                joiner.add(current);
                count++;
            }
        }
        return joiner.toString();
    }


    private String parseDate(String[] inputArray) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        for (int i = 0; i < inputArray.length; i++) {
            String current = inputArray[i];
            if (current == null) continue;
            try {
                dateFormat.parse(current);
            } catch (java.text.ParseException ignored) {
                continue;
            }
            inputArray[i] = null;
            return current;
        }
        throw new ParseException("не удалось распознать информацию о дате рождения");
    }


    private String parsePhone(String[] inputArray) throws ParseException {
        Pattern pattern = Pattern.compile("\\d+");

        for (int i = 0; i < inputArray.length; i++) {
            String current = inputArray[i];
            if (current != null && pattern.matcher(current).matches()) {
                inputArray[i] = null;
                return current;
            }
        }
        throw new ParseException("не удалось распознать информацию о номере телефона");
    }


    private String parseSex(String[] inputArray) throws ParseException {
        Pattern pattern = Pattern.compile("[fm]");

        for (int i = 0; i < inputArray.length; i++) {
            String current = inputArray[i];
            if (current != null && pattern.matcher(current).matches()) {
                inputArray[i] = null;
                return current;
            }
        }
        throw new ParseException("не удалось распознать информацию о поле");
    }
}

