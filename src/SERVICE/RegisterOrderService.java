package SERVICE;

import DAO.CurrentOptionDAO;
import DAO.MenuDAO;
import DAO.OrdersDAO;

public class RegisterOrderService {
    private MenuDAO menuDao;
    private OrdersDAO ordersDao;
    private CurrentOptionDAO currentOptionDao;
    public RegisterOrderService(){
        menuDao = new MenuDAO();
        ordersDao = new  OrdersDAO();
        currentOptionDao = new CurrentOptionDAO();
    }
    public int getOrderId(){
        return ordersDao.getOrderId();
    }

    public int getQuantity(String menuName){
        return menuDao.selectQuantity(menuName);
    }

    public void setOrder(int orderId, String userId, String menuName){
        ordersDao.insertOrder(orderId, userId, menuName);
    }

    public void setOptionAtOrder(int orderId, int optionNum){
        currentOptionDao.insertOption(orderId, optionNum);
    }
}
