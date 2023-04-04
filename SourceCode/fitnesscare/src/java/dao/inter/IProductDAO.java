package dao.inter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public interface IProductDAO {

    Product getProductNew();

    List<Product> pagingProduct(int index, int recordPerPage, String searchKey, String cp_id, String type, String value);

    ArrayList<Product> search(String keyword, int cid);

    int getTotalPublishedProduct();

    int getTotalProduct();

    ArrayList<Product> getAllProduct();

    ArrayList<String> getProductImage(int product_id);

    Product getProductByID(int product_id);

    List<Product> getRelatedProduct(int product_id, int category_id);

    Product getProductById(int product_id);

    int countProductByCondition(String searchKey, String cp_id);

    int getTotalProduct(String end);

    int getTotalProductByCategory(int i, String end);

    void addImg(String img);

    ArrayList<Product> searchByName(String name);

    void deleteProduct(int product_id);

    void deleteProductImg(int product_id);

    void updateProduct(Product model);

    java.sql.Date getCurrentDate();

    int searchIdProduct(String product_name, int original_price, int sale_price, int quantity, boolean status, int cp_id, Date updte_date);

    int searchIdImg(String img_url);

    void addProductImg(int product_id, int img_id);

    void updateProduct(int product_id, String product_name, int original_price, int sale_price, String pDescription, String brief_info, int quantity, boolean status, int cp_id, java.sql.Date updte_date);

    void addProduct(String product_name, int original_price, int sale_price, String pDescription, String brief_info, int quantity, boolean status, int cp_id, java.sql.Date updte_date);

}
