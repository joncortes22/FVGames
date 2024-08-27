package Model;

import db.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class ItemModelDB {
    ConexionBD conexion = new ConexionBD();
    ResultSet resultado = null;

    public ArrayList<Item> getAllStock() {
        try {
            //Abrimos la conexión
            conexion.setConexion();
            //Definimos la consulta
            conexion.setConsulta("SELECT id, name, category, availability , unitPrice FROM Stock");
            //Obtenemos los resultados
            resultado = conexion.getResultado();
            ArrayList<Item> im_list = new ArrayList<>();
            while (resultado.next()) {
                Item item_model = new Item(resultado.getInt("id"), resultado.getString("category"), resultado.getString("name"), resultado.getInt("availability"), resultado.getInt("unitPrice"));
                im_list.add(item_model);
            }
            conexion.cerrarConexion();
            return im_list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProduct(int id, String name, int availability, int unitPrice) {
        try {
            //Abrimos la conexión
            conexion.setConexion();
            //Definimos la consulta
            conexion.setConsulta("UPDATE stock\n" +
                    "SET Name = " + name + ", availability = " + availability + ", UnitPrice = " + unitPrice + " WHERE id = " + id);
            //Obtenemos los resultados
            conexion.performUpdate();

            conexion.cerrarConexion();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void insertNewProduct(String category, String name, int availability, int unitPrice){
        try
        {
            //Abrimos la conexión
            conexion.setConexion();
            //Definimos la consulta
            conexion.setConsulta("INSERT INTO STOCK (Category, Name, Availability, UnitPrice)\n" +
                    "VALUES('"+ category + "', '" + name + "', " + availability + ", " + unitPrice +")");
            //Obtenemos los resultados
            conexion.performUpdate();

            conexion.cerrarConexion();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

