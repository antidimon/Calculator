package Lessons;

import javax.swing.*;
import java.awt.*;

public class Labels extends JLabel{
    Labels(){
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.RIGHT);
        this.setVerticalAlignment(JLabel.CENTER);
    }
}
