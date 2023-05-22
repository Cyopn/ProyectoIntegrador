package proyectointegrador;

import java.awt.event.*;
import javax.swing.*;

public class ProyectoIntegrador extends JFrame {

    private final JMenuBar mb = new JMenuBar();
    private final JMenu mc = new JMenu("Calculo");
    private final JMenuItem mci = new JMenuItem("Integral");
    private final JMenuItem mcd = new JMenuItem("Diferencial");
    private final JMenu mi = new JMenu("Investigacion de operaciones");
    private final JMenuItem mim = new JMenuItem("Maximizar o minimizar");
    private final JMenuItem mir = new JMenuItem("Refion factible");

    public ProyectoIntegrador() {
        this.setSize(500, 400);
        this.setTitle("Proyecto Integrador");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mb.add(mc);
        mc.add(mci);
        mc.add(mcd);
        
        mb.add(mi);
        mi.add(mim);
        mi.add(mir);
        mci.addActionListener((ActionEvent e) -> {
            cinBtn(e);
        });
        mcd.addActionListener((ActionEvent e) -> {
            cdiBtn(e);
        });
        this.setJMenuBar(mb);
        Panels p = new Panels();
        p.cleanPanel(this);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ProyectoIntegrador().setVisible(true);
    }

    private void cinBtn(ActionEvent e) {
        Panels p = new Panels();
        p.cleanPanel(this);
        p.integralPanel(this, mb);
    }

    private void cdiBtn(ActionEvent e) {
        Panels p = new Panels();
        p.cleanPanel(this);
        p.diferencialPanel(this, mb);
    }
}
