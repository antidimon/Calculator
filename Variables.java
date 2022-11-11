package Lessons;

import java.util.ArrayList;
import java.util.List;

public class Variables {
    public static int commaStatic, commaInt, memorySqrt;
    public static String memoryStr = "";
    public static double memoryNum = 0, memoryForSqrt = 0;
    public static final String[] symbols = {"C", "//", "x^n", "⌫", "√", "()", "%", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ",", "="};
    private static final String[] operations = {"//", "%", "/", "*", "-", "+", "="};
    public static final ArrayList<String> operationsList = new ArrayList<>(List.of(operations));
    private static final String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static  final ArrayList<String> numbersList = new ArrayList<>(List.of(numbers));
    public static ArrayList<String> mainArray = new ArrayList<>();
    public static ArrayList<String> operationsArray = new ArrayList<>();
    public static ArrayList<Double> numberArray = new ArrayList<>();
}
