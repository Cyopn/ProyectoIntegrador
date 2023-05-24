package proyectointegrador;

import java.awt.event.*;
import javax.swing.*;

public class ProyectoIntegrador extends JFrame {

    private final Panels p = new Panels();
    private final JMenuBar mb = new JMenuBar();
    private final JMenu ms = new JMenu("Simulacion");
    private final JMenuItem msp = new JMenuItem("Numero pseudoaleatorio");
    private final JMenuItem msc = new JMenuItem("Chi-cuadrada");
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

        mb.add(ms);
        ms.add(msp);
        ms.add(msc);

        mb.add(mc);
        mc.add(mci);
        mc.add(mcd);

        mb.add(mi);
        mi.add(mim);
        mi.add(mir);

        msp.addActionListener((ActionEvent e) -> {
            snpBtn(e);
        });
        mci.addActionListener((ActionEvent e) -> {
            cinBtn(e);
        });
        mcd.addActionListener((ActionEvent e) -> {
            cdiBtn(e);
        });
        this.setJMenuBar(mb);
        p.pseudoPanel(this, mb);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        //new LoadView();
        new ProyectoIntegrador();

    }

    private void cinBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.integralPanel(this, mb);
    }

    private void cdiBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.diferencialPanel(this, mb);
    }

    private void snpBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.pseudoPanel(this, mb);
    }
}
