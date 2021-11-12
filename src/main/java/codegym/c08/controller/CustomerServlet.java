package codegym.c08.controller;

import codegym.c08.model.Customer;
import codegym.c08.model.TypeCustomer;
import codegym.c08.service.customer.CustomerServiceDB;
import codegym.c08.service.customer.ICustomerService;
import codegym.c08.service.type.ITypeService;
import codegym.c08.service.type.TypeCustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customers")
public class CustomerServlet extends HttpServlet {

    ICustomerService customerService = new CustomerServiceDB();
    ITypeService typeService = new TypeCustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null)
        {
            showAllCustomer(request, response);
        }
        else {
            if (action.equals("create"))
            //goi trang tao moi
            showFormNewCustomer(request, response);
        }
    }

    private void showFormNewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //goi trang danh sach
        request.setAttribute("typecus", typeService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showAllCustomer(HttpServletRequest request, HttpServletResponse response){
        //goi trang danh sach
        List<Customer> customers = customerService.findAll();
        request.setAttribute("ds", customers);
        request.setAttribute("name", "Hieu Dao");
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("create")){
            createNewCustomer(request, response);
        }
    }

    private void createNewCustomer(HttpServletRequest request, HttpServletResponse response) {
        //b1: lay du lieu
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String typeids = request.getParameter("type");
        int typeid = Integer.parseInt(typeids);
        Customer newC = new Customer(id, name, address, email);
        TypeCustomer typeCustomer = typeService.findById(typeid);
        newC.setTypeCustomer(typeCustomer);
        //b2: goi service them moi
        customerService.save(newC);
        //b3: quay tro ve trang danh sach
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/")
    }
}
