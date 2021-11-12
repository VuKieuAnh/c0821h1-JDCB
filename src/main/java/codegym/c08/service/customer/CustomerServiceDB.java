package codegym.c08.service.customer;

import codegym.c08.config.ConnectionSingleton;
import codegym.c08.model.Customer;
import codegym.c08.model.TypeCustomer;
import codegym.c08.service.type.ITypeService;
import codegym.c08.service.type.TypeCustomerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceDB implements ICustomerService {

    public static final String CREATECUSTOMER = "insert into customer (id, name, address, email, type_id) value (?, ?, ?, ?, ?);";
    private ITypeService typeService = new TypeCustomerService();
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
                int type_id = resultSet.getInt("type_id");
                TypeCustomer typeCustomer = typeService.findById(type_id);
                // tao moi doi tuong
                Customer customer = new Customer(id, name, address, email);
                customer.setTypeCustomer(typeCustomer);
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
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    CREATECUSTOMER
            );
            CallableStatement statement = connection.prepareCall("afhsgfsa");

            // truyen tham so tuong ung
            preparedStatement.setString(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.getTypeCustomer().getId());

            preparedStatement.executeUpdate();
            connection.commit();

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
