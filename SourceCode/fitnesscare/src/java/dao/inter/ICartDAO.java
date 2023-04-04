package dao.inter;

import java.util.List;
import model.Cart;

public interface ICartDAO {

    Cart checkCart(int user_id, int product_id);

    void addToCart(int product_id, String product_name, int price, int quantity, int total_cost, int user_id);

    void updateQuantityProductInCart(int product_id, int quantity, float total_cost, int user_id);

    List<Cart> getCartInfoByUserId(int user_id);
    
    List<String> getImageProduct(int product_id);

     void updateQuantityCart(int quantity, int cart_id, int product_id,int sum);

     void deleteCart(int product_id, int user_id);

     int getTotalItemInCart(int user_id);

     void deleteCartByUserId(int user_id);

     Cart getCartByCartID_ProductID(int cart_id, int product_id);
}
