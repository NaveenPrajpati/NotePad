import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class NotePad extends JFrame implements ActionListener {

    JMenu menu, submenu, menu2, submenu2, menu3;
    JMenuItem m1, m2, m3, m4, m5, m6, m7, m8,m9, ms1, ms2, ms3, ms4;
    JScrollPane jScrollPane;
    JTextArea jTextArea;

    public NotePad() {
        // getContentPane().setBackground(Color.CYAN);
        setLayout(null);
        JMenuBar jMenuBar = new JMenuBar();

        menu = new JMenu("File");
        m1 = new JMenuItem("New");
        m1.addActionListener(this);
        m2 = new JMenuItem("New window");
        m2.addActionListener(this);
        m3 = new JMenuItem("Open");
        m3.addActionListener(this);

        submenu = new JMenu("Save");
        ms1 = new JMenuItem("Save");
        ms1.addActionListener(this);

        ms2 = new JMenuItem("Save as");
        ms2.addActionListener(this);
        submenu.add(ms1);
        submenu.add(ms2);

        m5 = new JMenuItem("Print");
        m5.addActionListener(this);
        m6 = new JMenuItem("Exit");
        m6.addActionListener(this);
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        menu.add(submenu);
        menu.add(m5);
        menu.add(m6);
        jMenuBar.add(menu);


        menu2 = new JMenu("Edit");
        m4 = new JMenuItem("Undo");
        m7 = new JMenuItem("Find");
        m8 =new JMenuItem("Font");
        m8.addActionListener(this);

        menu2.add(m4);
        menu2.add(m7);
        menu2.add(m8);
        jMenuBar.add(menu2);

        menu3 = new JMenu("View");
        m9 = new JMenuItem("Status bar");
        menu3.add(m9);
        jMenuBar.add(menu3);

        add(jMenuBar);
        setJMenuBar(jMenuBar);

        jTextArea = new JTextArea();
        jTextArea.setBackground(Color.white);
        jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setBounds(0, 0, 600, 450);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(jScrollPane);


        setSize(600, 500);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NotePad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ms1) {
            File file = new File("new.txt");
            try {
                FileWriter fileWriter = new FileWriter(file);
                String st = jTextArea.getText();
                fileWriter.write(st);
                fileWriter.close();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource()==ms2) {
            JFileChooser jFileChooser = new JFileChooser("f:");
            // Invoke the showsSaveDialog function to show the save dialog
            int r = jFileChooser.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try {
                    FileWriter wr = new FileWriter(fi, false);
                    BufferedWriter w = new BufferedWriter(wr);
                    w.write(jTextArea.getText());
                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(this, evt.getMessage());
                }
            } else
                JOptionPane.showMessageDialog(this, "you cancelled the operation");

        } else if (e.getSource() == m1) {
            jTextArea.setText("");
        } else if (e.getSource() == m2) {
            new NotePad();
        } else if (e.getSource() == m3) {
            JFileChooser jFileChooser=new JFileChooser("D:");
            // Invoke the showsOpenDialog function to show the save dialog
            int r = jFileChooser.showOpenDialog(null);
            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try {
                    String s1 = "", sl = "";
                    FileReader fr = new FileReader(fi);
                    BufferedReader br = new BufferedReader(fr);
                    // Initialize sl
                    sl = br.readLine();
                    // Take the input from the file
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }
                    jTextArea.setText(sl);
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(this, evt.getMessage());
                }
            }
            else
                JOptionPane.showMessageDialog(this, "you cancelled the operation");
        } else if (e.getSource() == m5) {
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == m6) {
          int n=JOptionPane.showConfirmDialog(this,"are you sure");
          if(n==JOptionPane.YES_OPTION)
             System.exit(0);

        } else if(e.getSource()==m8){

        }
    }
}
