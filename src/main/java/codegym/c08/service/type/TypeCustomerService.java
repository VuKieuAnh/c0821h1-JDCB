package codegym.c08.service.type;

import codegym.c08.config.ConnectionSingleton;
import codegym.c08.model.TypeCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeCustomerService implements ITypeService {



    @Override
    public List<TypeCustomer> findAll() {
        Connection connection = ConnectionSingleton.getConnection();
        List<TypeCustomer> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from type;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list.add(new TypeCustomer(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(TypeCustomer customer) {

    }

    @Override
    public TypeCustomer findById(int id) {
        Connection connection = ConnectionSingleton.getConnection();
        TypeCustomer typeCustomer = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from type where id = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
//                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                typeCustomer = new TypeCustomer(id, name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return typeCustomer;
    }

    @Override
    public void update(int id, TypeCustomer customer) {

    }

    @Override
    public void remove(int id) {

    }
}

