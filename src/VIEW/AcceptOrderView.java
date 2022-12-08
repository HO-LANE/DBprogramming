package VIEW;

import java.util.Scanner;
public class AcceptOrderView {
    private Scanner input;
    public AcceptOrderView(){
        input = new Scanner(System.in);
    }
    public int askOrderId(){
        System.out.print("accept order id: ");
        return input.nextInt();
    }
}
