package proyectointegrador;

import java.awt.event.MouseEvent;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.chart.*;
import org.jfree.data.xy.*;

public class Buttons {

    public void inBtn(MouseEvent e, JFrame fr, JTextField tx, JLabel lb) {
        String s = null;
        String status = null;
        Bind b = new Bind();
        b.Setter("integrate", tx.getText());
        try {
            Process p = Runtime.getRuntime().exec("py pyfunc.py");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((s = stdInput.readLine()) != null) {
                status = s;
            }
            if (status.equals("ok")) {
                Map<String, String> dict = b.Reader();
                lb.setText(dict.get("result"));

            } else {
                JOptionPane.showConfirmDialog(fr, "La funcion es invalida.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
            while ((s = stdError.readLine()) != null) {
                JOptionPane.showConfirmDialog(fr, "Ocurrio un error al ejecutar lote externo.\n" + s, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(fr, "Ocurrio un error al ejecutar lote externo.\n" + ex, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

        }
    }

    public void diBtn(MouseEvent e, JFrame fr, JTextField tx, JLabel lb) {
        String s = null;
        String status = null;
        Bind b = new Bind();
        b.Setter("derivate", tx.getText());
        try {
            Process p = Runtime.getRuntime().exec("py pyfunc.py");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((s = stdInput.readLine()) != null) {
                status = s;
            }
            if (status.equals("ok")) {
                Map<String, String> dict = b.Reader();
                lb.setText(dict.get("result"));
            } else {
                JOptionPane.showConfirmDialog(fr, "La funcion es invalida.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
            while ((s = stdError.readLine()) != null) {
                JOptionPane.showConfirmDialog(fr, "Ocurrio un error al ejecutar lote externo.\n" + s, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(fr, "Ocurrio un error al ejecutar lote externo.\n" + ex, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void npBtn(MouseEvent e, JFrame fr, JTable t, JTextField f, JSpinner s, JSpinner q) {
        Functions fu = new Functions("");
        fu.cleanTable(t);
        if (f.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(fr, "Ingresa una raiz.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        } else {
            int xo = Integer.parseInt(f.getText());
            int d = Integer.parseInt(s.getValue().toString());
            if (xo == 0) {
                JOptionPane.showConfirmDialog(fr, "La raiz debe de ser mayor a cero.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            } else {
                int v = 1;
                int r = Integer.parseInt(q.getValue().toString());
                DefaultTableModel to = (DefaultTableModel) t.getModel();
                while (v <= r) {
                    String[] rs = fu.generatePs(xo, v, d);
                    if (Integer.parseInt(rs[2]) != 0) {
                        Object[] ro = {v, rs[0], rs[1], rs[2], "0." + rs[2]};
                        to.addRow(ro);
                        xo = Integer.parseInt(rs[2]);
                    } else {
                        JOptionPane.showConfirmDialog(fr, "Se llega al error en la vuelta " + v + ". \nA partir de este punto los demas numeros seran cero.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                        v = r + 1;
                    }
                    v++;
                }
            }
        }
    }

    public void saveNp(MouseEvent e, JFrame fr, JTable t) throws IOException {
        DefaultTableModel to = (DefaultTableModel) t.getModel();
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < to.getRowCount(); i++) {
            ls.add(to.getValueAt(i, 4).toString());
        }
        if (ls.size() > 0) {
            File f = new File("a.txt");
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter("a.txt");
            while (ls.size() > 0) {
                fw.write(ls.get(0) + "\n");
                ls.remove(0);
            }
            fw.close();
            JOptionPane.showConfirmDialog(fr, "Se guardaron con exito los numeros generados.", "Aviso", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showConfirmDialog(fr, "Primero debes generar al menos un numero pseudoaleatorio.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void mediaBtn(MouseEvent e, JFrame fr, JTable t) {
        DefaultTableModel to = (DefaultTableModel) t.getModel();
        float[] m = new float[t.getRowCount()];
        List<String> l = new ArrayList<>();
        for (int row = 0; row < to.getRowCount(); row++) {
            m[row] = Float.parseFloat(to.getValueAt(row, 1).toString());
        }
        for (int row = 0; row < to.getRowCount(); row++) {
            if (row == 0) {
                l.add("######");
            } else {
                l.add(((m[row - 1] + m[row]) / 2) + "");
            }
        }
        int x = 1;
        new Functions("").cleanTable(t);
        for (String r : l) {
            Object[] ro = {x, m[x - 1], r};
            x++;
            to.addRow(ro);
        }
        t.setModel(to);
    }

    public void showBtn(MouseEvent e, JFrame fr, JTable t) {
        TableModel to = (TableModel) t.getModel();
        double[] x = new double[t.getRowCount()];
        double[] y = new double[t.getRowCount()];
        double[] xx = new double[t.getRowCount()];
        double[] yy = new double[t.getRowCount()];
        if (to.getValueAt(0, 2).toString().length() <= 0) {
            JOptionPane.showConfirmDialog(fr, "Primero debes generar la media movil.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        } else {
            for (int row = 0; row < to.getRowCount(); row++) {
                if (to.getValueAt(row, 2).toString().contains("#")) {

                } else {
                    x[row] = row + 1;
                    y[row] = Double.parseDouble(to.getValueAt(row, 2).toString());
                }
            }
            for (int row = 0; row < to.getRowCount(); row++) {
                xx[row] = row + 1;
                yy[row] = Double.parseDouble(to.getValueAt(row, 1).toString());
            }
            new Graphic("Pronostico", x, y, "Real", xx, yy).setVisible(true);
        }
    }

    public void aBtn(MouseEvent e, JFrame fr, JTextField tx, JSpinner sa, JSpinner sb, JLabel lb, XYSeriesCollection d) {
        int a = Integer.parseInt(sa.getValue().toString());
        int b = Integer.parseInt(sb.getValue().toString());
        if (tx.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(fr, "Escribe una funcion.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        } else {
            if (a >= b) {
                JOptionPane.showConfirmDialog(fr, "Los intervalos no son validos.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            } else {
                String s = null;
                String status = null;
                Bind bd = new Bind();
                bd.Setter("integrate", tx.getText());
                try {
                    Process pr = Runtime.getRuntime().exec("py pyfunc.py");
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    BufferedReader stdError = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
                    while ((s = stdInput.readLine()) != null) {
                        status = s;
                    }
                    if (status.equals("ok")) {
                        Map<String, String> dict = bd.Reader();
                        String rs = dict.get("result");
                        Functions f = new Functions(tx.getText());
                        double r = f.area(rs.replace("**", "^"), a, b);
                        lb.setText("Resultado: " + rs + "   Area: " + r);
                        try {
                            double[] x = f.rg(a, b, 0.1);
                            double[] y = f.ev(x);
                            XYSeries ss = new XYSeries(tx.getText());
                            for (int i = 0; i < x.length; i++) {
                                ss.add(x[i], y[i]);
                            }
                            d.removeAllSeries();
                            d.addSeries(ss);
                        } catch (Exception ex) {
                            JOptionPane.showConfirmDialog(fr, "Ocurrio un error al calcular puntos de la grafica.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                            System.out.println(ex + "s");
                        }
                    } else {
                        JOptionPane.showConfirmDialog(fr, "La funcion es invalida.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                    }
                    while ((s = stdError.readLine()) != null) {
                        JOptionPane.showConfirmDialog(fr, "Ocurrio un error al ejecutar lote externo.\n" + s, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showConfirmDialog(fr, "Ocurrio un error al ejecutar lote externo.\n" + ex, "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

    public void chiBtn(MouseEvent e, JFrame fr, JTable t) {
        DefaultTableModel to = (DefaultTableModel) t.getModel();
        List<String> in = new ArrayList<>();
        List<Integer> o = new ArrayList<>();
        List<Integer> ee = new ArrayList<>();
        for (int i = 0; i < to.getRowCount(); i++) {
            in.add(to.getValueAt(i, 0).toString());
            o.add(Integer.parseInt(to.getValueAt(i, 1).toString()));
            ee.add(Integer.parseInt(to.getValueAt(i, 2).toString()));
        }
        int i = 0;
        double s = 0;
        double ss = 0;
        int so = 0;
        int se = 0;
        new Functions("").cleanTable(t);
        while (i <= in.size()) {
            if (i < in.size()) {
                s = (Math.pow((o.get(i) - ee.get(i)), 2)) / ee.get(i);
                to.addRow(new Object[]{in.get(i), o.get(i), ee.get(i), s});
                ss += s;
                so += o.get(i);
                se += ee.get(i);
            } else {
                to.addRow(new Object[]{"Total", so, se, ss});
            }

            i++;
        }
        t.setModel(to);
    }

    public void skBtn(MouseEvent e, JFrame fr, JTable t, JLabel dp, JLabel dn) {
        DefaultTableModel to = (DefaultTableModel) t.getModel();
        List<String> l = new ArrayList<>();
        List<String> ii = new ArrayList<>();
        for (int i = 0; i < to.getRowCount(); i++) {
            l.add(to.getValueAt(i, 1).toString());
            ii.add(to.getValueAt(i, 0).toString());
        }
        int x = 0;
        Collections.sort(l);
        new Functions("").cleanTable(t);
        DecimalFormat df = new DecimalFormat("#.0000000");
        for (String r : l) {
            Object[] ro = {ii.get(x), r, ((double) (x + 1) / l.size()), ((double) x / l.size()), df.format(((double) (x + 1) / l.size()) - Double.parseDouble(r)), df.format(Double.parseDouble(r) - ((double) x / l.size()))};
            x++;
            to.addRow(ro);
        }
        List<String> nri = new ArrayList<>();
        List<String> rin = new ArrayList<>();
        for (int i = 0; i < to.getRowCount(); i++) {
            nri.add(to.getValueAt(i, 4).toString());
            rin.add(to.getValueAt(i, 5).toString());
        }
        Collections.sort(nri);
        Collections.sort(rin);
        dp.setText("D+=  " + nri.get(nri.size() - 1));
        dn.setText("D-=  " + rin.get(rin.size() - 1));
        t.setModel(to);
    }

    public void grBtn(MouseEvent e, JFrame fr, JTextField tx, JSpinner sa, JSpinner sb, XYSeriesCollection d) {
        int a = Integer.parseInt(sa.getValue().toString());
        int b = Integer.parseInt(sb.getValue().toString());
        if (tx.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(fr, "Escribe una funcion.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        } else {
            if (a >= b) {
                JOptionPane.showConfirmDialog(fr, "Los intervalos no son validos.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            } else {
                Functions f = new Functions(tx.getText());
                try {
                    double[] x = f.rg(a, b, 0.1);
                    double[] y = f.ev(x);
                    XYSeries ss = new XYSeries(tx.getText());
                    for (int i = 0; i < x.length; i++) {
                        ss.add(x[i], y[i]);
                    }
                    d.removeAllSeries();
                    d.addSeries(ss);

                } catch (Exception ex) {
                    System.out.println("Error al obtener puntos " + ex);
                    JOptionPane.showConfirmDialog(fr, "Ocurrio un error al intentar graficar.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

    public void intBtn(MouseEvent e, JFrame fr, JTextField tx, JTable tn, JTable tp, JButton btn, JLabel ln, JLabel lp) {
        String a = tx.getText();
        if (!a.isEmpty()) {
            try {
                Pattern cn = Pattern.compile("[+-]");
                Map<String, Double> fn = new HashMap<String, Double>();
                String[] cu = {"d", "x", "c"};
                if (a.charAt(0) != '-') {
                    a = "+" + a;
                }
                Matcher op = cn.matcher(a);
                List<String> opp = new ArrayList<>();
                while (op.find()) {
                    opp.add(op.group());
                }
                String[] ts = cn.split(a);
                if (a.charAt(0) != '-' && ts.length > 4) {
                    JOptionPane.showConfirmDialog(fr, "El formato de la ecuacion es incorrecta.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                } else if (a.charAt(0) == '-' && ts.length <= 4) {
                    int id = 0;
                    ts[0] = ts[0].substring(1);
                    for (String i : ts) {
                        if (i.charAt(0) == 'x') {
                            i = "1x";
                        }
                        if (i.indexOf("x") != -1) {
                            i = i.substring(0, i.indexOf("x"));
                            fn.put(cu[id], Double.parseDouble("" + (op.group(id) + i)));
                        } else {
                            if (id == 1) {
                                fn.put("x", 0.0);
                                fn.put("c", Double.parseDouble(op.group(id) + i));
                                id += 1;
                            } else {
                                fn.put(cu[id], Double.parseDouble(op.group(id) + i));
                            }
                        }
                        id += 1;
                    }
                    if (id == 2) {
                        fn.put("c", 0.0);
                    }
                } else if (a.charAt(0) == '+' && ts.length <= 4) {
                    int id = 0;
                    ts = Arrays.copyOfRange(ts, 1, ts.length);
                    for (String i : ts) {
                        if (i.charAt(0) == 'x') {
                            i = "1x";
                        }
                        if (i.indexOf("x") != -1) {
                            i = i.substring(0, i.indexOf("x"));
                            fn.put(cu[id], Double.parseDouble(opp.get(id) + i));
                        } else {
                            if (id == 1) {
                                fn.put("x", 0.0);
                                fn.put("c", Double.parseDouble(opp.get(id) + i));
                                id += 1;
                            } else {
                                fn.put(cu[id], Double.parseDouble(opp.get(id) + i));
                            }
                        }
                        id += 1;
                    }
                    if (id == 2) {
                        fn.put("c", 0.0);
                    }
                } else {
                    JOptionPane.showConfirmDialog(fr, "El formato de la ecuacion es incorrecta.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                }
                DecimalFormat df = new DecimalFormat("#.00000");
                double ers = (fn.get("x") * fn.get("x")) + (-4 * fn.get("d") * fn.get("c"));
                double frs = ((-1 * fn.get("x")) + (Math.sqrt(ers))) / (2 * fn.get("d"));
                double srs = ((-1 * fn.get("x")) - (Math.sqrt(ers))) / (2 * fn.get("d"));
                double p = Math.max(Double.parseDouble(df.format(frs)), Double.parseDouble(df.format(srs)));
                double n = Math.min(Double.parseDouble(df.format(frs)), Double.parseDouble(df.format(srs)));
                double an = 0;
                while (an > n) {
                    an--;
                }
                double bn = an + 1;
                double bp = 0;
                while (bp < p) {
                    bp++;
                }
                double ap = bp - 1;
                List<Map<String, Double>> arn = new ArrayList<>();
                Map<String, Double> map = new HashMap<>();
                map.put("a", an);
                map.put("fa", Double.parseDouble(df.format((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))));
                map.put("b", bn);
                map.put("fb", Double.parseDouble(df.format((fn.get("d") * (bn * bn)) + (fn.get("x") * bn) + (fn.get("c")))));
                map.put("xo", Double.parseDouble(df.format(an + (((an - bn) * ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))) / (((fn.get("d") * (bn * bn)) + (fn.get("x") * bn) + (fn.get("c"))) - ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c"))))))));
                map.put("fxo", Double.parseDouble(df.format((fn.get("d") * ((an + (((an - bn) * ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))) / (((fn.get("d") * (bn * bn)) + (fn.get("x") * bn) + (fn.get("c"))) - ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))))) * (an + (((an - bn) * ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))) / (((fn.get("d") * (bn * bn)) + (fn.get("x") * bn) + (fn.get("c"))) - ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))))))) + (fn.get("x") * (an + (((an - bn) * ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))) / (((fn.get("d") * (bn * bn)) + (fn.get("x") * bn) + (fn.get("c"))) - ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c"))))))) + (fn.get("c")))));
                map.put("rs", Double.parseDouble(df.format(((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c"))) * ((fn.get("d") * ((an + (((an - bn) * ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))) / (((fn.get("d") * (bn * bn)) + (fn.get("x") * bn) + (fn.get("c"))) - ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))))) * (an + (((an - bn) * ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))) / (((fn.get("d") * (bn * bn)) + (fn.get("x") * bn) + (fn.get("c"))) - ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))))))) + (fn.get("x") * (an + (((an - bn) * ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))) / (((fn.get("d") * (bn * bn)) + (fn.get("x") * bn) + (fn.get("c"))) - ((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c"))))))) + (fn.get("c"))))));
                arn.add(map);
                Map<String, Double> evn = new Functions("").evalBis(an, bn, fn);
                arn.add(evn);
                while (evn.get("xo") != n && evn.get("rs") != 0) {
                    if (evn.get("rs") > 0) {
                        evn.put("a", evn.get("xo"));
                    } else {
                        evn.put("b", evn.get("xo"));
                    }
                    evn = new Functions("").evalInt(evn.get("a"), evn.get("b"), fn);
                    arn.add(evn);
                }
                List<Map<String, Double>> arp = new ArrayList<>();
                Map<String, Double> mp = new HashMap<>();
                mp.put("a", ap);
                mp.put("fa", Double.parseDouble(df.format((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))));
                mp.put("b", bp);
                mp.put("fb", Double.parseDouble(df.format((fn.get("d") * (bp * bp)) + (fn.get("x") * bp) + (fn.get("c")))));
                mp.put("xo", Double.parseDouble(df.format(ap + (((ap - bp) * ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))) / (((fn.get("d") * (bp * bp)) + (fn.get("x") * bp) + (fn.get("c"))) - ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c"))))))));
                mp.put("fxo", Double.parseDouble(df.format((fn.get("d") * ((ap + (((ap - bp) * ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))) / (((fn.get("d") * (bp * bp)) + (fn.get("x") * bp) + (fn.get("c"))) - ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))))) * (ap + (((ap - bp) * ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))) / (((fn.get("d") * (bp * bp)) + (fn.get("x") * bp) + (fn.get("c"))) - ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))))))) + (fn.get("x") * (ap + (((ap - bp) * ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))) / (((fn.get("d") * (bp * bp)) + (fn.get("x") * bp) + (fn.get("c"))) - ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c"))))))) + (fn.get("c")))));
                mp.put("rs", Double.parseDouble(df.format(((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c"))) * ((fn.get("d") * ((ap + (((ap - bp) * ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))) / (((fn.get("d") * (bp * bp)) + (fn.get("x") * bp) + (fn.get("c"))) - ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))))) * (ap + (((ap - bp) * ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))) / (((fn.get("d") * (bp * bp)) + (fn.get("x") * bp) + (fn.get("c"))) - ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))))))) + (fn.get("x") * (ap + (((ap - bp) * ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))) / (((fn.get("d") * (bp * bp)) + (fn.get("x") * bp) + (fn.get("c"))) - ((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c"))))))) + (fn.get("c"))))));
                arp.add(mp);
                Map<String, Double> evp = new Functions("").evalBis(ap, bp, fn);
                arp.add(evp);
                while (evp.get("xo") != n && evp.get("rs") != 0) {
                    if (evp.get("rs") > 0) {
                        evp.put("a", evp.get("xo"));
                    } else {
                        evp.put("b", evp.get("xo"));
                    }
                    evp = new Functions("").evalInt(evp.get("a"), evp.get("b"), fn);
                    arp.add(evp);
                }
                int v = 1;
                DefaultTableModel mn = (DefaultTableModel) tn.getModel();
                mn.setRowCount(0);
                for (Map<String, Double> r : arn) {
                    Object[] rw = {v, r.get("a"), r.get("b"), r.get("fa"), r.get("fb"), r.get("xo"), r.get("fxo"), r.get("rs")};
                    mn.addRow(rw);
                    v++;
                }
                tn.setModel(mn);
                v = 1;
                DefaultTableModel mpp = (DefaultTableModel) tp.getModel();
                mpp.setRowCount(0);
                for (Map<String, Double> r : arp) {
                    Object[] rw = {v, r.get("a"), r.get("b"), r.get("fa"), r.get("fb"), r.get("xo"), r.get("fxo"), r.get("rs")};
                    mpp.addRow(rw);
                    v++;
                }
                tp.setModel(mpp);
                ln.setText("Raiz aproximada: " + arn.get(arn.size() - 1).get("xo"));
                lp.setText("Raiz aproximada: " + arp.get(arp.size() - 1).get("xo"));
                JOptionPane.showConfirmDialog(fr, "Las raices son: \n->   " + arn.get(arn.size() - 1).get("xo") + "\n->   " + arp.get(arp.size() - 1).get("xo"), "Aviso", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                btn.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(fr, "La ecuacion es incorrecta. \nFormato: Ax2+Bx+C.", "Aviso", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showConfirmDialog(fr, "Introduce tu ecuacion. \nFormato: Ax2+Bx+C.", "Aviso", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void bisBtn(MouseEvent e, JFrame fr, JTextField tx, JTable tn, JTable tp, JButton btn, JLabel ln, JLabel lp) {
        String a = tx.getText();
        if (!a.isEmpty()) {
            try {
                Pattern cn = Pattern.compile("[+-]");
                Map<String, Double> fn = new HashMap<String, Double>();
                String[] cu = {"d", "x", "c"};
                if (a.charAt(0) != '-') {
                    a = "+" + a;
                }
                Matcher op = cn.matcher(a);
                List<String> opp = new ArrayList<>();
                while (op.find()) {
                    opp.add(op.group());
                }
                String[] ts = cn.split(a);
                if (a.charAt(0) != '-' && ts.length > 4) {
                    JOptionPane.showConfirmDialog(fr, "El formato de la ecuacion es incorrecta.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                } else if (a.charAt(0) == '-' && ts.length <= 4) {
                    int id = 0;
                    ts[0] = ts[0].substring(1);
                    for (String i : ts) {
                        if (i.charAt(0) == 'x') {
                            i = "1x";
                        }
                        if (i.indexOf("x") != -1) {
                            i = i.substring(0, i.indexOf("x"));
                            fn.put(cu[id], Double.parseDouble("" + (op.group(id) + i)));
                        } else {
                            if (id == 1) {
                                fn.put("x", 0.0);
                                fn.put("c", Double.parseDouble(op.group(id) + i));
                                id += 1;
                            } else {
                                fn.put(cu[id], Double.parseDouble(op.group(id) + i));
                            }
                        }
                        id += 1;
                    }
                    if (id == 2) {
                        fn.put("c", 0.0);
                    }
                } else if (a.charAt(0) == '+' && ts.length <= 4) {
                    int id = 0;
                    ts = Arrays.copyOfRange(ts, 1, ts.length);
                    for (String i : ts) {
                        if (i.charAt(0) == 'x') {
                            i = "1x";
                        }
                        if (i.indexOf("x") != -1) {
                            i = i.substring(0, i.indexOf("x"));
                            fn.put(cu[id], Double.parseDouble(opp.get(id) + i));
                        } else {
                            if (id == 1) {
                                fn.put("x", 0.0);
                                fn.put("c", Double.parseDouble(opp.get(id) + i));
                                id += 1;
                            } else {
                                fn.put(cu[id], Double.parseDouble(opp.get(id) + i));
                            }
                        }
                        id += 1;
                    }
                    if (id == 2) {
                        fn.put("c", 0.0);
                    }
                } else {
                    JOptionPane.showConfirmDialog(fr, "El formato de la ecuacion es incorrecta.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                }
                DecimalFormat df = new DecimalFormat("#.00000");
                double ers = (fn.get("x") * fn.get("x")) + (-4 * fn.get("d") * fn.get("c"));
                double frs = ((-1 * fn.get("x")) + (Math.sqrt(ers))) / (2 * fn.get("d"));
                double srs = ((-1 * fn.get("x")) - (Math.sqrt(ers))) / (2 * fn.get("d"));
                double p = Math.max(Double.parseDouble(df.format(frs)), Double.parseDouble(df.format(srs)));
                double n = Math.min(Double.parseDouble(df.format(frs)), Double.parseDouble(df.format(srs)));
                double an = 0;
                while (an > n) {
                    an--;
                }
                double bn = an + 1;
                double bp = 0;
                while (bp < p) {
                    bp++;
                }
                double ap = bp - 1;
                List<Map<String, Double>> arn = new ArrayList<>();
                Map<String, Double> map = new HashMap<>();
                map.put("a", an);
                map.put("fa", Double.parseDouble(df.format((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c")))));
                map.put("b", bn);
                map.put("fb", Double.parseDouble(df.format((fn.get("d") * (bn * bn)) + (fn.get("x") * bn) + (fn.get("c")))));
                map.put("xo", Double.parseDouble(df.format(((an) + (bn)) / 2)));
                map.put("fxo", Double.parseDouble(df.format((fn.get("d") * ((((an) + (bn)) / 2) * (((an) + (bn)) / 2))) + (fn.get("x") * (((an) + (bn)) / 2)) + (fn.get("c")))));
                map.put("rs", Double.parseDouble(df.format(((fn.get("d") * (an * an)) + (fn.get("x") * an) + (fn.get("c"))) * (fn.get("d") * ((((an) + (bn)) / 2) * (((an) + (bn)) / 2))) + (fn.get("x") * (((an) + (bn)) / 2)) + (fn.get("c")))));
                arn.add(map);
                Map<String, Double> evn = new Functions("").evalBis(an, bn, fn);
                arn.add(evn);
                while (evn.get("xo") != n && evn.get("rs") != 0) {
                    if (evn.get("rs") > 0) {
                        evn.put("a", evn.get("xo"));
                    } else {
                        evn.put("b", evn.get("xo"));
                    }
                    evn = new Functions("").evalBis(evn.get("a"), evn.get("b"), fn);
                    arn.add(evn);
                }
                List<Map<String, Double>> arp = new ArrayList<>();
                Map<String, Double> mp = new HashMap<>();
                mp.put("a", ap);
                mp.put("fa", Double.parseDouble(df.format((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c")))));
                mp.put("b", bp);
                mp.put("fb", Double.parseDouble(df.format((fn.get("d") * (bp * bp)) + (fn.get("x") * bp) + (fn.get("c")))));
                mp.put("xo", Double.parseDouble(df.format(((ap) + (bp)) / 2)));
                mp.put("fxo", Double.parseDouble(df.format((fn.get("d") * ((((ap) + (bp)) / 2) * (((ap) + (bp)) / 2))) + (fn.get("x") * (((ap) + (bp)) / 2)) + (fn.get("c")))));
                mp.put("rs", Double.parseDouble(df.format(((fn.get("d") * (ap * ap)) + (fn.get("x") * ap) + (fn.get("c"))) * (fn.get("d") * ((((ap) + (bp)) / 2) * (((ap) + (bp)) / 2))) + (fn.get("x") * (((ap) + (bp)) / 2)) + (fn.get("c")))));
                arp.add(mp);
                Map<String, Double> evp = new Functions("").evalBis(ap, bp, fn);
                arp.add(evp);
                while (evp.get("xo") != n && evp.get("rs") != 0) {
                    if (evp.get("rs") > 0) {
                        evp.put("a", evp.get("xo"));
                    } else {
                        evp.put("b", evp.get("xo"));
                    }
                    evp = new Functions("").evalBis(evp.get("a"), evp.get("b"), fn);
                    arp.add(evp);
                }
                int v = 1;
                DefaultTableModel mn = (DefaultTableModel) tn.getModel();
                mn.setRowCount(0);
                for (Map<String, Double> r : arn) {
                    Object[] rw = {v, r.get("a"), r.get("b"), r.get("fa"), r.get("fb"), r.get("xo"), r.get("fxo"), r.get("rs")};
                    mn.addRow(rw);
                    v++;
                }
                tn.setModel(mn);
                v = 1;
                DefaultTableModel mpp = (DefaultTableModel) tp.getModel();
                mpp.setRowCount(0);
                for (Map<String, Double> r : arp) {
                    Object[] rw = {v, r.get("a"), r.get("b"), r.get("fa"), r.get("fb"), r.get("xo"), r.get("fxo"), r.get("rs")};
                    mpp.addRow(rw);
                    v++;
                }
                tp.setModel(mpp);
                ln.setText("Raiz aproximada: " + arn.get(arn.size() - 1).get("xo"));
                lp.setText("Raiz aproximada: " + arp.get(arp.size() - 1).get("xo"));
                JOptionPane.showConfirmDialog(fr, "Las raices son: \n->   " + arn.get(arn.size() - 1).get("xo") + "\n->   " + arp.get(arp.size() - 1).get("xo"), "Aviso", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                btn.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(fr, "La ecuacion es incorrecta. \nFormato: Ax2+Bx+C.", "Aviso", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showConfirmDialog(fr, "Introduce tu ecuacion. \nFormato: Ax2+Bx+C.", "Aviso", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void grahpBtn(MouseEvent e, JFrame fr, JTextField tx, JTable tn, JTable tp) {
        DefaultTableModel ton = (DefaultTableModel) tn.getModel();
        DefaultTableModel top = (DefaultTableModel) tp.getModel();
        int a = Integer.parseInt("" + ton.getValueAt(0, 1).toString().replaceAll(".0", "")) - 1;
        int b = Integer.parseInt("" + top.getValueAt(0, 2).toString().replaceAll(".0", "")) + 1;
        JFrame gr = new JFrame("Grafica");
        gr.setSize(600, 500);
        gr.setLocationRelativeTo(null);
        XYSeriesCollection d = new XYSeriesCollection();
        JPanel p = new ChartPanel(ChartFactory.createXYLineChart("Aproximacion", "X", "Y", d));
        p.setBounds(0, 0, 584, 464);
        XYSeries ss = new XYSeries("Biseccion");
        String fn = tx.getText().replaceAll("x2", "x^2").replace("x", "*x");
        System.out.println(fn);
        Functions f = new Functions(fn);
        try {
            double[] x = f.rg(a, b, 0.1);
            double[] y = f.ev(x);
            for (int i = 0; i < x.length; i++) {
                ss.add(x[i], y[i]);
            }
            d.removeAllSeries();
            d.addSeries(ss);
        } catch (Exception ex) {
            System.out.println("Error al obtener puntos " + ex);
            JOptionPane.showConfirmDialog(fr, "Ocurrio un error al intentar graficar.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
        gr.add(p);
        gr.setVisible(true);
    }

    public void masBtn(MouseEvent e, JFrame fr, JTextField t1, JTextField t2, JTextField t3) {
        String e1 = t1.getText();
        String e2 = t2.getText();
        String e3 = t3.getText();
        if (e1.isEmpty() || e2.isEmpty() || e3.isEmpty()) {
            JOptionPane.showConfirmDialog(fr, "Introduce todas las ecuaciones.", "Aviso", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        } else {
            List<String> ec = new ArrayList<>();
            ec.add(e1);
            ec.add(e2);
            ec.add(e3);
            Pattern sm = Pattern.compile("[+-=]");
            Pattern ig = Pattern.compile("[xyz]");
            List<String> fe = new ArrayList<>();
            List<Integer> fc = new ArrayList<>();
            List<List<String>> gbe = new ArrayList<>();

            try {
                for (String a : ec) {
                    String[] b = a.split("=");
                    fe.add(b[0]);
                }
                for (String a : ec) {
                    String[] b = a.split("=");
                    fc.add(Integer.parseInt(b[1]));
                }
                for (String a : fe) {
                    if (a.charAt(0) != '-') {
                        char[] d = a.toCharArray();
                        char[] newD = new char[d.length + 1];
                        newD[0] = '+';
                        System.arraycopy(d, 0, newD, 1, d.length);
                        a = new String(newD);
                    }
                    String[] im = sm.split(a);
                    String[] ii = ig.split(a);
                    List<String> sf = new ArrayList<>();
                    sf.add("");
                    sf.add("");
                    sf.add("");
                    int j = 0;
                    int i = 0;
                    String[] nm = new String[3];
                    for (String r : im) {
                        if (!r.isEmpty()) {
                            nm[i] = r;
                            i++;
                        }
                    }
                    for (String r : ii) {
                        if (r.length() <= 1) {
                            if (nm[j].equals("x")) {
                                System.out.println("x");
                                sf.set(0, "1");
                            } else if (nm[j].equals("y")) {
                                System.out.println("y");
                                sf.set(1, "1");
                            } else if (nm[j].equals("z")) {
                                System.out.println("z" + j);
                                sf.set(2, "1");
                            } else {
                                sf.set(j, "0");
                            }
                        } else {
                            if (nm[j].equals("x")) {
                                sf.set(0, r);
                            } else if (nm[j].equals("y")) {
                                sf.set(1, r);
                            } else if (nm[j].equals("z")) {
                                sf.set(2, r);
                            } else {
                                sf.set(j, "0");
                            }
                        }
                        j++;
                    }
                    gbe.add(sf);
                }
                double[] re = new Functions("").gauss(gbe, fc);
                JOptionPane.showConfirmDialog(fr, "Valores para los coeficientes: \n->  x = " + re[0] + "\n->   y = " + re[1] + "\n->   z = " + re[2], "Resultado", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(fr, "Error al calcular.\nAsegurate de haber escrito las ecuaciones correctamente.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

}
