package SERVICE;

import DAO.ReviewDAO;

public class RegisterReviewService {
    private ReviewDAO reviewDao;

    public RegisterReviewService(){
        reviewDao = new ReviewDAO();
    }
    public void insertReview(int orderId, int star, String message){
        reviewDao.insertReview(orderId, star, message);
    }
}
