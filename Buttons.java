package Lessons;

import javax.swing.*;
import java.awt.*;

import static Lessons.Frames.label1;
import static Lessons.Frames.label2;
import static Lessons.Variables.symbols;

public class Buttons extends JButton {
    Buttons(int numberOfSymbol){
        //Настройки кнопок
        this.setText(symbols[numberOfSymbol]);
        this.setFocusable(false);
        this.setFont(new Font("<html>", Font.BOLD, 20));
        this.addActionListener(e -> Functional.keyPressed(label1, label2,symbols[numberOfSymbol]));
        if (symbols[numberOfSymbol].equals("=")){
            this.setBackground(Color.GREEN);
        }
    }
}
