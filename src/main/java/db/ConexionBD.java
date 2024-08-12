/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;

/**
 *
 * @author deiv
 */
public class ConexionBD {
    
    //Datos de gestión de BD
    Connection conexion = null;
    PreparedStatement consulta = null;
    ResultSet resultado = null;
    
    //Parámetros de inicialización de BD
    String url = "jdbc:mysql://127.0.0.1:3308/FVGamesAdmin";
    String username = "FVGamesAdmin";
    String password = "Admin123";


    //Construir los métodos para la conexión a la BD

    public void setConsulta(String sql) {
        try
        {
            this.consulta = conexion.prepareStatement(sql);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public PreparedStatement getConsulta() {
        return consulta;
    }

    public ResultSet getResultado() {
        try
        {
            return consulta.executeQuery();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void setConexion() {
        try
        {
            this.conexion = DriverManager.getConnection(url, username, password);
            System.out.println("Successful connected");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void cerrarConexion()
    {
        if(resultado != null)
        {
            try
            {
                resultado.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        
        if(consulta != null)
        {
            try
            {
                consulta.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        
        if(conexion != null)
        {
            try
            {
                conexion.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}