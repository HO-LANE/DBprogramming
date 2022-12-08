package VIEW;

import java.util.Scanner;

public class RegisterUserView {
    private Scanner input;
    public RegisterUserView(){
        input = new Scanner(System.in);
    }
    public String askId(){
        System.out.print("ID: ");
        return input.nextLine().trim();
    }

    public String askPassWd(){
        System.out.print("password: ");
        return input.nextLine().trim();
    }

    public String askName(){
        System.out.print("name: ");
        return input.nextLine().trim();
    }

    public String askPhoneNum(){
        System.out.print("phone number: ");
        return input.next().trim();
    }
}
