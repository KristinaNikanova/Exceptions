package ru.gb.learn;

import java.text.SimpleDateFormat;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class Parser {
    public Account parse(String[] inputArray) throws ParseException {
        if (inputArray.length != 6)
            throw new ParseException("Введено неверное количество данных: " + inputArray.length);
        String fio = new NameParser().parse(inputArray);
        String birthDate = new DateParser().parse(inputArray);
        String phone = new PhoneParser().parse(inputArray);
        String sex = new SexParser().parse(inputArray);
        return new Account(fio, birthDate, phone, sex);
    }

    private abstract static class SubParser {
        abstract String parse(String[] inputArray) throws ParseException;
    }


    private static class NameParser extends SubParser {
        Pattern pattern = Pattern.compile("\\p{IsAlphabetic}");

        @Override
        String parse(String[] inputArray) throws ParseException {
            StringJoiner joiner = new StringJoiner(" ");
            int count = 0;
            for (int i = 0; i < inputArray.length || count == 3; i++) {
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
    }


    private static class DateParser extends SubParser {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        @Override
        String parse(String[] inputArray) throws ParseException {
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
    }


    private static class PhoneParser extends SubParser {
        Pattern pattern = Pattern.compile("\\d+");

        @Override
        String parse(String[] inputArray) throws ParseException {
            for (int i = 0; i < inputArray.length; i++) {
                String current = inputArray[i];
                if (current != null && pattern.matcher(current).matches()) {
                    inputArray[i] = null;
                    return current;
                }
            }
            throw new ParseException("не удалось распознать информацию о номере телефона");
        }
    }


    private static class SexParser extends SubParser {
        Pattern pattern = Pattern.compile("[fm]");

        @Override
        String parse(String[] inputArray) throws ParseException {
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
}
