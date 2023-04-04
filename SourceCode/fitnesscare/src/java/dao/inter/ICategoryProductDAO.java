package dao.inter;

import java.util.ArrayList;
import model.CategoryProduct;

public interface ICategoryProductDAO {

    ArrayList<CategoryProduct> getAllCategory();

    CategoryProduct getCategoryByID(int cid);

    public ArrayList<CategoryProduct> getProductCategories();
}
