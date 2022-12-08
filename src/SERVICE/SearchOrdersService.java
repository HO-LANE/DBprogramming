package SERVICE;

import DAO.CurrentOptionDAO;
import DAO.MenuDAO;
import DAO.MenuOptionDAO;
import DAO.OrdersDAO;
import DTO.MenuDTO;
import DTO.MenuOptionDTO;
import DTO.OrdersDTO;

import java.util.List;

public class SearchOrdersService {
    private MenuDAO menuDao;
    private CurrentOptionDAO cuurentOptionDao;
    private MenuOptionDAO menuOptionDao;
    private OrdersDAO ordersDao;
    public SearchOrdersService(){
        menuDao = new MenuDAO();
        cuurentOptionDao = new CurrentOptionDAO();
        menuOptionDao = new MenuOptionDAO();
        ordersDao = new OrdersDAO();
    }
    public List<OrdersDTO> selectOrdersForClient(String user_id){
        return ordersDao.selectAllOrdersForClient(user_id);
    }

    public List<Integer> selectCurrentOptionNums(int orderId){
        return cuurentOptionDao.selectOptionNum(orderId);
    }

    public MenuDTO selectMenu(String menuName){
        return menuDao.selectMenu(menuName);
    }

    public MenuOptionDTO selectMenuOption(int optionNum){
        return menuOptionDao.selectMenuOption(optionNum);
    }
}
