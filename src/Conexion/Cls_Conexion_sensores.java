package Conexion;
//Modificado 23:06:54
import Logica.VariablesConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Properties;

public class Cls_Conexion_sensores {
    
   private VariablesConfig DatosConfig;
    
    
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";// Hacemos referencia al DRIVER que hemos importado para hacer la conexión
    String UserDDBB;
    String PassDDBB;
    String RemoteServer;
    String RemoteServer2;
    String LocalServer;
    //private static final String RemoteServer = "192.168.1.20";
    String PUERTO;
    String DDBB;
    String UrlRemota;
    private Connection CN_Sensores;

    public Cls_Conexion_sensores() {
        
            DatosConfig = new VariablesConfig();
        
        UserDDBB =DatosConfig.dimeUser();
       // DatosConfig.setUser();
       System.out.println("UserDDBB   Modificado  ???==== "+UserDDBB);
        PassDDBB = DatosConfig.dimePass();
        DDBB =DatosConfig.dimeDDBB();
        RemoteServer = DatosConfig.dimeRemoteServer();
        RemoteServer2 = DatosConfig.dimeRemoteServer2();
        LocalServer = DatosConfig.dimeLocalServer();
        PUERTO = DatosConfig.dimePuerto();
        UrlRemota=  "jdbc:mysql://"+RemoteServer+":"+PUERTO+"/"+DDBB+"?autoReconnect=true&useSSL=false";
     
        CN_Sensores= null;
    }
        
    //Este método nos retorna la conexión
    public Connection getConnection(){
        try{
            Class.forName(DRIVER);
            CN_Sensores = DriverManager.getConnection(UrlRemota, UserDDBB, PassDDBB);
           
          }catch(ClassNotFoundException | SQLException ex){
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al conectar con la base de datos", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error de Conectar Con Sensores_DDBB...");
            //System.exit(0);
        }
        return CN_Sensores;
    }
     //Cierra conexiones abiertas a la base de datos.
    public void close(){
        try{
            System.out.println("Cerrando Conexión");
            CN_Sensores.close();
           System.out.println("Conexión Cerrada");
        }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al cerrar la conexión con la base de datos En Catch Close", JOptionPane.ERROR_MESSAGE);
       }
    } 
    
}
