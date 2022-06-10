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
    private CustomerPreparedDAO cDAO = new CustomerPreparedDAO();
    
    public void insertDataCustomer(Customer c){
        cDAO.insertCustomer(c);
    }
    
    public String showDataCustomer(){
        List<Customer> dataCustomer = cDAO.showCustomer();
        
        String customerString = "";
        for(int i=0; i<dataCustomer.size(); i++){
            customerString += dataCustomer.get(i).showDataCustomer();
        }
        
        return customerString;
    }
    
//    public String showDataDesktop(){
//        List<Computer> dataComputer = cDao.showComputer();
//        
//        String computerString = "";
//        for(int i=0; i<dataComputer.size(); i++){
//            computerString += dataComputer.get(i).showDataDesktop();
//        }
//        
//        return computerString;
//    }
    
    public List<Customer> searchCustomer(String query){
        return cDAO.searchCustomer(query);
    }
    
    public void updateDataCustomer(Customer c){
        cDAO.updateCustomer(c);
    }
    
    public void deleteDataCustomer(int id){
        cDAO.deleteCustomer(id);
    }
    
    public TableCustomer getTableCustomer(String query) {
        TableCustomer tableC = new TableCustomer(cDAO.searchCustomer(query));
        return tableC;
    }
    
    public List<Customer> showListAllCustomer(){
        List<Customer> dataCustomer = cDAO.showCustomer();
        
        return dataCustomer;
    }
}
