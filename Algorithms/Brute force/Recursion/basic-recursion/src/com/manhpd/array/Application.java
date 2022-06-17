package com.manhpd.array;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("China", "Asia");
        map.put("USA", "North America");

        for (String key : map.keySet()) {
            System.out.println(key + " in " + map.get(key));
            map.put("Viet Nam", "Asia");
        }
    }

}
