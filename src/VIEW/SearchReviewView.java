package VIEW;

import DTO.ReviewDTO;

import java.util.List;
import java.util.Scanner;
public class SearchReviewView {
    Scanner input;
    public SearchReviewView(){
        input = new Scanner(System.in);
    }

    public String printReview(List<ReviewDTO> reviews){
        String result = "";
        for(ReviewDTO review : reviews) {
            result += review.toString();
        }
        return result;
    }

    public int askRow(){
        System.out.print("몇 페이지");
        return input.nextInt();
    }

    public int askLineCount(){
        System.out.print("몇 줄 표현");
        return input.nextInt();
    }
}