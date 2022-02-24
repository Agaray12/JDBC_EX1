package tienda.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class DAO {
    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    
    protected void conectarBase() throws Exception {
        try {
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            
            conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al conectar a la base de datos");
            throw ex;
        }
    }
    
    protected void consultarBase(String sql) throws Exception {
        try {
            conectarBase();     
            sentencia = conexion.createStatement();  
            resultado = sentencia.executeQuery(sql);
        } catch (Exception ex) {
            System.out.println("Error al realizar consulta");
            throw ex;
        }
    }

    protected void insertarModificarEliminar(String sql) throws Exception {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
//            try{ 
//            conexion.rollback(); //PARA QUE NO SE EJECUTEN CAMBIOS PARCIALES
//            } catch (SQLException e){
//                throw new Exception("Error al hacer el rollback");
//            }
            throw ex;
        } finally {
            desconectarBase();
        }
    }
    
    protected void desconectarBase() throws Exception {
        try {
            if (resultado != null) { 
                resultado.close();
            }
            if (sentencia != null) { 
                sentencia.close();
            }
            if (conexion != null) { 
                conexion.close();
            }
        } catch (Exception ex) {
            System.out.println("Error en desconectar base!");
            throw ex;
        }
    }
}