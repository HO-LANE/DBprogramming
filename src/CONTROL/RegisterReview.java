package CONTROL;


import SERVICE.RegisterReviewService;
import VIEW.RegisterReviewView;

public class RegisterReview {
    private RegisterReviewView view;
    private RegisterReviewService service;
    public RegisterReview(){
        view = new RegisterReviewView();
        service = new RegisterReviewService();
    }
    public void execute(){
        int orderId = view.askOrderId();
        int star = view.askStar();
        String message = view.askMessage();
        service.insertReview(orderId, star, message);
    }
}
