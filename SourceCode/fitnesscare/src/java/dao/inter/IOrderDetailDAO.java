
package dao.inter;

import java.util.List;
import model.Cart;
import model.OrderDetail;


public interface IOrderDetailDAO {

     void addCartToOrder(List<Cart> listCart, int order_id);

     List<OrderDetail> getOrderDetailByOrder(int orderId);
    
}
