package proyectointegrador;

import java.awt.event.*;
import javax.swing.*;

public class ProyectoIntegrador extends JFrame {

    private final Panels p = new Panels();
    private final JMenuBar mb = new JMenuBar();
    private final JMenu ms = new JMenu("Simulacion");
    private final JMenuItem msp = new JMenuItem("Numero pseudoaleatorio");
    private final JMenuItem msc = new JMenuItem("Chi-cuadrada");
    private final JMenuItem msm = new JMenuItem("Media movil");
    private final JMenu mc = new JMenu("Calculo");
    private final JMenuItem mci = new JMenuItem("Integral");
    private final JMenuItem mcd = new JMenuItem("Diferencial");
    private final JMenuItem mca = new JMenuItem("Area");

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
        ms.add(msm);

        mb.add(mc);
        mc.add(mci);
        mc.add(mcd);
        mc.add(mca);

        msp.addActionListener((ActionEvent e) -> {
            mspBtn(e);
        });
        msc.addActionListener((ActionEvent e) -> {
            mscBtn(e);
        });
        msm.addActionListener((ActionEvent e) -> {
            msmBtn(e);
        });
        mci.addActionListener((ActionEvent e) -> {
            cinBtn(e);
        });
        mcd.addActionListener((ActionEvent e) -> {
            cdiBtn(e);
        });
        mca.addActionListener((ActionEvent e) -> {
            mcaBtn(e);
        });
        this.setJMenuBar(mb);
        p.initPanel(this, mb);
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

    private void mspBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.pseudoPanel(this, mb);
    }

    private void mscBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.chiPanel(this, mb);
    }

    private void msmBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.mediaPanel(this, mb);
    }
    
    private void mcaBtn(ActionEvent e){
        p.cleanPanel(this);
        p.areaPanel(this, mb);
    }
}
