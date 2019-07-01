package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Logica.VariablesConfig;



public class Cls_Conexion {
    
    private final VariablesConfig DatosConfig;
    
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";// Hacemos referencia al DRIVER que hemos importado para hacer la conexión
    private static String UserDDBB;
    //public  final String UserDDBB =DatosConfig.dimeUser();
    private static String UserPass = "cristhian";
    //private static final String RemoteServer = "89.129.146.6";
    private static  String RemoteServer = "192.168.1.20";
    private static  String PUERTO = "3306";
    private static  String DDBB = "Gea";
    private static  String UrlRemota = "jdbc:mysql://"+RemoteServer+":"+PUERTO+"/"+DDBB+"?autoReconnect=true&useSSL=false";
    private Connection CN;

    public Cls_Conexion() {
        
        DatosConfig = new VariablesConfig();
        
        UserDDBB =DatosConfig.dimeUser();
        System.out.println(DatosConfig.dimeUser()+"Mazda");
        System.out.println("Usuario pido datos actuales"+UserDDBB);
     DatosConfig.dimePass();
      DatosConfig.dimeDDBB();
      DatosConfig.dimePuerto();
      DatosConfig.dimeLocalServer();
      DatosConfig.dimeRemoteServer();
      DatosConfig.dimeRemoteServer2();
      
        CN = null;
    }
        
   
    //Este método nos retorna la conexión
    public Connection getConnection() throws SQLException, ClassNotFoundException, InterruptedException{
        int contador = 0;
        while(CN==null){
            
            Class.forName(DRIVER);
            CN = DriverManager.getConnection(UrlRemota, UserDDBB, UserPass);
            System.out.println("Conectando...");
            //Thread.sleep(5000);
            contador++;
            System.out.println("Contador="+contador);
          }
       
        
        
        
        /*
        catch(ClassNotFoundException | SQLException ex){
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al conectar con la base de datos", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error de Conectar Con la DDBB...");
            //System.exit(0);
          }*/
          
        return CN;
    }
    
    
/*
//Cierra conexiones abiertas a la base de datos.
    public void close(){
        try{
            System.out.println("Cerrando Conexión");
            CN.close();
            System.out.println("Conexión Cerrada");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al cerrar la conexión con la base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }*/
    
    
    
}
