package proyectointegrador;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeriesCollection;

public class Panels {

    Functions f = new Functions("");

    public void initPanel(JFrame fr, JMenuBar mb) {
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Bienvenido");
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl.setBounds((fr.getWidth() - 210) / 2, 200, 200, 22);
        p.add(lbl);
        fr.add(p);
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
        lbl3.setHorizontalAlignment(SwingConstants.CENTER);
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
        fr.setLocationRelativeTo(null);
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
        lbl3.setHorizontalAlignment(SwingConstants.CENTER);
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
        fr.setLocationRelativeTo(null);
    }

    public void pseudoPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(500, 450);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Numeros Pseudoaleatorios");
        JLabel lxo = new JLabel("Xo");
        JTextField txo = new JTextField();
        ((AbstractDocument) txo.getDocument()).setDocumentFilter(new validator());
        JLabel ld = new JLabel("Digitos");
        JSpinner sd = new JSpinner(new SpinnerNumberModel(4, 1, 1000, 2));
        JLabel lc = new JLabel("Cantidad");
        JSpinner sc = new JSpinner(new SpinnerNumberModel(2, 2, 1000, 1));
        JButton btn = new JButton("Generar");
        JButton sb = new JButton("Guardar");
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
                Buttons b = new Buttons();
                b.npBtn(e, fr, t, txo, sd, sc);
            }
        });
        sb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Buttons b = new Buttons();
                    b.saveNp(e, fr, t);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        });
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl.setBounds((fr.getWidth() - 310) / 2, 10, 300, 22);
        lxo.setFont(fo);
        lxo.setBounds(63, 35, 75, 22);
        txo.setBounds(35, 60, 75, 22);
        ld.setBounds(138, 35, 50, 22);
        sd.setBounds(130, 60, 50, 22);
        lc.setBounds(238, 35, 50, 22);
        sc.setBounds(238, 60, 50, 22);
        btn.setBounds((fr.getWidth() - 200) / 3, 320, 90, 22);
        sb.setBounds(((fr.getWidth() - 200) / 3) * 3, 320, 90, 22);
        sp.setBounds(18, 90, 450, 200);
        p.add(lbl);
        p.add(txo);
        p.add(lxo);
        p.add(ld);
        p.add(sd);
        p.add(lc);
        p.add(sc);
        p.add(btn);
        p.add(sb);
        p.add(sp);
        fr.add(p);
        fr.revalidate();
        fr.repaint();
        fr.setLocationRelativeTo(null);
    }

    public void chiPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(500, 450);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Chi-cuadrada");
        JLabel lc = new JLabel("Categoria");
        JLabel lh = new JLabel("Categoria");
        JLabel lm = new JLabel("Categoria");
        JTextField tc = new JTextField();
        JTextField th = new JTextField();
        ((AbstractDocument) th.getDocument()).setDocumentFilter(new validator());
        JTextField tm = new JTextField();
        ((AbstractDocument) tm.getDocument()).setDocumentFilter(new validator());
        JButton btn = new JButton("Generar");
        JButton sb = new JButton("Guardar");
        Font fo = new Font("Arial", Font.BOLD, 12);
        JTable t = new JTable();
        JScrollPane sp = new JScrollPane(t);
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        t.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Categoria", "Hombre", "Mujer", "Total"
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
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl.setBounds((fr.getWidth() - 210) / 2, 10, 200, 22);
        lc.setFont(fo);
        lc.setBounds(63, 35, 75, 22);
        btn.setBounds((fr.getWidth() - 200) / 3, 320, 90, 22);
        sb.setBounds(((fr.getWidth() - 200) / 3) * 3, 320, 90, 22);
        sp.setBounds(18, 90, 450, 200);
        p.add(lbl);
        p.add(btn);
        p.add(sb);
        p.add(sp);
        fr.add(p);
        fr.revalidate();
        fr.repaint();
        fr.setLocationRelativeTo(null);
    }

    public void mediaPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(500, 450);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Media Movil");
        JTable t = new JTable();
        JScrollPane sp = new JScrollPane(t);
        JButton b = new JButton("Generar");
        JButton bt = new JButton("Grafica");
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        t.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Vuelta", "Numero", "Media Movil",}
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

        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl.setBounds((fr.getWidth() - 210) / 2, 10, 200, 22);
        sp.setBounds(18, 50, 450, 200);
        b.setBounds(80, 290, 80, 22);
        bt.setBounds(280, 290, 80, 22);
        DefaultTableModel mt = (DefaultTableModel) t.getModel();
        int x = 1;
        for (String r : f.loadNp(fr, mb)) {
            Object[] ro = {x, r, ""};
            x++;
            mt.addRow(ro);
        }
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Buttons b = new Buttons();
                b.mediaBtn(e, fr, t);
            }
        });
        bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Buttons b = new Buttons();
                b.showBtn(e, fr, t);
            }
        });
        p.add(lbl);
        p.add(sp);
        p.add(b);
        p.add(bt);
        fr.add(p);
        fr.revalidate();
        fr.repaint();
        fr.setLocationRelativeTo(null);
    }

    public void areaPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(700, 600);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Calculo de areas bajo funciones");
        JLabel lbl2 = new JLabel("Escribe tu funcion aqui:");
        JLabel lbl3 = new JLabel("Resultado: ");
        JLabel lbla = new JLabel("Limite a");
        JLabel lblb = new JLabel("Limite b");
        JTextField txt = new JTextField(25);
        JSpinner sa = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
        JSpinner sb = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
        JButton btn = new JButton("Calcular");
        XYSeriesCollection d = new XYSeriesCollection();
        JPanel g = new ChartPanel(ChartFactory.createXYAreaChart("Area", "x", "y", d));
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        lbl.setFont(new Font("Arial", Font.PLAIN, 18));
        lbl2.setFont(new Font("Arial", Font.PLAIN, 16));
        lbla.setFont(new Font("Arial", Font.PLAIN, 16));
        lblb.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl3.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl.setBounds((fr.getWidth() - 310) / 2, 10, 300, 22);
        lbl2.setBounds(35, 45, 200, 22);
        lbla.setBounds(370, 45, 75, 22);
        lblb.setBounds(520, 45, 75, 22);
        lbl3.setBounds(180, 85, 400, 22);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setBounds(200, 45, 150, 22);
        sa.setBounds(450, 45, 50, 22);
        sb.setBounds(580, 45, 50, 22);
        btn.setBounds(50, 85, 100, 22);
        g.setBounds(0, 120, 684, 418);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Buttons b = new Buttons();
                b.aBtn(e, fr, txt, sa, sb, lbl3, p, d);
            }
        });

        p.add(lbl);
        p.add(lbl2);
        p.add(lbl3);
        p.add(lbla);
        p.add(lblb);
        p.add(txt);
        p.add(sa);
        p.add(sb);
        p.add(btn);
        p.add(g);
        fr.add(p);
        fr.revalidate();
        fr.repaint();
        fr.setLocationRelativeTo(null);
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
