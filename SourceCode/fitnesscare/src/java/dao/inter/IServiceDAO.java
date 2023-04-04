
package dao.inter;

import java.util.ArrayList;
import model.Service;


public interface IServiceDAO {
    Service getServiceByID(int service_id);
    ArrayList<Service> getAllService();
    int getTotalService();
    int getTotalPublishedService();
    ArrayList<Service> search(String keyword);
    ArrayList<Service> pagingService(int index, int recordPerPage, String searchKey, String type, String value);
    
}
