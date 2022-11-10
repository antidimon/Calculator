package Lessons;

import javax.swing.*;
import java.awt.*;

public class Frames extends JFrame{
    public static Labels label1 = new Labels();
    public static Labels label2 = new Labels();

    Frames(){
        JPanel upperPanel = new JPanel();
        upperPanel.setPreferredSize(new Dimension(500, 220));
        upperPanel.setLayout(new GridLayout(2, 1));
        label1.setText("0");
        label1.setFont(new Font("<html>",Font.ITALIC, 20));
        label2.setForeground(Color.gray);
        label2.setFont(new Font("<html>",Font.ITALIC, 30));
        label2.setText("0");
        upperPanel.add(label1);
        upperPanel.add(label2);


        JPanel downPanel = new JPanel();
        downPanel.setBackground(Color.GRAY);
        downPanel.setPreferredSize(new Dimension(500, 530));
        downPanel.setLayout(new GridLayout(6, 4, 5, 5));
        downPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
        for (int i = 0; i < 24; i++){
            downPanel.add(new Buttons(i));
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 750);
        this.setLayout(new BorderLayout());
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(downPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setResizable(false);
    }
}
