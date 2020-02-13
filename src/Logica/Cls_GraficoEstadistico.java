package Logica;

import Logica.Cls_Estadistica;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Cls_GraficoEstadistico {
    //Private final Cls_Estadistica Estadistics
    String variable1 = "";
    //variable1 = String//Pendiente pasar valores
    //TODO: Pendiente pasar valores a los gráficos, primero por variable y luego por array
    public static void generarBarras() {
        
        try {
            //https://www.youtube.com/watch?v=qolbNweZVXU&t=350s
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            ds.addValue(200, "Cocina", "");
            ds.addValue(310, "Pasillo", "");
            ds.addValue(110, "Puerta", "");
            ds.addValue(150, "MainRoom", "");
            ds.addValue(450, "Patio", "");
            ds.addValue(320, "Salón", "");
            ds.addValue(10, "Baño", "");
            ds.addValue(10, "Recibidor", "");

            JFreeChart jf = ChartFactory.createBarChart3D("Sensores", "Estadisticas",
                    "Registros de Actividad", ds, PlotOrientation.VERTICAL, true, true, true);

            ChartFrame f = new ChartFrame("Comportamiento", jf);
            f.setSize(1000, 600);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
}
