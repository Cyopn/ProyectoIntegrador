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
}
