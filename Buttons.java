package Lessons;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static Lessons.Frames.label1;
import static Lessons.Frames.label2;

public class Buttons extends JButton {
    public static int commaStatic, commaInt;
    public static String memoryStr = "";
    public static double memoryNum = 0, memoryVar = 0;
    private final String[] symbols = {"C", "//", "", "⌫", "√", "()", "%", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ",", "="};
    private final String[] operations = {"//", "%", "/", "*", "-", "+"};
    private final ArrayList<String> operationsList = new ArrayList<>(List.of(operations));
    private static ArrayList<String> mainArray = new ArrayList<>();
    private static ArrayList<String> operationsArray = new ArrayList<>();
    private static ArrayList<Double> numberArray = new ArrayList<>();
    Buttons(int numberOfSymbol){
        //Настройки кнопок
        this.setText(symbols[numberOfSymbol]);
        this.setFocusable(false);
        this.setFont(new Font("<html>", Font.BOLD, 20));
        this.addActionListener(e -> keyPressed(label1, label2,symbols[numberOfSymbol]));
        if (symbols[numberOfSymbol].equals("=")){
            this.setBackground(Color.GREEN);
        }
    }
    private void keyPressed(Labels label1, Labels label2, String valueOfButton){
        switch (valueOfButton) {
            //operations
            case "C" -> {
                memoryStr = "";
                mainArray.clear();
                operationsArray.clear();
                memoryNum = 0;
                commaInt = 0;
                commaStatic = 0;
                label1.setText("0");
                label2.setText("0");
            }
            case "//" -> {
                operation("//");
                memoryStr += "//";
                label1.setText(memoryStr);
            }
            case "⌫" -> {
                if (memoryStr.length() > 1) {
                    memoryStr = memoryStr.substring(0, memoryStr.length() - 1);
                    label1.setText(memoryStr);
                }
                else {
                    memoryStr = "";
                    label1.setText("0");
                }
                otmenaDeystv();
            }
            case "√" -> {

                memoryStr += "√";
                label1.setText(memoryStr);
            }
            case "%" -> {
                operation("%");
                memoryStr += "%";
                label1.setText(memoryStr);
            }
            case "/" -> {
                operation("/");
                memoryStr += "/";
                label1.setText(memoryStr);
            }
            case "*" -> {
                operation("*");
                memoryStr += "*";
                label1.setText(memoryStr);
            }
            case "-" -> {
                operation("-");
                memoryStr += "-";
                label1.setText(memoryStr);
            }
            case "+" -> {
                operation("+");
                memoryStr += "+";
                label1.setText(memoryStr);
            }
            case "," -> {
                memoryStr += ",";
                label1.setText(memoryStr);
                commaStatic = 1;
                commaInt = 1;
            }

            //numbers
            case "0" -> {
                memoryNumber(0);
                memoryStr += "0";
                label1.setText(memoryStr);
            }
            case "1" -> {
                memoryNumber(1);
                memoryStr += "1";
                label1.setText(memoryStr);
            }
            case "2" -> {
                memoryNumber(2);
                memoryStr += "2";
                label1.setText(memoryStr);
            }
            case "3" -> {
                memoryNumber(3);
                memoryStr += "3";
                label1.setText(memoryStr);
            }
            case "4" -> {
                memoryNumber(4);
                memoryStr += "4";
                label1.setText(memoryStr);
            }
            case "5" -> {
                memoryNumber(5);
                memoryStr += "5";
                label1.setText(memoryStr);
            }
            case "6" -> {
                memoryNumber(6);
                memoryStr += "6";
                label1.setText(memoryStr);
            }
            case "7" -> {
                memoryNumber(7);
                memoryStr += "7";
                label1.setText(memoryStr);
            }
            case "8" -> {
                memoryNumber(8);
                memoryStr += "8";
                label1.setText(memoryStr);
            }
            case "9" -> {
                memoryNumber(9);
                memoryStr += "9";
                label1.setText(memoryStr);
            }
        }
    }
    private void operation(String operation){
        mainArray.add(String.valueOf(memoryNum));
        memoryNum = 0;
        commaInt = 0;
        if (mainArray.size() > 1) {
            // После первой операции, если подряд идут 2 знака + или -, то вписываем + или -
            if ((operationsArray.get(0).equals("+")) && (mainArray.size() == 2) && (operation.equals("+") || operation.equals("-"))){
                mainArray.add(operationsArray.get(0));
                operationsArray.clear();
                operationsArray.add(operation);
            }
            else {
                //Если операции в стэке уже 2
                if (operationsArray.size()==2){
                    //Если слежующая операция + или -
                    if (operation.equals("+") || operation.equals("-")){
                        mainArray.add(operationsArray.get(1));
                        mainArray.add(operationsArray.get(0));
                        operationsArray.clear();
                        operationsArray.add(operation);
                    }
                    //Если следующая операция не + или -
                    else {
                        mainArray.add(operationsArray.get(1));
                        operationsArray.set(1, operation);
                    }
                }
                //Если операцая в стэке 1
                //Если эта операция не + или -
                else if ((!operationsArray.get(0).equals("+")) && (!operationsArray.get(0).equals("-"))){
                    mainArray.add(operationsArray.get(0));
                    operationsArray.set(0, operation);
                }
                //Если подряд идут ++ -- +- -+
                else if (operation.equals("+") || operation.equals("=")){
                    mainArray.add(operationsArray.get(0));
                    operationsArray.set(0, operation);
                }
                //Если первая операция + или -, а следующая не + или -
                else {
                    operationsArray.add(operation);
                }
            }
        }
        //Первая операция
        else {operationsArray.add(operation);}
        System.out.println(mainArray);
    }
    private void memoryNumber(double number){
        //Составление числа
        if (mainArray.size() == 0){memoryNum = number;}
        //Числа с запятой
        else if (commaStatic == 1){
            memoryNum += number/(Math.pow(10, commaInt));
            commaInt++;
        }
        else {memoryNum = memoryNum*10 + number;}

        if (mainArray.size() == 0){
            numberArray.add(memoryNum);
        }
        else if (mainArray.size() <= 2){
            numberArray.set(0, operations(numberArray.get(0), operationsArray.get(0), memoryNum));
        }
        else {
            // private final String[] operations = {"//", "%", "/", "*", "-", "+"};
            for (int i = 0; i < mainArray.size(); i++){
                int j = numberArray.size();
                if (operationsList.contains(mainArray.get(i))){
                    numberArray.set(j-2, operations(numberArray.get(j-2), mainArray.get(i), numberArray.get(j-1)));
                    numberArray.remove(j-1);
                }
                else {
                    numberArray.set(j, Double.parseDouble(mainArray.get(i)));
                }
            }
        }
        label2.setText(String.valueOf(numberArray.get(0)));
    }
    private void otmenaDeystv(){

    }
    private double operations (double number1, String operation, double number2){
        switch (operation){
            case "//" -> {
                return (double) Integer.parseInt(String.valueOf(number1)) / Integer.parseInt(String.valueOf(number2));
            }
            case "%" -> {
                return number1 % number2;
            }
            case "/" -> {
                return number1 / number2;
            }
            case "*" -> {
                return number1 * number2;
            }
            case "-" -> {
                return number1 - number2;
            }
            case "+" -> {
                return number1 + number2;
            }
            default -> {return 0;}
        }
    }
}
