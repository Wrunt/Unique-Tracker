package poe.unique.tracker;
import java.awt.*;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.joda.time.LocalDate;

public class MainPanel extends JPanel {
    public JPanel controls1, controls1a, controls1b, controls2, main;
    public JLabel spacer;
    public JButton save, load, add;
    public JComboBox base, type, hands, item;
    public JTextArea list;
    public JScrollPane scroll;
    public ArrayList lines1, lines2;
    public int newUnique;
    
    @SuppressWarnings("empty-statement")
    public MainPanel() {        
        load = new JButton("Load List");
        LoadListener ll = new LoadListener();
        load.addActionListener(ll);
        save = new JButton("Save List");
        SaveListener sl = new SaveListener();
        save.addActionListener(sl);
        add = new JButton("Add Unique");
        AddListener al = new AddListener();
        add.addActionListener(al);
        add.setEnabled(false);
        add.setAlignmentX(RIGHT_ALIGNMENT);
        
        list = new JTextArea("");
        list.setMinimumSize(new Dimension(775, 300));
        list.setEditable(false);
        
        scroll = new JScrollPane(list);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(775, 250));
        
        try {
            Scanner sc = new Scanner(new File("data\\log.txt"));
            lines1 = new ArrayList<String>();
            while (sc.hasNextLine()) {
                lines1.add(sc.nextLine());
            }    
            list.setText("");
            for(int i = 0; i < lines1.size(); i++) {
                list.append((String) lines1.get(i));
                list.append("\r\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] bBox = {"Pick One", "Accessories", "Armor", "Weapons", "Flasks", "Maps"};
        base = new JComboBox(bBox);
        base.setEditable(false);
        base.setSelectedIndex(0);
        ComboListenerA cla = new ComboListenerA();
        base.addActionListener(cla);
        
        String[] hBox = {"Pick One", "One-Handed", "Two-Handed"};
        hands = new JComboBox(hBox);
        hands.setEditable(false);
        hands.setVisible(false);
        hands.setSelectedIndex(0);
        ComboListenerB clb = new ComboListenerB();
        hands.addActionListener(clb);
        hands.setEnabled(false);
        
        String[] tBox = {"Pick One"};
        type = new JComboBox(tBox);
        type.setEditable(false);
        type.setVisible(false);
        type.setSelectedIndex(0);
        ComboListenerC clc = new ComboListenerC();
        type.addActionListener(clc);
        type.setEnabled(false);
        
        String[] iBox = {"Pick One"};
        item = new JComboBox(iBox);
        item.setEditable(false);
        item.setVisible(false);
        item.setSelectedIndex(0);
        ComboListenerD cld = new ComboListenerD();
        item.addActionListener(cld);
        item.setEnabled(false);
        
        spacer = new JLabel("                           ");
                
        controls1a = new JPanel();
        controls1a.setMinimumSize(new Dimension(240 ,load.getPreferredSize().height));
        controls1a.add(load);
        
        controls1b = new JPanel();
        controls1b.add(save);
        controls1b.setMinimumSize(new Dimension(240 ,load.getPreferredSize().height));
        
        controls1 = new JPanel();
        controls1.setMinimumSize(new Dimension(480 ,load.getPreferredSize().height));
        controls1.add(controls1a);
        controls1.add(controls1b);
        
        controls2 = new JPanel();
        controls1.setMinimumSize(new Dimension(475 ,load.getPreferredSize().height));
        controls2.add(base);
        controls2.add(hands);
        controls2.add(type);
        controls2.add(item);
        controls2.add(spacer);
        controls2.add(add);
                
        main = new JPanel();
        BoxLayout layout = new BoxLayout (main, BoxLayout.Y_AXIS);
        main.setLayout(layout);
        main.add(controls1);
        main.add(scroll);
        main.add(controls2);
        add(main);
    }
    
    private class LoadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Scanner sc = new Scanner(new File("data\\log.txt"));
                lines1 = new ArrayList<String>();
                while (sc.hasNextLine()) {
                    lines1.add(sc.nextLine());
                }
                
                list.setText("");
                for(int i = 0; i < lines1.size(); i++) {
                    list.append((String) lines1.get(i));
                    list.append("\r\n");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }
    
    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter("data\\log.txt"));
                for (int i = 0; i < lines1.size(); i++) {
                    writer.write((String) lines1.get(i));
                    writer.write("\r\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private class ComboListenerA implements ActionListener {        
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] tBox1 = {"Pick One", "Amulet", "Belt", "Ring", "Quiver"};
            String[] tBox2 = {"Pick One", "Body Armor", "Boots", "Gloves", "Helmet", "Shield"};
            switch (base.getSelectedIndex()) {
               case 0:
                   hands.setEnabled(false);
                   type.setEnabled(false);
                   type.removeAllItems();
                   item.setEnabled(false);
                   item.removeAllItems();
                   hands.setVisible(false);
                   type.setVisible(false);
                   item.setVisible(false);
                   break;
               case 1:
                   hands.setVisible(false);
                   hands.setEnabled(false);
                   item.setEnabled(false);
                   item.setVisible(false);
                   type.removeAllItems();
                   type.setEnabled(true);
                   type.setVisible(true);
                   for (int i = 0; i < tBox1.length; i++) {
                       type.addItem(tBox1[i]);
                   }
                   System.out.println (base.getSelectedIndex());
                   break;
               case 2:   
                   type.setEnabled(true);
                   type.setVisible(true);
                   hands.setVisible(false);
                   hands.setEnabled(false);
                   item.setEnabled(false);
                   item.setVisible(false);
                   type.removeAllItems();
                   for (int i = 0; i < tBox2.length; i++) {
                       type.addItem(tBox2[i]);
                   }
                   break;
               case 3:
                   type.setEnabled(false);
                   type.setVisible(false);
                   hands.setVisible(true);
                   hands.setEnabled(true);
                   item.setEnabled(false);
                   item.setVisible(false);
                   break;
               case 4:
                   type.setEnabled(false);
                   type.setVisible(false);
                   hands.setVisible(false);
                   hands.setEnabled(false);
                   try {
                       Scanner sc = new Scanner(new File("data\\Flasks.txt"));
                       lines2 = new ArrayList<>();
                       while (sc.hasNextLine()) {
                           lines2.add(sc.nextLine());
                       }
                       
                       String[] itemListA = (String[]) lines2.toArray(new String[lines2.size()]);
                       
                       item.removeAllItems();
                       item.addItem("Pick One");
                       for (int i = 0; i < itemListA.length; i++) {
                           item.addItem(itemListA[i]);   
                       }
                       item.setEnabled(true);
                       item.setVisible(true);
                       
                   } catch (FileNotFoundException ex) {
                       Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   break;
               case 5:
                   type.setEnabled(false);
                   type.setVisible(false);
                   hands.setVisible(false);
                   hands.setEnabled(false);
                   try {
                       Scanner sc = new Scanner(new File("data\\Maps.txt"));
                       lines2 = new ArrayList<>();
                       while (sc.hasNextLine()) {
                           lines2.add(sc.nextLine());
                       }
                       
                       String[] itemListB = (String[]) lines2.toArray(new String[lines2.size()]);
                       
                       item.removeAllItems();
                       item.addItem("Pick One");
                       for (int i = 0; i < itemListB.length; i++) {
                           item.addItem(itemListB[i]);   
                       }
                       item.setEnabled(true);
                       item.setVisible(true);
                       
                   } catch (FileNotFoundException ex) {
                       Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   break;
           }
        }
    }
    
    private class ComboListenerB implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] tBox3 = {"Pick One", "Axe", "Claw", "Dagger", "Mace", "Sword", "Wand"};
            String[] tBox4 = {"Pick One", "Axe", "Bow", "Mace", "Sword", "Staff"};
            switch (hands.getSelectedIndex()) {
                case 0:
                    type.removeAllItems();
                    type.setEnabled(false);
                    type.setVisible(false);
                    item.setEnabled(false);
                    item.setVisible(false);
                    break;
                case 1:
                    type.removeAllItems();
                    for (int i = 0; i < tBox3.length; i++) {
                       type.addItem(tBox3[i]);
                    }
                    type.setEnabled(true);
                    type.setVisible(true);
                    break;
                case 2:
                    type.removeAllItems();
                    for (int i = 0; i < tBox4.length; i++) {
                       type.addItem(tBox4[i]);
                    }
                    type.setEnabled(true);
                    type.setVisible(true);
                    break;
            }
        }
    }
    
    private class ComboListenerC implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (base.getSelectedIndex()) {
               case 1:
                   try {
                       String location = "data\\" + type.getSelectedItem() + ".txt";
                       Scanner sc = new Scanner(new File(location));
                       lines2 = new ArrayList<>();
                       while (sc.hasNextLine()) {
                           lines2.add(sc.nextLine());
                       }
                       
                       String[] itemListC = (String[]) lines2.toArray(new String[lines2.size()]);
                       
                       item.removeAllItems();
                       item.addItem("Pick One");
                       for (int i = 0; i < itemListC.length; i++) {
                           item.addItem(itemListC[i]);   
                       }
                       item.setEnabled(true);
                       item.setVisible(true);
                       
                   } catch (FileNotFoundException ex) {
                       Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   break;
               case 2:
                   try {
                       String location = "data\\" + type.getSelectedItem() + ".txt";
                       Scanner sc = new Scanner(new File(location));
                       lines2 = new ArrayList<>();
                       while (sc.hasNextLine()) {
                           lines2.add(sc.nextLine());
                       }
                       
                       String[] itemListD = (String[]) lines2.toArray(new String[lines2.size()]);
                       
                       item.removeAllItems();
                       item.addItem("Pick One");
                       for (int i = 0; i < itemListD.length; i++) {
                           item.addItem(itemListD[i]);   
                       }
                       item.setEnabled(true);
                       item.setVisible(true);
                       
                   } catch (FileNotFoundException ex) {
                       Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   break;
               case 3:
                   if (hands.getSelectedIndex() == 1) {
                       try {
                           String location = "data\\1h" + type.getSelectedItem() + ".txt";
                           Scanner sc = new Scanner(new File(location));
                           lines2 = new ArrayList<>();
                           while (sc.hasNextLine()) {
                               lines2.add(sc.nextLine());
                           }
                       
                           String[] itemListE = (String[]) lines2.toArray(new String[lines2.size()]);
                       
                           item.removeAllItems();
                           item.addItem("Pick One");
                           for (int i = 0; i < itemListE.length; i++) {
                               item.addItem(itemListE[i]);   
                           }
                           item.setEnabled(true);
                           item.setVisible(true);
                       
                       } catch (FileNotFoundException ex) {
                           Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       break;
                   } else if (hands.getSelectedIndex() == 2) {
                       try {
                           String location = "data\\2h" + type.getSelectedItem() + ".txt";
                           Scanner sc = new Scanner(new File(location));
                           lines2 = new ArrayList<>();
                           while (sc.hasNextLine()) {
                               lines2.add(sc.nextLine());
                           }
                       
                           String[] itemListC = (String[]) lines2.toArray(new String[lines2.size()]);
                       
                           item.removeAllItems();
                           item.addItem("Pick One");
                           for (int i = 0; i < itemListC.length; i++) {
                               item.addItem(itemListC[i]);   
                           }
                           item.setEnabled(true);
                           item.setVisible(true);
                       
                       } catch (FileNotFoundException ex) {
                           Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                   break;
            }
            }
        }
    
    private class ComboListenerD implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           if (item.getSelectedIndex() >= 1) {
               add.setEnabled(true);
           } else {
               add.setEnabled(false);
           }
        }
    }
    
    private class AddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            LocalDate today = new LocalDate();
            
            int month = today.getMonthOfYear();
            int day = today.getDayOfMonth();
            int year = today.getYear();
            
            String date = month + "/" + day + "/" + year;
            
            String nu = date + " " + item.getSelectedItem();
            
            lines1.add(nu);
            
            list.setText(null);
            for(int i = 0; i < lines1.size(); i++) {
                list.append((String) lines1.get(i));
                list.append("\r\n");
            }
        }      
    }   
}