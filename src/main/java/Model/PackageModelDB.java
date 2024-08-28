package Model;

import db.ConexionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PackageModelDB {
    ConexionBD conexion = new ConexionBD();
    ResultSet resultado = null;
    public ArrayList<Package> getAllPackages()
    {
        try
        {
            //Abrimos la conexión
            conexion.setConexion();
            //Definimos la consulta
            conexion.setConsulta("SELECT id, items, discountPercentage FROM package");

            //Obtenemos los resultados
            resultado = conexion.getResultado();
            ArrayList<Package> am_list = new ArrayList<Package>();
            while(resultado.next())
            {
                Package admin_model = new Package(resultado.getInt("id"), resultado.getString("items"), resultado.getInt("discountPercentage"));
                am_list.add(admin_model);
            }
            conexion.cerrarConexion();
            return am_list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void insertNewPackage(String items, int discount){
        try
        {
            //Abrimos la conexión
            conexion.setConexion();
            //Definimos la consulta
            conexion.setConsulta("INSERT INTO package (items, discountPercentage)\n" +
                    "VALUES('"+ items + "', " + discount + ")");
            //Obtenemos los resultados
            conexion.performUpdate();

            conexion.cerrarConexion();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
