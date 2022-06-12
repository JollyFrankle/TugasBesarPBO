package control;

import dao.CustomerPreparedDAO;
import java.util.List;
import model.Customer;
import table.TableCustomer;

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
    
    public TableCustomer getTableCustomer(String query) {
        return new TableCustomer(cDao.searchCustomer(query));
    }
    
    public List<Customer> searchCustomer(String id){
        List<Customer> c = null;
        c = cDao.searchCustomer(id);
        
        return c;
    }
    
    public TableCustomer getSearchToTable(String id){
        List<Customer> dataCustomer = cDao.searchCustomer(id);
        TableCustomer tableCustomer = new TableCustomer(dataCustomer);
        
        return tableCustomer;
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
