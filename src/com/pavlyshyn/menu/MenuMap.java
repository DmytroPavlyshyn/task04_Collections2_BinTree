package com.pavlyshyn.menu;

import java.util.*;

public class MenuMap {
    Map<String, DescriptionAction> descriptionActionMap;

    public MenuMap() {
        descriptionActionMap = new LinkedHashMap<>();
    }

    public MenuMap(Map<String, DescriptionAction> descriptionActionMap) {
        this.descriptionActionMap = descriptionActionMap;
    }

    public void put(String command, String description, Actionable actionable) {
        descriptionActionMap.put(command, new DescriptionAction(description, actionable));
    }
    public void outputMenu(){
        System.out.println("\nMenuMap:");
        for(DescriptionAction s: descriptionActionMap.values()){
            System.out.println(s.getDescription());
        }
    }
    public void show(){
        String keyMenu;
        Scanner scanner = new Scanner(System.in);
        while (true){
            outputMenu();
            System.out.println("Please select menu.");
            keyMenu = scanner.nextLine().toUpperCase();
            try {
                descriptionActionMap.get(keyMenu).getAction().run();
            }catch (Exception e){}
        }
    }
    public static void main(String[] args) {
        MenuMap menuMap = new MenuMap();
        menuMap.put("1", "1 - Test MyFuncInterface",()->{
            System.out.println("MyFuncInterface");
        });
        menuMap.put("2", "2 - Test Command", ()->{
            System.out.println("Test Command");
        });
        menuMap.put("3", "3 - Test Streams", ()->{
            System.out.println("Test Streams");
        });
        menuMap.put("4", "4 - Test text lines",()-> {
            System.out.println("Test text lines");
        });
        menuMap.put("Q","Q - exit", ()->{
            System.exit(0);
        });
        menuMap.show();

    }
}
