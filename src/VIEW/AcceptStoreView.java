package VIEW;

import java.util.Scanner;
public class AcceptStoreView {
    private Scanner input;
    public AcceptStoreView(){
        input = new Scanner(System.in);
    }
    public String askId(){
        System.out.print("accept store name: ");
        return input.nextLine().trim();
    }
}
