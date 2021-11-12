package codegym.c08.service.customer;

import codegym.c08.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {

    private static List<Customer> customers = new ArrayList<>();
    static {
        customers.add(new Customer("1", "Dinh", "dinh09@gmail.com", "Ha Noi"));
        customers.add(new Customer("2", "Thai Clone", "thaihue@gmail.com", "Nam Dinh"));
        customers.add(new Customer("3", "Ha", "ha@gmail.com", "Phu Tho"));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
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
