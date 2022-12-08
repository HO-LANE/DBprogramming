package CONTROL;

import SERVICE.RegisterOrderService;
import VIEW.RegisterOrderView;

import java.util.List;

public class RegisterOrder {
    private RegisterOrderView view;
    private RegisterOrderService service;
    public RegisterOrder(){
        view = new RegisterOrderView();
        service = new RegisterOrderService();
    }
    public void execute(){
        String menuName = view.askMenuName();
        List<Integer> optionNums = view.askOptionNums();
        
        int quantity = service.getQuantity(menuName);
        if (quantity > 0){
            int orderId = service.getOrderId();
            service.setOrder(orderId, "임시 아이디", menuName);
            for(int optionNum : optionNums)
            service.setOptionAtOrder(orderId, optionNum);
        } else {
            System.out.println("재료 소진");
        }
    }
}
