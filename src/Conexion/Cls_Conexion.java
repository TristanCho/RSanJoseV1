package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;




public class Cls_Conexion {
    
  
    private static final String DRIVER = "com.mysql.jdbc.Driver";// Hacemos referencia al DRIVER que hemos importado para hacer la conexión
    private static final String UserDDBB = "cristhian";
    private static final String UserPass = "cristhian";
    private static final String RemoteServer = "89.129.146.6";
    //private static final String LocalServer = "192.168.1.20";
    private static final String PUERTO = "3306";
    private static final String DDBB = "Gea";
    private static final String UrlRemota = "jdbc:mysql://"+RemoteServer+":"+PUERTO+"/"+DDBB+"?autoReconnect=true&useSSL=false";
    private Connection CN;

    public Cls_Conexion() {
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
