package poe.unique.tracker;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MidScreenFrame {
    public JFrame frame;
    public int fx, fy, resX, resY, offX, offY;
    public ArrayList<String> config;
    
    public MidScreenFrame() {
        try {
            Scanner sc = new Scanner(new File("data\\config.txt"));
            config = new ArrayList<String>();
            while (sc.hasNextLine()) {
                    config.add(sc.nextLine());
            }
            
            String mon = config.get(3);
            
            int m = Integer.parseInt(mon) - 1;
            
            fx = 800;
            fy = 350;
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            resX = dim.width;
            resY = dim.height;
            
            offX = resX / 2 - fx / 2;
            offY = resY / 2 - fy / 2;
                   
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
                if (xoffs < 0) {
                    frame.setLocation(xoffs/2-offX, offY);
                } else {
                    frame.setLocation(xoffs/2+offX, offY);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MidScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}