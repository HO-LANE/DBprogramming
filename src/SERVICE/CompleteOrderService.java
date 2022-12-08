package SERVICE;

import DAO.OrdersDAO;
public class CompleteOrderService {
    private OrdersDAO OrdersDao;
    public CompleteOrderService(){
        OrdersDao = new OrdersDAO();
    }
    public void completeOrder(int orderId){
        OrdersDao.completeOrder(orderId);
    }
}
