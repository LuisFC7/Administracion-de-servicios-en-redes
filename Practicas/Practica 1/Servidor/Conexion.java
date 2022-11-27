import java.sql.DriverManager;

import javax.naming.spi.DirStateFactory.Result;

import java.sql.*;

//Conexión de la base de datos
// public class Conexion {
//     Connection cont;

//     public Conexion() {
//         try {
//             Class.forName("com.mysql.cj.jdbc.Driver");
//             cont = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalogo_productos", "root",
//                     "Basesdedatosmysql1");
//         } catch (Exception e) {
//             System.out.println("Error: Connection database not succesfully");
//         }
//     }

//     public static void main(String[] args) {
//         Conexion cnt = new Conexion();
//         Statement st;
//         ResultSet resultado;
//         try {
//             st = cnt.cont.createStatement();
//             resultado = st.executeQuery("select * from productos");

//             while (resultado.next()) {
//                 System.out.println(resultado.getInt("Id"));
//                 System.out.println(resultado.getString("Nombre"));
//                 System.out.println(resultado.getBigDecimal("Precio"));
//                 System.out.println(resultado.getLong("Descripcion"));
//                 System.out.println(resultado.getInt("Stock"));
//                 System.out.println(resultado.getString("Imagen"));
//             }
//             cnt.cont.close();

//         } catch (Exception e) {
//             // TODO: handle exception
//         }

//     }

// }
public class Conexion {
    String bd = "catalogo_productos";
    String url = "jbdc:mysql://127.0.0.1:3306/";
    String user = "root";
    String password = "Basesdedatosmysql1";
    String driver = "com.mysql.jdbc.Driver";
    Connection conec;

    public Conexion(String bd) {
        this.bd = bd;

    }

    public Connection conectar() {
        try {
            Class.forName(driver);
            conec = DriverManager.getConnection(url + bd, user, password);
            System.out.println("Connection was done succesfully...");
        } catch (Exception e) {
            System.out.println("No se pudo conectar");
        }
        return conec;

    }

    public void desconectar() {
        try {
            conec.close();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        Conexion conexion = new Conexion("catalogo_productos");
        conexion.conectar();

    }

}
