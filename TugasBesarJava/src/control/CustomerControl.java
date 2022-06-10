package control;

import dao.CustomerPreparedDAO;
import java.util.List;
import model.Customer;

/**
 *
 * @author Tanto
 */
public class CustomerControl {
    private CustomerPreparedDAO cDao = new CustomerPreparedDAO();
    
    public void insertDataCustomer(Customer c){
        cDao.insertCustomer(c);
    }
    
    public String showDataCustomer(){
        List<Customer> dataCustomer = cDao.showCustomer();
        
        String customerString = "";
        for(int i=0; i<dataCustomer.size(); i++){
            customerString += dataCustomer.get(i).showDataCustomer();
        }
        
        return customerString;
    }
    
    
    
    public List<Customer> searchCustomer(String id){
        List<Customer> c = null;
        c = cDao.searchCustomer(id);
        
        return c;
    }
    
    public void updateDataCustomer(Customer c){
        cDao.updateCustomer(c);
    }
    
    public void deleteDataCustomer(int id){
        cDao.deleteCustomer(id);
    }
    
    public List<Customer> showListAllCustomer(){
        List<Customer> dataCustomer = cDao.showCustomer();
        
        return dataCustomer;
    }
}
