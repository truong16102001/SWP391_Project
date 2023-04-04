package dao.inter;

import java.util.List;
import java.util.Map;
import model.Chart;
import model.Order;
import model.StatusOrder;

public interface IOrderDAO {

    Order checkProductOrderByUser(int user_id, int product_id);

    int createNewOrder(int sum, String fullname, String email, String phone, String address, int user_id, String note, String payment_method);

    void updatePaymentMethod(String cod, int order_id);

    Order getOrderByID(int order_id);

    List<Order> getAllOrder();

    List<Order> pagingOrder(int index, int RECORD_PER_PAGE, String key, String status);

    List<StatusOrder> getAllStatusOrder();

    int getNextOrderId();

    //Map<Integer, Integer> getStatisticIncome();
    List<Integer> getStatisticIncome();

    int countOrderByStatus(String key, String status);

    void updateStatusOrder(int user_id, int i, int order_id);

    List<Order> filterOrderByStatus(String statusStr);

    List<Chart> getChartRevenueArea(String start, int day);

    int gettotalOrderByStatus(int i, String start, int numberOfDay);

}
