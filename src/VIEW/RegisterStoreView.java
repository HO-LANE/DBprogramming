package VIEW;

import java.util.Scanner;
public class RegisterStoreView {
    private Scanner input;
    public RegisterStoreView(){
        input = new Scanner(System.in);
    }
    public String askStoreName(){
        System.out.print("store name: ");
        return input.nextLine().trim();
    }

    public String askIntroduce(){
        System.out.print("one line, introduce: ");
        return input.nextLine().trim();
    }

    public String askLocation(){
        System.out.print("one line, location: ");
        return input.nextLine().trim();
    }

    public String askStorePhoneNumber(){
        System.out.print("store phone number: ");
        return input.next().trim();
    }

    public String askOpen(){
        System.out.print("open: ");
        return input.next().trim();
    }

    public String askClose(){
        System.out.print("close: ");
        return input.next().trim();
    }

    public String askOwner(){
        System.out.print("owner id: ");
        return input.next().trim();
    }
}
