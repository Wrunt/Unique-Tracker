package poe.unique.tracker;

import java.awt.*;
import javax.swing.*;

public class MidScreenFrame {
    public JFrame frame;
    public int fx, fy, resX, resY, offX, offY;
    
    public MidScreenFrame() {
        fx = 800;
        fy = 350;
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        resX = dim.width;
        resY = dim.height;
        
        offX = resX / 2 - fx / 2;
        offY = resY / 2 - fy / 2;
        
        // Variable picks monitor number
        int m = 1;
        
        frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(fx, fy)); 
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        GraphicsConfiguration[] gc = gd[m].getConfigurations();
        
        for (int i = 0; i < gc.length; i++) {
            Rectangle gcBounds = gc[i].getBounds();
            int xoffs = (int) gcBounds.x;
            int yoffs = (int) gcBounds.y;
            frame.setLocation(xoffs/2-offX, offY);
        }
    }
}