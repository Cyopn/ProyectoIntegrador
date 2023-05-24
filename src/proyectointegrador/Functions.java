package proyectointegrador;

import java.awt.*;

public class Functions {

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
}
