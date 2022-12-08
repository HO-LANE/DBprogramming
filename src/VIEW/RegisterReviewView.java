package VIEW;

import java.util.Scanner;
public class RegisterReviewView {
    private Scanner input;
    public RegisterReviewView(){
        input = new Scanner(System.in);
    }
    public int askOrderId(){
        System.out.print("order id: ");
        return input.nextInt();
    }

    public int askStar(){
        System.out.print("star: ");
        return input.nextInt();
    }

    public String askMessage(){
        System.out.print("한줄평: ");
        return input.nextLine();
    }
}
