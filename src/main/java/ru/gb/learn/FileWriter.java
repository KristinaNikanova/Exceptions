package ru.gb.learn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
    public void write(Account acc) {
        try (java.io.FileWriter fw = new java.io.FileWriter(new File("data", acc.getSurname()) + ".txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw);) {
            pw.write(acc.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
