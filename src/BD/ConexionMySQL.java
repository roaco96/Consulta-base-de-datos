/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class ConexionMySQL 
{
    private String nombreBD, usuario, pass, host;
    private Connection conexion;

    public ConexionMySQL(String usuario, String contrasena, String bd) 
    {
        this.usuario=usuario;
        pass=contrasena;
        nombreBD=bd;
        host="localhost";
        conexion=null;
    }
    
    private void registrarDriver() throws SQLException
    {
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (Exception e)
        {
           throw new SQLException("Error al conectar con MySQL: " +e.getMessage());
        }

    }
    
    public void conectar() throws SQLException
    {
        if (conexion == null || conexion.isClosed()) 
        {
            registrarDriver();
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://" + host + "/" + nombreBD, usuario, pass);
        }

    }
    
    public void desconectar() throws SQLException
    {
        if (conexion != null && !conexion.isClosed())
            conexion.close();

    }
    
    public ResultSet ejecutarSelect(String consulta) throws SQLException
    {
        Statement stmt = conexion.createStatement();
        ResultSet rset = stmt.executeQuery(consulta);
        
        return rset;
    }

    public int ejecutarINSERT_DELETE_UPDATE(String consulta) throws SQLException
    {
        Statement stmt= conexion.createStatement();
        int filas_afectadas= stmt.executeUpdate(consulta);
        return filas_afectadas;
    }
    
}
