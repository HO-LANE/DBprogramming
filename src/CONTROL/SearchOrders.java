package CONTROL;

import DTO.MenuDTO;
import DTO.MenuOptionDTO;
import DTO.OrdersDTO;
import SERVICE.SearchOrdersService;
import VIEW.SearchOrdersView;

import java.util.List;

public class SearchOrders {
    private SearchOrdersView view;
    private SearchOrdersService service;
    public SearchOrders(){
        view = new SearchOrdersView();
        service = new SearchOrdersService();
    }
    public String printAllOrdersForClient(String user_id) {
        List<OrdersDTO> orders = service.selectOrdersForClient(user_id);
        List<Integer> optionNums = null;
        MenuDTO menuDto;
        MenuOptionDTO menuOptionDto;
        String str = "";

        for(OrdersDTO order: orders){
            optionNums = service.selectCurrentOptionNums(order.getOrderId());
            menuDto = service.selectMenu(order.getMenuName());
            System.out.print(order.toString());
            for(int optionNum : optionNums){
                menuOptionDto = service.selectMenuOption(optionNum);
            }
        }
        return str;
    }
    
    public void printAcceptedStore(){
    }
}
