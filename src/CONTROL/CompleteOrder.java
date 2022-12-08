package CONTROL;

import SERVICE.CompleteOrderService;
import VIEW.CompleteOrderView;
public class CompleteOrder {
    private CompleteOrderView view;
    private CompleteOrderService service;
    public CompleteOrder(){
        view = new CompleteOrderView();
        service = new CompleteOrderService();
    }
    public void execute(int orderId){
//        int orderId = view.askOrderId();
        service.completeOrder(orderId);
    }
}