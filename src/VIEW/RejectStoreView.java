package VIEW;

import java.util.Scanner;
public class RejectStoreView {
    private Scanner input;
    public RejectStoreView(){
        input = new Scanner(System.in);
    }
    public String askId(){
        System.out.print("reject store name: ");
        return input.nextLine().trim();
    }
}
