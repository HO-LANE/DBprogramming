package SERVICE;

import DAO.OrdersDAO;
public class AcceptOrderService {
    private OrdersDAO OrdersDao;
    public AcceptOrderService(){
        OrdersDao = new OrdersDAO();
    }
    public void acceptOrder(int orderId){
        OrdersDao.acceptOrder(orderId);
    }
}
