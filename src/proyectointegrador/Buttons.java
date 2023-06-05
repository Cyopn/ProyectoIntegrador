package proyectointegrador;

import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
        DefaultTableModel to = (DefaultTableModel) t.getModel();
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

    public void aBtn(MouseEvent e, JFrame fr, JTextField tx, JSpinner sa, JSpinner sb, JLabel lb, JPanel p, XYSeriesCollection d) {
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

}
