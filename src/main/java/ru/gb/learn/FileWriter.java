package ru.gb.learn;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringJoiner;

public class FileWriter {
    public void write(Account acc) {
        try (java.io.FileWriter fw = new java.io.FileWriter("names.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw);) {
            StringJoiner joiner = new StringJoiner(" ", "", "\n");
            joiner.add(acc.getFio())
                    .add(acc.getBirthDate())
                    .add(acc.getPhone())
                    .add(acc.getSex());
            pw.write(joiner.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
