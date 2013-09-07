package poe.unique.tracker;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        MidScreenFrame frame = new MidScreenFrame();
        frame.frame.setTitle("PoE - Unique Tracker"); 

        MainPanel primarypanel = new MainPanel();
        frame.frame.getContentPane().add(primarypanel);

        frame.frame.pack();
        frame.frame.setVisible(true);
    }
}