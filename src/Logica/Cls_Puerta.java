package Logica;

import Conexion.Cls_Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Cls_Puerta {

    private DefaultTableModel TABLA;
    private ResultSet RS;
    private PreparedStatement PS;
    private final Cls_Conexion cnn;
    private final String SQL_SELECT = "SELECT * FROM `VCaro`";

    public Cls_Puerta(Cls_Conexion cnn) {
        PS = null;
        this.cnn = cnn;
    }

    private DefaultTableModel ConfiguraTitulos_Puerta() {
        TABLA = new DefaultTableModel();        
        TABLA.addColumn("Sensor");
        TABLA.addColumn("Hora");
        return TABLA;
    }

    //Método para listar los datos desde la BBDD
    public DefaultTableModel Tabla_Puerta() throws InterruptedException, ClassNotFoundException, SQLException {

        try {
            //System.out.println("Desde el try de puerta");
            ConfiguraTitulos_Puerta();
            PS = cnn.getConnection().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            Object[] fila = new Object[2];

            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getTimestamp(2);
                TABLA.addRow(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al Listar los datos desde Cls_Puerta: " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            //CN.close();
        }
        return TABLA;
    }

}
