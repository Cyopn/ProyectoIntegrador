package proyectointegrador;

import org.jfree.chart.*;
import javax.swing.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graphic extends JFrame {

    XYSeriesCollection d = new XYSeriesCollection();

    public Graphic(String f, double[] x, double[] y, String ff, double[] xx, double[] yy) {
        JPanel p = new ChartPanel(ChartFactory.createScatterPlot("Media movil", "Datos", "Valor", d));
        this.setSize(600, 520);
        this.setTitle("Grafica");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        p.setBounds(0, 0, 584, 464);
        
        XYSeries ss = new XYSeries(ff);
        for (int i = 0; i < xx.length; i++) {
            ss.add(xx[i], yy[i]);
        }
        d.addSeries(ss);
        
        XYSeries s = new XYSeries(f);
        for (int i = 0; i < x.length; i++) {
            s.add(x[i], y[i]);
        }
        d.addSeries(s);

        this.add(p);

    }

}
