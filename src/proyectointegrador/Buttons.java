package proyectointegrador;

import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import javax.swing.*;

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
        if (f.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(fr, "Ingresa una raiz.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
        } else {
            int xo = Integer.parseInt(f.getText());
            int d = Integer.parseInt(s.getValue().toString());
            if (xo == 0) {
                JOptionPane.showConfirmDialog(fr, "La raiz debe de ser mayor a cero.", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            } else {
                int v = 1;
                int xo2 = 0;
                String c = "";
                int r = 0;
                List<String> x2 = new ArrayList<>();
                List<String> ri = new ArrayList<>();
                while (v <= r) {
                    
                }
            }
        }
    }
}
