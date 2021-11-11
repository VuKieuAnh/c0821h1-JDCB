package codegym.c08.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static Connection connection;

    public static Connection getConnection(){
        if (connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/c08h1",
                        "root",
                        "123456@Abc");
                System.out.println("ket noi thanh cong");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Ket noi khong thanh cong");
            }
        }
        return connection;
    }

}
