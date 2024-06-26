package EjerciciosSeis;

import java.sql.*;

public class EjercicioUno {
    /*
     * Username and password and login URL
     * */
    final static String url = "jdbc:mysql://localhost:3306/ej1";
    final static String userName = "root";
    final static String password = "admin";
    final static String instSQLUpdate = "create table PersonasPais(id int primary key," +
            " nombre varchar(25), edad tinyint, nombrePais varchar(25), tamnno varchar(25))";
    final static String instSQLConsult = "select persona.id, persona.nombre," +
            "persona.edad,pais.nombrePais,pais.tamañoPais from persona join pais on pais.idPais = persona.id;";

    public static void main(String[] args) {
        try {
            dBConection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void dBConection() throws SQLException {
        Connection conectionJnr = DriverManager.getConnection(url, userName, password);
        Statement st = conectionJnr.createStatement();
        Statement st2 = conectionJnr.createStatement();
        consultationsSQL(st, st2);
    }

    static void consultationsSQL(Statement st, Statement st2) throws SQLException {
        /*
         * create table if exists db*/
        st.executeUpdate(instSQLUpdate);
        ResultSet rst = st.executeQuery(instSQLConsult);
        int id, edad;
        String nombre, nombrePais, tamnnoPais, instSQLInsert;
        while (rst.next()) {
            id = rst.getInt("id");
            nombre = rst.getString("nombre");
            edad = rst.getInt("edad");
            nombrePais = rst.getString("nombrePais");
            tamnnoPais = rst.getString("tamañoPais");

            instSQLInsert = "insert into PersonasPais values(" + id + "," +
                    "'" + nombre + "'" + "," + edad + "," + "'" + nombrePais +
                    "'" + "," + "'" + tamnnoPais + "'" + ");";
            insertTable(st2, instSQLInsert);
        }
    }

    static void insertTable(Statement st, String instSQLInsert) throws SQLException {
        st.executeUpdate(instSQLInsert);
    }
}