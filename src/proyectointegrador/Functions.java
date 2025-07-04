package proyectointegrador;

import java.awt.Font;
import java.awt.FontMetrics;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.mariuszgromada.math.mxparser.*;

public class Functions {

    String df;

    public void val() {
        License.iConfirmNonCommercialUse("Cyopn");
    }

    public Functions(String def) {
        df = def;
    }

    public double eval(double x) throws Exception {
        Expression e = new Expression(df.replaceAll("x", String.valueOf(x)));
        double r = e.calculate();
        return r;
    }

    public double[] rg(double xo, double xn, double d) {
        int n = (int) (Math.abs(xn - xo) / d);
        double[] r = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            r[i] = xo;
            xo += d;
        }
        return r;
    }

    public double[] ev(double[] x) throws Exception {
        int n = x.length;
        double[] r = new double[n];
        for (int i = 0; i < n; i++) {
            r[i] = eval(x[i]);
        }
        return r;
    }

    public double area(String fn, int a, int b) {
        Expression e = new Expression(fn.replaceAll("x", String.valueOf(a)).replaceAll("log", "lg"));
        Expression e1 = new Expression(fn.replaceAll("x", String.valueOf(b)).replaceAll("log", "lg"));

        return e1.calculate() - e.calculate();
    }

    public int getStringMetric(String s, Font f) {
        FontMetrics fn = new FontMetrics(f) {
            @Override
            public int stringWidth(String str) {
                return super.stringWidth(str);
            }
        };
        int w = fn.stringWidth(s);
        System.out.println(w);
        return 0;
    }

    public String[] generatePs(int xo, int v, int d) {
        String[] res = new String[3];
        int xo2 = Integer.parseInt((xo * xo) + "");
        if ((String.valueOf(xo2).length()) % 2 == 1) {
            xo2 = Integer.parseInt(xo2 + "0");
        }
        int i = (String.valueOf(xo2).length() - d) / 2;
        String[] c = String.valueOf(xo2).split("");
        int j = 1;
        String rs = "";
        for (String w : c) {
            if (j > i && j <= i + d) {
                rs += w;
            } else {
            }
            j++;
        }
        res[0] = String.valueOf(xo);
        res[1] = String.valueOf(xo2);
        res[2] = rs;
        return res;
    }

    public List<String> loadNp(JFrame fr, JMenuBar mb) {
        List<String> l = new ArrayList<>();
        Panels p = new Panels();

        try {
            File f = new File("a.txt");
            Scanner r = new Scanner(f);
            while (r.hasNextLine()) {
                l.add(r.nextLine());
            }
            r.close();
            if (l.size() <= 0) {
                JOptionPane.showConfirmDialog(fr, "Primero debes generar tus numeros pseudoaleatorios.", "Aviso", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                p.cleanPanel(fr);
                p.pseudoPanel(fr, mb);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showConfirmDialog(fr, "Error al cargar numeros pseudoaleatorios.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

            System.out.println("Error al cargar elementos: " + ex);
        }
        return l;
    }

    public Map<String, Double> evalBis(double a, double b, Map<String, Double> fn) {
        Map<String, Double> map = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#.00000");
        double fap = (fn.get("d") * (a * a)) + (fn.get("x") * a) + (fn.get("c"));
        double fbp = (fn.get("d") * (b * b)) + (fn.get("x") * b) + (fn.get("c"));
        double xo = ((a) + (b)) / 2;
        double fxo = (fn.get("d") * (xo * xo)) + (fn.get("x") * xo) + (fn.get("c"));
        double axo = fap * fxo;
        map.put("a", a);
        map.put("fa", Double.parseDouble(df.format(fap)));
        map.put("b", b);
        map.put("fb", Double.parseDouble(df.format(fbp)));
        map.put("xo", Double.parseDouble(df.format(xo)));
        map.put("fxo", Double.parseDouble(df.format(fxo)));
        map.put("rs", Double.parseDouble(df.format(axo)));
        return map;
    }

    public Map<String, Double> evalInt(double a, double b, Map<String, Double> fn) {
        Map<String, Double> map = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#.00000");
        double fap = (fn.get("d") * (a * a)) + (fn.get("x") * a) + (fn.get("c"));
        double fbp = (fn.get("d") * (b * b)) + (fn.get("x") * b) + (fn.get("c"));
        double xo = a + (((a - b) * fap) / (fbp - fap));
        double fxo = (fn.get("d") * (xo * xo)) + (fn.get("x") * xo) + (fn.get("c"));
        double axo = fap * fxo;
        map.put("a", a);
        map.put("fa", Double.parseDouble(df.format(fap)));
        map.put("b", b);
        map.put("fb", Double.parseDouble(df.format(fbp)));
        map.put("xo", Double.parseDouble(df.format(xo)));
        map.put("fxo", Double.parseDouble(df.format(fxo)));
        map.put("rs", Double.parseDouble(df.format(axo)));
        return map;
    }

    public double[] gauss(List<List<String>> gb, List<Integer> f) {
        double[] fc = new double[f.size()];
        double[][] gbe = new double[gb.size()][3];
        int it = 0;
        int jt = 0;
        for (int r : f) {
            fc[it] = r;
            it++;
        }
        it = 0;
        for (List<String> r : gb) {
            for (String rs : r) {
                if (rs.isEmpty()) {
                    rs = "0";
                }
                gbe[it][jt] = Integer.parseInt(rs);
                jt++;
            }
            jt = 0;
            it++;
        }
        int n = fc.length;
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double factor = gbe[i][k] / gbe[k][k];
                for (int j = k; j < n; j++) {
                    gbe[i][j] -= factor * gbe[k][j];
                }
                fc[i] -= factor * fc[k];
            }
        }
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = fc[i] / gbe[i][i];
            for (int j = 0; j < i; j++) {
                fc[j] -= gbe[j][i] * x[i];
            }
        }
        return x;
    }

    public void cleanTable(JTable t) {
        DefaultTableModel to = (DefaultTableModel) t.getModel();
        int ri = t.getRowCount();
        while (ri > 0) {
            to.removeRow(0);
            ri = t.getRowCount();
        }
    }
}
