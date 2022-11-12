package Lessons;

/*
Что нужно сделать:
1. Добавить в удаление операций число с плавающей точкой
2. Добавить операции скобок путём вложенных массивов
3. Добавить возведение числа в степень в весь функционал
4. Добавить создание противоположного числа
 */

import java.awt.*;

import static Lessons.Frames.label1;
import static Lessons.Frames.label2;
import static Lessons.Variables.*;

public class Functional {
    public static void keyPressed(Labels label1, Labels label2, String valueOfButton) {
        if (operationsList.contains(valueOfButton)) {
            operation(valueOfButton);
            memoryStr += valueOfButton;
            label1.setText(memoryStr);
        }
        if (numbersList.contains(valueOfButton)){
            memoryNumber(Double.parseDouble(valueOfButton));
            memoryStr += valueOfButton;
            label1.setText(memoryStr);
        }
        switch (valueOfButton) {
            //Not standart operations
            case "C" -> {
                memoryStr = "";
                mainArray.clear();
                operationsArray.clear();
                numberArray.clear();
                memoryNum = 0;
                commaInt = 0;
                commaStatic = 0;
                memoryForSqrt = 0;
                label1.setText("0");
                label2.setText("0");
            }
            case "⌫" -> {
                if (memoryStr.length() > 1) {
                    memoryStr = memoryStr.substring(0, memoryStr.length() - 1);
                    label1.setText(memoryStr);
                } else {
                    memoryStr = "";
                    label1.setText("0");
                }
                otmenaDeystv();
            }
            case "√" -> {
                memorySqrt = 1;
                memoryStr += "√";
                label1.setText(memoryStr);
            }
            case "," -> {
                memoryStr += ",";
                label1.setText(memoryStr);
                commaStatic = 1;
                commaInt = 1;
            }
        }
    }

    private static void operation(String operation) {
        if (memorySqrt == 1) {
            memoryNum = Math.sqrt(memoryNum);
            memorySqrt = 0;
            memoryForSqrt = 0;
        }
        if (operation.equals("=")){
            label1.setText("");
            label2.setForeground(Color.BLACK);
            label2.setFont(new Font("<html>",Font.ITALIC, 50));
            label2.setText(String.valueOf(numberArray.get(0)));
        }
        else {
            mainArray.add(String.valueOf(memoryNum));
            memoryNum = 0;
            commaInt = 0;
            if (mainArray.size() > 1) {
                // После первой операции, если подряд идут 2 знака + или -, то вписываем + или -
                if ((operationsArray.get(0).equals("+")) && (mainArray.size() == 2) && (operation.equals("+") || operation.equals("-"))) {
                    mainArray.add(operationsArray.get(0));
                    operationsArray.clear();
                    operationsArray.add(operation);
                } else {
                    //Если операции в стэке уже 2
                    if (operationsArray.size() == 2) {
                        //Если слежующая операция + или -
                        if (operation.equals("+") || operation.equals("-")) {
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
                    else if ((!operationsArray.get(0).equals("+")) && (!operationsArray.get(0).equals("-"))) {
                        mainArray.add(operationsArray.get(0));
                        operationsArray.set(0, operation);
                    }
                    //Если подряд идут ++ -- +- -+
                    else if (operation.equals("+") || operation.equals("=")) {
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
            else {
                operationsArray.add(operation);
            }
            System.out.println(operationsArray);
            System.out.println(mainArray);
        }
    }

    private static void memoryNumber(double number) {
        //Составление числа
        if (numberArray.size() == 0) {
            memoryNum = number;
        }
        //Числа с запятой
        else if (commaStatic == 1) {
            memoryNum += number / (Math.pow(10, commaInt));
            commaInt++;
        } else {
            memoryNum = memoryNum * 10 + number;
        }

        makeAnswer();
        label2.setText(String.valueOf(numberArray.get(0)));
    }

    private static void otmenaDeystv() {
        if (mainArray.size() == 1 && operationsArray.size()==0){mainArray.clear();}
        else if (mainArray.size() == 1 && operationsArray.size()==1){operationsArray.clear();}
        else if ((memoryNum == 0)&&(operationsArray.size() == 2)){
            operationsArray.remove(operationsArray.indexOf(String.valueOf(memoryStr.charAt(memoryStr.length()-1))));
            memoryNum = Double.parseDouble(mainArray.get(mainArray.size()-1));
        }
        else if (operationsList.contains(mainArray.get(mainArray.size()-1)) && operationsList.contains(mainArray.get(mainArray.size()-2))){
            if (memoryNum > 0) {
                if (operationsArray.size() == 0) {
                    if (memoryNum < 10) {
                        memoryNum = 0;
                    } else {
                        memoryNum = (double) (Integer.parseInt(String.valueOf(memoryNum).substring(0, String.valueOf(memoryNum).length() - 2)) / 10);
                    }
                    if (memoryNum == 0) {
                        mainArray.remove(mainArray.size() - 3);
                        if (operationsArray.size() == 0) {
                            operationsArray.add(mainArray.get(mainArray.size() - 2));
                        } else {
                            operationsArray.set(0, mainArray.get(mainArray.size() - 2));
                        }
                        mainArray.remove(mainArray.size() - 2);
                    } else {
                        mainArray.set(mainArray.size() - 3, String.valueOf(memoryNum));
                    }
                }
                else {
                    memoryNum = (double) (Integer.parseInt(String.valueOf(memoryNum).substring(0, String.valueOf(memoryNum).length() - 2)) / 10);
                }
            }
            else if (operationsArray.size() != 1) {
            operationsArray.set(0, mainArray.get(mainArray.size()-1));
            mainArray.remove(mainArray.size()-1);
            memoryNum = Double.parseDouble(mainArray.get(mainArray.size()-1));
            }
            else {
                operationsArray.remove(0);
                memoryNum = Double.parseDouble(mainArray.get(mainArray.size()-3));
            }
        }
        else if ((memoryNum == 0)&&(operationsArray.size() == 1)){
            operationsArray.set(0, String.valueOf(mainArray.get(mainArray.size()-1)));
            memoryNum = Double.parseDouble(mainArray.get(mainArray.size()-2));
        }
        else if ((memoryNum > 0) && (operationsArray.size() == 1) && (memoryNum < 10)){
            operationsArray.set(0, mainArray.get(mainArray.size()-1));
            mainArray.remove(mainArray.size()-2);
            mainArray.remove(mainArray.size()-1);
            memoryNum = 0;
        }
        else if ((memoryNum >= 10) && (operationsArray.size() == 1)){
            memoryNum = (double) (Integer.parseInt(String.valueOf(memoryNum).substring(0, String.valueOf(memoryNum).length() - 2)) / 10);
            mainArray.set(mainArray.size()-2, String.valueOf(memoryNum));
        }
    }

    private static double operations(double number1, String operation, double number2) {
        switch (operation) {
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
            default -> {
                return 0;
            }
        }
    }

    public static void makeAnswer() {
        double num = memoryNum;
        if (memorySqrt == 1) {
            num = Math.sqrt(memoryNum);
        }
        if (numberArray.size() == 0) {
            numberArray.add(num);
        } else if (numberArray.size() == 1 && operationsArray.size() == 0) {
            numberArray.set(0, num);
        } else if (mainArray.size() == 1 && memorySqrt == 1) {
            if (memoryForSqrt != 0) {
                numberArray.set(0, operations(memoryForSqrt, operationsArray.get(0), num));
            } else {
                memoryForSqrt = numberArray.get(0);
                numberArray.set(0, operations(numberArray.get(0), operationsArray.get(0), num));
            }
        } else if (mainArray.size() == 1){
            numberArray.set(0,operations(numberArray.get(0), operationsArray.get(0), num));
        }else {
            // private final String[] operations = {"//", "%", "/", "*", "-", "+"};
            numberArray.clear();
            for (String s : mainArray) {
                int j = numberArray.size();
                if (operationsList.contains(s)) {
                    numberArray.set(j - 2, operations(numberArray.get(j - 2), s, numberArray.get(j - 1)));
                    numberArray.remove(j - 1);
                } else {
                    numberArray.add(Double.parseDouble(s));
                }
            }
            if (operationsArray.size() == 1) {
                numberArray.set(0, operations(numberArray.get(0), operationsArray.get(0), num));
            } else {
                numberArray.set(1, operations(numberArray.get(1), operationsArray.get(1), num));
                numberArray.set(0, operations(numberArray.get(0), operationsArray.get(0), numberArray.get(1)));
            }
        }
    }
}
