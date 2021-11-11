package codegym.c08.service;

import codegym.c08.config.ConnectionSingleton;
import codegym.c08.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceDB implements ICustomerService {

    public Connection getConnection(){
        Connection connection = null;
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
        return connection;
    }

//    public static void main(String[] args) {
//        CustomerServiceDB serviceDB = new CustomerServiceDB();
//        serviceDB.findAll();
//    }

    @Override
    public List<Customer> findAll() {
        Connection connection = ConnectionSingleton.getConnection();
        List<Customer> customers = new ArrayList<>();
        // viet query de lay du lieu
        try {
            //cho phep chay cau lenh
            PreparedStatement statement = connection.prepareStatement("select * from customer;");
            // hung du lieu ve
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                //lay du lieu theo tung truong
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                // tao moi doi tuong
                Customer customer = new Customer(id, name, address, email);
                // them vao list
                customers.add(customer);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // tra ve cho ham
        return customers;
    }

    @Override
    public void save(Customer customer){
        // lay connect
        Connection connection = ConnectionSingleton.getConnection();
        // goi cau query
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into customer (id, name, address, email) value (?, ?, ?, ?);"
            );

            // truyen tham so tuong ung
            preparedStatement.setString(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Customer customer) {

    }

    @Override
    public void remove(int id) {

    }
}
