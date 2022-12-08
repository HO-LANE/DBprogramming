package VIEW;

import java.util.Scanner;
public class CompleteOrderView {
    private Scanner input;
    public CompleteOrderView(){
        input = new Scanner(System.in);
    }
    public int askOrderId(){
        System.out.print("complete order id: ");
        return input.nextInt();
    }
}
