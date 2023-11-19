package edu.hw6.Task1;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String filePath = "project-template/disk";

        edu.hw6.Task1.DiskMap diskMap = new edu.hw6.Task1.DiskMap(filePath);

        diskMap.put("rus", "привет");
        diskMap.put("esp", "holla");
        diskMap.put("french", "bonjour");
        System.out.println("Значение \"rus\": " + diskMap.get("rus"));
        diskMap.remove("rus");
        System.out.println("All keys: " + diskMap.keySet());
        System.out.println("All values: " + diskMap.values());
        diskMap.clear();
    }
}
