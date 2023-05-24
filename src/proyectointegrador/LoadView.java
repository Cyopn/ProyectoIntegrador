package proyectointegrador;

import java.awt.*;
import javax.swing.*;

public class LoadView extends Frame {

    JLabel l = new JLabel();
    JLabel t = new JLabel("Proyecto Integrador");
    JLabel m = new JLabel("Simulacion");
    JProgressBar p = new JProgressBar();

    public LoadView() {
        this.setLayout(null);
        this.setTitle("Inciando Proyecto Integrador");
        this.setUndecorated(true);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(46, 144, 232));

        t.setFont(new Font("Arial", Font.BOLD, 18));
        t.setBounds(25, 25, 350, 25);
        t.setForeground(new Color(161, 197, 53));

        m.setFont(new Font("Arial", Font.BOLD, 15));
        m.setBounds(250, 320, 200, 40);
        m.setForeground(Color.black);

        p.setBounds(25, 250, 350, 5);
        p.setBorderPainted(true);
        p.setBackground(Color.WHITE);
        p.setForeground(Color.red);
        p.setValue(0);

        this.add(t);
        this.add(m);
        this.add(p);

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
