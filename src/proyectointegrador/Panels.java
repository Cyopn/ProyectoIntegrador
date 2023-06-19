package proyectointegrador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeriesCollection;
import utils.TextPrompt;

public class Panels {

    Functions f = new Functions("");

    public void initPanel(JFrame fr, JMenuBar mb) {
        JPanel p = new JPanel();
        p.setLayout(null);
        JLabel lbl = new JLabel("Bienvenido");
        JLabel lp = new JLabel("Proyecto integrador");
        JLabel l1 = new JLabel("Juan Antonio Calvillo Benitez");
        JLabel l2 = new JLabel("Castillo Pérez Elizabeth Guadalupe ");
        JLabel l3 = new JLabel("Hernández Gutiérrez Daniela ");
        JLabel l4 = new JLabel("Espinoza López Jonathan");
        JLabel l5 = new JLabel("Islas Pacheco Jesús Alberto");
        JLabel ld = new JLabel("Docente");
        JLabel ln = new JLabel("Ramírez Hidalgo Juan Alberto");

        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        Font f = new Font("Arial", Font.PLAIN, 14);
        lbl.setFont(new Font("Arial", Font.PLAIN, 22));
        lp.setFont(new Font("Arial", Font.PLAIN, 18));
        ld.setFont(new Font("Arial", Font.PLAIN, 18));
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
        ln.setFont(f);
        ld.setHorizontalAlignment(SwingConstants.RIGHT);
        ln.setHorizontalAlignment(SwingConstants.RIGHT);

        lbl.setBounds(190, 15, 300, 25);
        lp.setBounds(40, 45, 200, 25);
        l1.setBounds(50, 70, 300, 25);
        l2.setBounds(50, 95, 300, 25);
        l3.setBounds(50, 120, 300, 25);
        l4.setBounds(50, 145, 300, 25);
        l5.setBounds(50, 170, 300, 25);
        ld.setBounds(250, 275, 200, 25);
        ln.setBounds(250, 300, 200, 25);
        lbl.setBackground(Color.red);
        p.add(lbl);
        p.add(lp);
        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(ld);
        p.add(ln);
        fr.add(p);
    }

    public void integralPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(500, 400);
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
        lbl3.setBounds((fr.getWidth() - 210) / 2, 200, 200, 22);
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
        fr.setSize(500, 400);
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
        lbl3.setBounds((fr.getWidth() - 210) / 2, 200, 200, 22);
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
        t.getTableHeader().setReorderingAllowed(false);
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
        fr.setSize(500, 400);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Chi-cuadrada");
        JTextField th = new JTextField();
        ((AbstractDocument) th.getDocument()).setDocumentFilter(new validator());
        JTextField tm = new JTextField();
        ((AbstractDocument) tm.getDocument()).setDocumentFilter(new validator());
        JButton btn = new JButton("Generar");
        JTable t = new JTable();
        JScrollPane sp = new JScrollPane(t);
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        t.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Intervalo", "O", "E", "E^2"
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
        int[] o = new int[11];
        List<String> in = new ArrayList<>();
        List<String> ls = f.loadNp(fr, mb);
        int e = Integer.parseInt(((ls.size() / 10) + "").replaceAll("\\..*", ""));
        DefaultTableModel mt = (DefaultTableModel) t.getModel();
        double x = 0;
        DecimalFormat df = new DecimalFormat("#.0");
        int id = 0;
        int io = 0;
        while (x < 0.9) {
            for (String r : ls) {
                if (Double.parseDouble(r) >= Double.parseDouble(df.format((x))) && Double.parseDouble(r) <= Double.parseDouble(df.format((x + 0.1)))) {
                    io++;
                    o[id] = io;
                }
            }
            id++;
            io = 0;
            in.add(df.format(x) + " - " + df.format((x + 0.1)));
            x = x + 0.1;
        }
        id = 0;
        for (String r : in) {
            Object[] ro = {r, o[id], e, ""};
            id++;
            mt.addRow(ro);
        }
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Buttons b = new Buttons();
                b.chiBtn(e, fr, t);
            }
        });
        t.getTableHeader().setReorderingAllowed(false);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl.setBounds((fr.getWidth() - 210) / 2, 10, 200, 22);
        btn.setBounds((fr.getWidth() - 100) / 2, 280, 90, 22);
        sp.setBounds(18, 55, 450, 183);
        p.add(lbl);
        p.add(btn);
        p.add(sp);
        fr.add(p);
        fr.revalidate();
        fr.repaint();
        fr.setLocationRelativeTo(null);
    }

    public void mediaPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(500, 410);
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
        t.getTableHeader().setReorderingAllowed(false);
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
        sa.setBounds(435, 45, 50, 22);
        sb.setBounds(580, 45, 50, 22);
        btn.setBounds(50, 85, 100, 22);
        g.setBounds(0, 120, 684, 418);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Buttons b = new Buttons();
                b.aBtn(e, fr, txt, sa, sb, lbl3, d);
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

    public void skPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(500, 430);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Smirnov-Kolmogorov");
        JLabel dp = new JLabel("D+=");
        JLabel dn = new JLabel("D-=");

        JTable t = new JTable();
        JScrollPane sp = new JScrollPane(t);
        JButton b = new JButton("Generar");
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        t.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "i", "ri", "i/n", "(i-1)/n", "(i/n)-ri", "ri-(i-1)/n"}
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        t.getTableHeader().setReorderingAllowed(false);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl.setBounds((fr.getWidth() - 210) / 2, 10, 200, 22);
        dp.setBounds((fr.getWidth() - 210) / 3, 265, 200, 22);
        dn.setBounds((fr.getWidth() - 210), 265, 200, 22);

        sp.setBounds(18, 50, 450, 200);
        b.setBounds((fr.getWidth() - 90) / 2, 310, 80, 22);
        DefaultTableModel mt = (DefaultTableModel) t.getModel();
        int x = 1;
        List<String> l = f.loadNp(fr, mb);
        Collections.sort(l);
        for (String r : l) {
            Object[] ro = {x, r};
            x++;
            mt.addRow(ro);
        }
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Buttons b = new Buttons();
                b.skBtn(e, fr, t, dp, dn);
            }
        });
        p.add(lbl);
        p.add(dp);
        p.add(dn);
        p.add(sp);
        p.add(b);
        fr.add(p);
        fr.revalidate();
        fr.repaint();
        fr.setLocationRelativeTo(null);
    }

    public void funPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(700, 600);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Grafica de funciones");
        JLabel lbl2 = new JLabel("Escribe tu funcion aqui:");
        JLabel lbla = new JLabel("Intervalo");
        JLabel lblb = new JLabel("Intervalo");
        JTextField txt = new JTextField(25);
        JSpinner sa = new JSpinner(new SpinnerNumberModel(-5, -1000, 1000, 1));
        JSpinner sb = new JSpinner(new SpinnerNumberModel(5, -1000, 1000, 1));
        JButton btn = new JButton("Graficar");
        XYSeriesCollection d = new XYSeriesCollection();
        JPanel g = new ChartPanel(ChartFactory.createXYLineChart("Grafica de funciones", "x", "y", d));
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        lbl.setFont(new Font("Arial", Font.PLAIN, 18));
        lbl2.setFont(new Font("Arial", Font.PLAIN, 16));
        lbla.setFont(new Font("Arial", Font.PLAIN, 16));
        lblb.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl.setBounds((fr.getWidth() - 310) / 2, 10, 300, 22);
        lbl2.setBounds(35, 45, 200, 22);
        lbla.setBounds(370, 45, 75, 22);
        lblb.setBounds(520, 45, 75, 22);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setBounds(200, 45, 150, 22);
        sa.setBounds(435, 45, 50, 22);
        sb.setBounds(580, 45, 50, 22);
        btn.setBounds(50, 85, 100, 22);
        g.setBounds(0, 120, 684, 418);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Buttons b = new Buttons();
                b.grBtn(e, fr, txt, sa, sb, d);
            }
        });

        p.add(lbl);
        p.add(lbl2);
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

    public void optPanel(JFrame fr, JMenuBar mb) {
        //  fr.setSize();
    }

    public void rfPanel(JFrame fr, JMenuBar mb) {
        //  fr.setSize();
    }

    public void intPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(700, 610);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Raices aproximadas: Intervalo");
        JLabel lbl2 = new JLabel("Escribe tu funcion aqui:");
        JLabel ln = new JLabel("Raiz aproximada: ");
        JLabel lp = new JLabel("Raiz aproximada: ");
        JTextField txt = new JTextField(25);
        JButton btn = new JButton("Obtener");
        JButton btg = new JButton("Grafica");
        JTable tn = new JTable();
        JScrollPane spn = new JScrollPane(tn);
        JTable tp = new JTable();
        JScrollPane spp = new JScrollPane(tp);
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        tn.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Vuelta", "a", "b", "f(a)", "f(b)", "Xo", "f(Xo)", "f(a)*f(Xo)"}
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tp.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Vuelta", "a", "b", "f(a)", "f(b)", "Xo", "f(Xo)", "f(a)*f(Xo)"}
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
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
        lbl.setFont(new Font("Arial", Font.PLAIN, 18));
        lbl2.setFont(new Font("Arial", Font.PLAIN, 16));
        ln.setFont(new Font("Arial", Font.PLAIN, 14));
        lp.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl.setBounds((fr.getWidth() - 310) / 2, 10, 300, 22);
        lbl2.setBounds(35, 45, 200, 22);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setBounds(200, 45, 150, 22);
        btn.setBounds((fr.getWidth() - 250) / 3, 85, 100, 22);
        btg.setBounds((fr.getWidth() - 250), 85, 100, 22);
        tn.getTableHeader().setReorderingAllowed(false);
        spn.setBounds(18, 140, 650, 180);
        ln.setBounds(18, 112, 200, 22);
        lp.setBounds(18, 322, 200, 22);
        spp.setBounds(18, 350, 650, 180);
        btg.setEnabled(false);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Buttons().bisBtn(e, fr, txt, tn, tp, btg, ln, lp);
            }
        });
        btg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Buttons().grahpBtn(e, fr, txt, tn, tp);
            }
        });
        TextPrompt txp = new TextPrompt("Ax2+Bx+C", txt);
        txp.changeAlpha(0.7f);
        p.add(lbl2);
        p.add(btn);
        p.add(btg);
        p.add(txt);
        p.add(lbl);
        p.add(ln);
        p.add(lp);
        p.add(spn);
        p.add(spp);
        fr.add(p);
        fr.revalidate();
        fr.repaint();
        fr.setLocationRelativeTo(null);
    }

    public void bisPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(700, 610);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Raices aproximadas: Biseccion");
        JLabel lbl2 = new JLabel("Escribe tu funcion aqui:");
        JLabel ln = new JLabel("Raiz aproximada: ");
        JLabel lp = new JLabel("Raiz aproximada: ");
        JTextField txt = new JTextField(25);
        JButton btn = new JButton("Obtener");
        JButton btg = new JButton("Grafica");
        JTable tn = new JTable();
        JScrollPane spn = new JScrollPane(tn);
        JTable tp = new JTable();
        JScrollPane spp = new JScrollPane(tp);
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        tn.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Vuelta", "a", "b", "f(a)", "f(b)", "Xo", "f(Xo)", "f(a)*f(Xo)"}
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tp.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Vuelta", "a", "b", "f(a)", "f(b)", "Xo", "f(Xo)", "f(a)*f(Xo)"}
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
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
        lbl.setFont(new Font("Arial", Font.PLAIN, 18));
        lbl2.setFont(new Font("Arial", Font.PLAIN, 16));
        ln.setFont(new Font("Arial", Font.PLAIN, 14));
        lp.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl.setBounds((fr.getWidth() - 310) / 2, 10, 300, 22);
        lbl2.setBounds(35, 45, 200, 22);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setBounds(200, 45, 150, 22);
        btn.setBounds((fr.getWidth() - 250) / 3, 85, 100, 22);
        btg.setBounds((fr.getWidth() - 250), 85, 100, 22);
        tn.getTableHeader().setReorderingAllowed(false);
        spn.setBounds(18, 140, 650, 180);
        ln.setBounds(18, 112, 200, 22);
        lp.setBounds(18, 322, 200, 22);
        spp.setBounds(18, 350, 650, 180);
        btg.setEnabled(false);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Buttons().bisBtn(e, fr, txt, tn, tp, btg, ln, lp);
            }
        });
        btg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Buttons().grahpBtn(e, fr, txt, tn, tp);
            }
        });
        TextPrompt txp = new TextPrompt("Ax2+Bx+C", txt);
        txp.changeAlpha(0.7f);
        p.add(lbl2);
        p.add(btn);
        p.add(btg);
        p.add(txt);
        p.add(lbl);
        p.add(ln);
        p.add(lp);
        p.add(spn);
        p.add(spp);
        fr.add(p);
        fr.revalidate();
        fr.repaint();
        fr.setLocationRelativeTo(null);
    }

    public void masPanel(JFrame fr, JMenuBar mb) {
        fr.setSize(500, 300);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel("Sistemas de ecuaciones");
        JLabel lbl2 = new JLabel("Escribe tus ecuaciones aqui:");
        JTextField t1 = new JTextField(25);
        JTextField t2 = new JTextField(25);
        JTextField t3 = new JTextField(25);
        JButton btn = new JButton("Calcular");
        p.setLayout(null);
        p.setSize(fr.getWidth(), fr.getHeight() - mb.getHeight());
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl2.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl.setBounds((fr.getWidth() - 200) / 2, 10, 300, 22);
        lbl2.setBounds(50, 45, 200, 22);
        t1.setBounds((fr.getWidth() - 170) / 2, 80, 150, 22);
        t2.setBounds((fr.getWidth() - 170) / 2, 110, 150, 22);
        t3.setBounds((fr.getWidth() - 170) / 2, 140, 150, 22);
        btn.setBounds((fr.getWidth() - 110) / 2, 185, 100, 22);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Buttons().masBtn(e, fr, t1, t2, t3);
            }
        });
        p.add(lbl);
        p.add(lbl2);
        p.add(t1);
        p.add(t2);
        p.add(t3);
        p.add(btn);
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
