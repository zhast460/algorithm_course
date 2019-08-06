package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LC359_Logger_Rate_Limiter {
    private Map<String, Integer> map = new HashMap<>();
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message) || map.get(message) <= timestamp - 10){
            map.put(message, timestamp);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        LC359_Logger_Rate_Limiter logger = new LC359_Logger_Rate_Limiter();
        System.out.println(logger.shouldPrintMessage(1, "foo"));
        System.out.println(logger.shouldPrintMessage(2,"bar"));
        System.out.println(logger.shouldPrintMessage(3,"foo"));
        System.out.println(logger.shouldPrintMessage(8,"bar"));
        System.out.println(logger.shouldPrintMessage(10,"foo"));
        System.out.println(logger.shouldPrintMessage(11,"foo"));
    }
}
