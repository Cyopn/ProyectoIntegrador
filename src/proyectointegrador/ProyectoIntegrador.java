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
    private final JMenuItem msv = new JMenuItem("Smirnov-Kolmogorov");
    private final JMenu mc = new JMenu("Calculo");
    private final JMenuItem mci = new JMenuItem("Integral");
    private final JMenuItem mcd = new JMenuItem("Diferencial");

    private final JMenu mf = new JMenu("Funciones");
    private final JMenuItem mfa = new JMenuItem("Area");
    private final JMenuItem mfg = new JMenuItem("Grafica");

    private final JMenu mi = new JMenu("Investigacion de operaciones");
    private final JMenuItem mio = new JMenuItem("Optimizacion");
    private final JMenuItem mir = new JMenuItem("Region factible");

    private final JMenu mn = new JMenu("Metodos Numericos");
    private final JMenuItem mni = new JMenuItem("Intervalo");
    private final JMenuItem mnb = new JMenuItem("Biseccion");
    
    private final JMenu ma = new JMenu("Algebra lineal");
    private final JMenuItem mas = new JMenuItem("Sistemas de ecuaciones");

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
        ms.add(msv);

        mb.add(mc);
        mc.add(mci);
        mc.add(mcd);

        mb.add(mf);
        mf.add(mfa);
        mf.add(mfg);

        //mb.add(mi);
        mi.add(mio);
        mi.add(mir);

        mb.add(mn);
        mn.add(mni);
        mn.add(mnb);
        
        mb.add(ma);
        ma.add(mas);

        msp.addActionListener((ActionEvent e) -> {
            mspBtn(e);
        });
        msc.addActionListener((ActionEvent e) -> {
            mscBtn(e);
        });
        msm.addActionListener((ActionEvent e) -> {
            msmBtn(e);
        });
        msv.addActionListener((ActionEvent e) -> {
            msvBTtn(e);
        });
        mci.addActionListener((ActionEvent e) -> {
            cinBtn(e);
        });
        mcd.addActionListener((ActionEvent e) -> {
            cdiBtn(e);
        });
        mfa.addActionListener((ActionEvent e) -> {
            mcaBtn(e);
        });
        mfg.addActionListener((ActionEvent e) -> {
            mfgBtn(e);
        });

        mio.addActionListener((ActionEvent e) -> {
            mioBtn(e);
        });

        mir.addActionListener((ActionEvent e) -> {

        });

        mni.addActionListener((ActionEvent e) -> {
            mniBtn(e);
        });
        
        mnb.addActionListener((ActionEvent e)->{
            mnbBtn(e);
        });
        
        mas.addActionListener((ActionEvent e)->{
            masBtn(e);
        });
        
        this.setJMenuBar(mb);
        p.initPanel(this, mb);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LoadView();
        new Functions("").val();
        //new ProyectoIntegrador();

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

    private void mcaBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.areaPanel(this, mb);
    }

    private void msvBTtn(ActionEvent e) {
        p.cleanPanel(this);
        p.skPanel(this, mb);
    }

    private void mfgBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.funPanel(this, mb);
    }

    private void mioBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.optPanel(this, mb);
    }

    private void mirBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.rfPanel(this, mb);
    }

    private void mniBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.intPanel(this, mb);
    }

    private void mnbBtn(ActionEvent e) {
        p.cleanPanel(this);
        p.bisPanel(this, mb);
    }
    
    private void masBtn(ActionEvent e){
        p.cleanPanel(this);
        p.masPanel(this, mb);
    }
}
