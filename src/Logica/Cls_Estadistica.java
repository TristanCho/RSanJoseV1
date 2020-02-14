package Logica;

import Conexion.Cls_Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Cls_Estadistica {

    private DefaultTableModel TABLA;
    private ResultSet RS;
    private PreparedStatement PS;
    private final Cls_Conexion cnn;
    String Sensor1;  
    private final String SQL_SELECT = "SELECT Sensores.nombre_sensor,COUNT(sensor) as NumeroRegistros\n"
            + "FROM Actividad INNER join Sensores\n"
            + "ON Actividad.sensor = Sensores.Id_sensor\n"
            + "where hora between cast('2019-02-13' as date) and cast('2020-02-13' as date)\n"
            + "group by Actividad.sensor\n"
            + "ORDER BY Actividad.sensor ASC";
    //static String variable1;
    
    public Cls_Estadistica(Cls_Conexion cnn) {
        PS = null;
        this.cnn = cnn;
       // Sensor1 = "rrrrr";        
    }
   

    private DefaultTableModel ConfiguraTitulos_Estadistica() {
        TABLA = new DefaultTableModel();
        TABLA.addColumn("Nombre Sensor");
        TABLA.addColumn("N. Registros");
        return TABLA;
    }
    //Método para listar los datos desde la BBDD

    public DefaultTableModel Tabla_Estadistica() throws InterruptedException, ClassNotFoundException, SQLException {

        try {
            //System.out.println("Desde el try de puerta");
            ConfiguraTitulos_Estadistica();
            PS = cnn.getConnection().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            Object[] fila = new Object[2];

            while (RS.next()) {
                Sensor1 = RS.getString(1);
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                TABLA.addRow(fila);
                
            }
            
System.out.println("print Sensor1 = "+Sensor1);
        } catch (SQLException e) {
            System.out.println("Error al Listar los datos desde Cls_Estadistica: " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            //CN.close();
        }
        return TABLA;
    }
    
     public static void generarBarras() {
         //System.out.println("Generando Barras..."+Sensor1);
       //String SensorX = Sensor1.toString();
        try {

            //https://www.youtube.com/watch?v=qolbNweZVXU&t=350s
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            //ds.add
            //ds.addValue(200, SensorX, "");
            ds.addValue(310, "Pasillo", "");
            ds.addValue(110, "Cero", "");
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
      private String dime_dato() {// MÉTODO GETTER
        return Sensor1;
    }
      private void GS1(String Sensor1){//Método Setter
          this.Sensor1= "Rojo";
      }
}
