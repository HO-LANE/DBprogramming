package CONTROL;

import SERVICE.RejectOrderService;
import VIEW.RejectOrderView;
public class RejectOrder {
    private RejectOrderView view;
    private RejectOrderService service;
    public RejectOrder(){
        view = new RejectOrderView();
        service = new RejectOrderService();
    }
    public void execute(){
        int orderId = view.askOrderId();
        service.rejectOrder(orderId);
    }
}