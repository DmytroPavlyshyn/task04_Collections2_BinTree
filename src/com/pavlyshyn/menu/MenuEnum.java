package com.pavlyshyn.menu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MenuEnum {
    MY_FUNC_INTERFACE(()->{
        System.out.println("MyFuncInterface");
    }),
    COMMAND(()->{
        System.out.println("Test Command");
    }),
    STREAMS(()->{
        System.out.println("Test Streams");
    }),
    TEXT_LINES(()->{
        System.out.println("Test text lines");
    }),
    EXIT(()->{
        System.exit(0);
    });

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String command =  this.name().replaceAll("_", " ");
        Pattern pattern = Pattern.compile("(\\w)(\\w*)");
        Matcher matcher = pattern.matcher(command);
        while (matcher.find()){
            stringBuilder.append(matcher.group(1));
            stringBuilder.append(matcher.group(2).toLowerCase());
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
    }

    private Actionable actionable;

    MenuEnum(Actionable actionable) {
        this.actionable = actionable;
    }

    public Actionable getActionable() {
        return actionable;
    }

    public static void outputMenu(){
        System.out.println("\nMenuMap:");
        int i = 1;
        for(MenuEnum s: MenuEnum.values()){
            System.out.println("" + i++ + " "+ s);
        }
    }
    public static void show(){
        String keyMenu;
        Scanner scanner = new Scanner(System.in);
        while (true){
            outputMenu();
            System.out.println("Please select menu.");
            keyMenu = scanner.nextLine().toUpperCase();
            try {
                MenuEnum.values()[Integer.parseInt(keyMenu)-1].getActionable().run();
            }catch (Exception e){}
        }
    }

    public static void main(String[] args) {
        MenuEnum.show();
    }
}
