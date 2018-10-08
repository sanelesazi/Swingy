package com.ssibiya.swingy.view.console;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {

    protected final static String RESET = "\u001B[0m";
    protected final static String GREEN = "\u001B[32m";
    protected final static String YELLOW = "\u001B[33m";
    protected final static String RED = "\u001B[31m";
    protected final static String PURPLE = "\u001B[35m";

    public static void clearConsole()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String getInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print(PURPLE + "--->");
        try {
            input = reader.readLine();}
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(RESET);
        return input;
    }
}
