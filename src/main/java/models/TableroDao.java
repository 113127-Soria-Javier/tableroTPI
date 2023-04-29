package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableroDao {
    String nombre;
    int a1;
    int a2;
    int a3;
    int b1;
    int b2;
    int b3;
    int c1;
    int c2;
    int c3;
    public void guardar(int[][] cuadros){
        a1 = cuadros[1][1];
        a2 = cuadros[1][2];
        a3 = cuadros[1][3];
        b1 = cuadros[2][1];
        b2 = cuadros[2][2];
        b3 = cuadros[2][3];
        c1 = cuadros[3][1];
        c2 = cuadros[3][2];
        c3 = cuadros[3][3];

        String query = "insert into tablero(a1, a2, a3, b1, b2, b3, c1, c2, c3) values("
                + a1 + ", " + a2 + ", " + a3 + ", " + b1 + ", " + b2 + ", "
                + b3 + ", " + c1 + ", " + c2 + ", " + c3 + ")";

        conectar(query);
    }
    public void conectar(String query){
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String user = "sa";
        String password = "1234";
        String conexionUrl = "jdbc:sqlserver://localhost:1433;databaseName=ajedrez;user=sa;password=1234;encrypt=false;trustServerCertificate=true;";

        Connection connection = null;

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(conexionUrl, user, password);

            //String query = "insert into Prueba(lala) values('hola3')";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception ex){
            Logger.getLogger(TableroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
