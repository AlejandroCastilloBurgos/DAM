import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Practica0 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EMP", "root", "");
            System.out.println("Conexion correcta");

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT ENAME, DEPTNO FROM EMP WHERE DEPTNO = 30");

            while (rs.next()) {
                String nombre = rs.getString("ENAME");
                int dept = rs.getInt("DEPTNO");
                System.out.println(" " + nombre + " " + dept);
            }

            rs.close();
            st.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Practica0.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
