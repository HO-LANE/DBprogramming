package CONTROL;

import SERVICE.AcceptOrderService;
import VIEW.AcceptOrderView;
public class AcceptOrder {
    private AcceptOrderView view;
    private AcceptOrderService service;
    public AcceptOrder(){
        view = new AcceptOrderView();
        service = new AcceptOrderService();
    }
    public void execute(){
        int orderId = view.askOrderId();
        service.acceptOrder(orderId);
    }
}