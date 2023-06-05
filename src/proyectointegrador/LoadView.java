package proyectointegrador;

import java.awt.*;
import javax.swing.*;

public class LoadView extends Frame {

    JLabel l = new JLabel();
    ImageIcon im = new ImageIcon("bg.jpeg");
    JLabel t = new JLabel("Proyecto Integrador");

    JProgressBar p = new JProgressBar();

    public LoadView() {
        this.setLayout(null);
        this.setTitle("Inciando Proyecto Integrador");
        this.setUndecorated(true);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(46, 144, 232));

        l.setIcon(im);
        l.setBounds(0, 0, 400, 300);

        t.setFont(new Font("Arial", Font.BOLD, 18));
        t.setBounds(25, 25, 350, 25);
        t.setForeground(new Color(35, 43, 46));

        p.setBounds(25, 250, 350, 5);
        p.setBorderPainted(true);
        p.setBackground(new Color(175, 198, 204));
        p.setForeground(new Color(121, 141, 144));
        p.setValue(0);

        this.add(t);
        this.add(p);

        this.add(l);

        this.setVisible(true);
        runBar();
    }

    public void runBar() {
        int i = 0;

        while (i <= 100) {
            try {
                Thread.sleep(50);
                p.setValue(i);
                i++;
                if (i == 100) {
                    this.dispose();
                    new ProyectoIntegrador();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
