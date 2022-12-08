package CONTROL;

import SERVICE.SearchReviewService;
import VIEW.SearchReviewView;

public class SearchReview {
    private SearchReviewView view;
    private SearchReviewService service;
    public SearchReview(){
        view = new SearchReviewView();
        service = new SearchReviewService();
    }
    public String printAllReview(int count, int lineCount){
//        int count = view.askRow();
//        int lineCount = view.askLineCount();
        return view.printReview(service.selectReview(count, lineCount));
    }
}
