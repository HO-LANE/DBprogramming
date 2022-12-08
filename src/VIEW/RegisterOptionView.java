package VIEW;

import java.util.Scanner;
public class RegisterOptionView {
    private Scanner input;
    public RegisterOptionView(){
        input = new Scanner(System.in);
    }
    public String askOptionName(){
        System.out.print("option name: ");
        return input.next().trim();
    }

    public int askPrice(){
        System.out.print("price: ");
        return input.nextInt();
    }

    public int askOptionNum(){
        System.out.print("option number: ");
        return input.nextInt();
    }

}
