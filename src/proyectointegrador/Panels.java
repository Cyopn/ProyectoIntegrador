package proyectointegrador;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

public class Panels {

    public void initPanel(JFrame fr) {
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Bienvenido");
    }

    public void integralPanel(JFrame fr, JMenuBar mb) {
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Calculo de integrales");
        JLabel lbl2 = new JLabel("Escribe tu funcion aqui:");
        JLabel lbl3 = new JLabel();
        JTextField txt = new JTextField(25);
        JButton btn = new JButton("Calcular");
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl2.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl3.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl.setBounds((fr.getWidth() - 210) / 2, 10, 200, 22);
        lbl2.setBounds(75, 45, 200, 22);
        lbl3.setBounds((fr.getWidth() - 210) / 2, 225, 200, 22);
        txt.setBounds((fr.getWidth() - 160) / 2, 80, 150, 22);
        btn.setBounds((fr.getWidth() - 110) / 2, 120, 100, 22);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Buttons b = new Buttons();
                b.inBtn(e, fr, txt, lbl3);
            }
        });
        p.add(lbl);
        p.add(lbl2);
        p.add(lbl3);
        p.add(txt);
        p.add(btn);
        fr.add(p);

        fr.revalidate();
        fr.repaint();
    }

    public void diferencialPanel(JFrame fr, JMenuBar mb) {
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Calculo de derivadas");
        JLabel lbl2 = new JLabel("Escribe tu funcion aqui:");
        JLabel lbl3 = new JLabel();
        JTextField txt = new JTextField(25);
        JButton btn = new JButton("Calcular");
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl2.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl3.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl.setBounds((fr.getWidth() - 210) / 2, 10, 200, 22);
        lbl2.setBounds(75, 45, 200, 22);
        lbl3.setBounds((fr.getWidth() - 210) / 2, 225, 200, 22);
        txt.setBounds((fr.getWidth() - 160) / 2, 80, 150, 22);
        btn.setBounds((fr.getWidth() - 110) / 2, 120, 100, 22);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Buttons b = new Buttons();
                b.diBtn(e, fr, txt, lbl3);
            }
        });
        p.add(lbl);
        p.add(lbl2);
        p.add(lbl3);
        p.add(txt);
        p.add(btn);
        fr.add(p);
        fr.revalidate();
        fr.repaint();
    }

    public void cleanPanel(JFrame fr) {
        List<Component> c = Arrays.asList(fr.getContentPane().getComponents());
        for (Component n : c) {
            if (n.toString().contains("JPanel")) {
                fr.remove(n);
                fr.revalidate();
                fr.repaint();
            }
        }
    }
}
