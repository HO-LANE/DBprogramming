package VIEW;

import java.util.Scanner;
public class RejectOrderView {
    private Scanner input;
    public RejectOrderView(){
        input = new Scanner(System.in);
    }
    public int askOrderId(){
        System.out.print("reject order id: ");
        return input.nextInt();
    }
}
