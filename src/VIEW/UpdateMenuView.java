package VIEW;
import java.util.Scanner;
public class UpdateMenuView {
    private Scanner input;
    public UpdateMenuView(){
        input = new Scanner(System.in);
    }
    public String askMenuName(){
        System.out.print("menu name: ");
        return input.nextLine().trim();
    }

    public String askMenuNewName(){
        System.out.print("new name: ");
        return input.nextLine().trim();
    }

    public int askPrice(){
        System.out.print("price: ");
        return input.nextInt();
    }
}
