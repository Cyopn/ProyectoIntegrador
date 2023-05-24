package proyectointegrador;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;

public class Panels {

    Buttons b = new Buttons();
    Functions f = new Functions();

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

    public void pseudoPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(500, 500);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Numeros Pseudoaleatorios");
        JLabel lxo = new JLabel("Xo");
        JTextField txo = new JTextField();
        ((AbstractDocument) txo.getDocument()).setDocumentFilter(new validator());
        JLabel ld = new JLabel("Digitos");
        JSpinner sd = new JSpinner(new SpinnerNumberModel(4, 1, 1000, 1));
        JLabel lc = new JLabel("Cantidad");
        JSpinner sc = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        JButton btn = new JButton("Generar");
        Font fo = new Font("Arial", Font.BOLD, 12);
        JTable t = new JTable();
        JScrollPane sp = new JScrollPane(t);
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());

        t.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Vuelta", "Xo", "Xo2", "Centro", "Ri"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                b.npBtn(e, fr, t, txo, sd, sc);
            }
        });
        lbl.setBounds((fr.getWidth() - 210) / 2, 10, 200, 22);
        lxo.setFont(fo);
        lxo.setBounds(63, 35, 75, 22);
        txo.setBounds(35, 60, 75, 22);
        ld.setBounds(138, 35, 50, 22);
        sd.setBounds(130, 60, 50, 22);
        lc.setBounds(238, 35, 50, 22);
        sc.setBounds(238, 60, 50, 22);
        btn.setBounds((fr.getWidth() - 100) / 2, 320, 90, 22);
        sp.setBounds(8, 90, 450, 200);

        p.add(lbl);
        p.add(txo);
        p.add(lxo);
        p.add(ld);
        p.add(sd);
        p.add(lc);
        p.add(sc);
        p.add(btn);
        p.add(sp);

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

    private class validator extends DocumentFilter {

        private final Pattern regexCheck = Pattern.compile("[0-9]+");

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            if (regexCheck.matcher(str).matches()) {
                super.insertString(fb, offs, str, a);
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String str, AttributeSet attrs)
                throws BadLocationException {
            if (str == null) {
                return;
            }
            if (regexCheck.matcher(str).matches()) {
                fb.replace(offset, length, str, attrs);
            }
        }
    }
}
