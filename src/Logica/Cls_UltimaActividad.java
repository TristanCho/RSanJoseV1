package Logica;

import Conexion.Cls_Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Cls_UltimaActividad {
    
    private DefaultTableModel TABLA;
    private ResultSet RS;
    private PreparedStatement PS;
    private final Cls_Conexion cnn;
    private final String SQL_SELECT = "SELECT * FROM `ultimaActividad`";

    
    
     public Cls_UltimaActividad(Cls_Conexion cnn) {
        PS = null;
        this.cnn = cnn;
    }
     private DefaultTableModel ConfiguraTitulos_ultimaActividad(){
         TABLA = new DefaultTableModel();
        TABLA.addColumn("Sensor");
        TABLA.addColumn("Hora");
        return TABLA;
     }
     
     //Método para listar los datos desde la BBDD
      public DefaultTableModel Tabla_UltimaActividad() throws InterruptedException, ClassNotFoundException, SQLException {

        try {
            System.out.println("Desde el try de ultima actividad");
            ConfiguraTitulos_ultimaActividad();
            PS = cnn.getConnection().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            Object[] fila = new Object[2];

            while (RS.next()) {

                fila[0] = RS.getString(1);
                fila[1] = RS.getTimestamp(2);

                TABLA.addRow(fila);
           
            }

        } catch (SQLException e) {
            System.out.println("Error al Listar los datos desde Cls_UltimaActividad: " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            //CN.close();
        }
        return TABLA;
    }
     
}
