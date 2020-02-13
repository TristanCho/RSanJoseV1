package Logica;

import Conexion.Cls_Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Cls_Estadistica {

    private DefaultTableModel TABLA;
    private ResultSet RS;
    private PreparedStatement PS;
    private final Cls_Conexion cnn;
    private final String SQL_SELECT = "SELECT Sensores.nombre_sensor,COUNT(sensor) as NumeroRegistros\n"
            + "FROM Actividad INNER join Sensores\n"
            + "ON Actividad.sensor = Sensores.Id_sensor\n"
            + "where hora between cast('2019-02-13' as date) and cast('2020-02-13' as date)\n"
            + "group by Actividad.sensor\n"
            + "ORDER BY Actividad.sensor ASC";
    public String ValorPrueba = "";

    public Cls_Estadistica(Cls_Conexion cnn) {
        PS = null;
        this.cnn = cnn;
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
                ValorPrueba = RS.getString(1);
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                TABLA.addRow(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al Listar los datos desde Cls_Estadistica: " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            //CN.close();
        }
        return TABLA;
    }

    // private PasoDatos(){ }
}
