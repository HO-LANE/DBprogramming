package SERVICE;

import DAO.ReviewDAO;
import DTO.ReviewDTO;

import java.util.List;

public class SearchReviewService {
    private ReviewDAO reviewDao;
    public SearchReviewService(){
        reviewDao = new ReviewDAO();
    }
    public List<ReviewDTO> selectReview(int row, int lineCount){
        return reviewDao.selectAllReview(row, lineCount);
    }
}
