package Model;

import db.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Date;

public class SaleModelDB {
    ConexionBD conexion = new ConexionBD();
    ResultSet resultado = null;

    public ArrayList<Sale> getAllSales()
    {
        try
        {
            //Abrimos la conexión
            conexion.setConexion();
            //Definimos la consulta
            conexion.setConsulta("SELECT CustomerID,  Items, Date, Total, C.Name as CustomerName, C.LastName as CustomerLastName, C.email, C.preferedPaymentMethod\n" +
                    "FROM Sales S\n" +
                    "INNER Join Customer C ON S.CustomerID = C.id");
            //Obtenemos los resultados
            resultado = conexion.getResultado();
            ArrayList<Sale> sm_list = new ArrayList<Sale>();
            while(resultado.next())
            {
                Sale sale_model = new Sale(resultado.getInt("CustomerID"), resultado.getString("Items"), resultado.getDate("Date"), resultado.getInt("Total"));
                sm_list.add(sale_model);
            }
            conexion.cerrarConexion();
            return sm_list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void registerSale(int customerId, String concatedItems, Date date, float total){
        try
        {
            //Abrimos la conexión
            conexion.setConexion();
            //Definimos la consulta
            conexion.setConsulta("INSERT INTO SALES(CustomerId, Items, Date, Total)\n" +
                    "VALUES ("+customerId+", '"+ concatedItems+"', '"+ date +"', '"+ total +"');");
            //Obtenemos los resultados
            conexion.performUpdate();

            conexion.cerrarConexion();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Sale> getSalesByCustomer(int customerId)
    {
        try
        {
            //Abrimos la conexión
            conexion.setConexion();
            //Definimos la consulta
            conexion.setConsulta("SELECT CustomerID,  Items, Date, SalesAgent, Total, C.Name as CustomerName, C.LastName as CustomerLastName, C.email, C.preferedPaymentMethod\n" +
                    "FROM Sales S\n" +
                    "INNER Join Customer C ON S.CustomerID = C.id\n" +
                    "WHERE CustomerID = " + customerId);
            //Obtenemos los resultados
            resultado = conexion.getResultado();
            ArrayList<Sale> sm_list = new ArrayList<Sale>();
            while(resultado.next())
            {
                Sale sale_model = new Sale(resultado.getInt("CustomerID"), resultado.getString("Items"), resultado.getDate("Date"), resultado.getInt("Total"));
                sm_list.add(sale_model);
            }
            conexion.cerrarConexion();
            return sm_list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Sale> getSalesByIntervalTime(Date fromDate, Date toDate)
    {
        try
        {
            //Abrimos la conexión
            conexion.setConexion();
            //Definimos la consulta
            conexion.setConsulta("SELECT  CustomerId, Items, Date, SalesAgent, Total" +
                    "FROM SALES\n" +
                    "WHERE DATE >= '" + fromDate + "'\n" +
                    "AND DATE <= '" + toDate + "'");
            //Obtenemos los resultados
            resultado = conexion.getResultado();
            ArrayList<Sale> sm_list = new ArrayList<Sale>();
            while(resultado.next())
            {
                Sale sale_model = new Sale(resultado.getInt("CustomerID"), resultado.getString("Items"), resultado.getDate("Date"), resultado.getInt("Total"));
                sm_list.add(sale_model);
            }
            conexion.cerrarConexion();
            return sm_list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
