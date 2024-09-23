package org.example;

import java.io.*;
import java.util.*;



public class Main {
    public static void main(String[] args){
        System.out.println("Введите путь до файла или завершите работу командой \"Завершить работу\"");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        while(!filePath.equals("Завершить работу")) {
            readFile(filePath);
            System.out.println("Введите путь до файла или завершите работу командой \"Завершить работу\"");
            filePath = scanner.nextLine();
        }
        System.out.println("Программа завершена");
    }

    static public void readFile(String filePath){
        try{
            Statistic statistic = new Statistic();
            File file = new File(filePath);
            String fileName = file.getName();
            int dotIndex = fileName.lastIndexOf('.');
            String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex+1);
            Format format;
            switch (extension){
                case "json" -> format = Format.JSON;
                case "csv" -> format = Format.CSV;
                default -> {
                    System.out.println("Неверный формат файла!");
                    return;
                }
            }
            switch (format){
                case JSON -> Reader.readFromJson(file, statistic);
                case CSV -> Reader.readFromCsv(file, statistic);
            }
        } catch (IOException e) {
            System.out.println("Не верно указан путь до файла!");
        }
    }
}