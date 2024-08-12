package Model;

import db.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class AdminModelDB {
    ConexionBD conexion = new ConexionBD();
    ResultSet resultado = null;

    public ArrayList<Admin> getAllAdmins()
    {
        try
        {
            //Abrimos la conexión
            conexion.setConexion();
            //Definimos la consulta
            conexion.setConsulta("SELECT U.id as AdminId, username, password, Roleid, R.role\n" +
                    "FROM User U\n" +
                    "INNER JOIN RoleCatalog R ON U.RoleId = R.id\n" +
                    "WHERE U.roleid = 1");

            //Obtenemos los resultados
            resultado = conexion.getResultado();
            ArrayList<Admin> am_list = new ArrayList<Admin>();
            while(resultado.next())
            {
                Admin admin_model = new Admin(resultado.getInt("username"), resultado.getString("password"));
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
}
