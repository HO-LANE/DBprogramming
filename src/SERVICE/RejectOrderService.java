package SERVICE;

import DAO.OrdersDAO;
public class RejectOrderService {
    private OrdersDAO OrdersDao;
    public RejectOrderService(){
        OrdersDao = new OrdersDAO();
    }
    public void rejectOrder(int orderId){
        OrdersDao.rejectOrder(orderId);
    }
}
