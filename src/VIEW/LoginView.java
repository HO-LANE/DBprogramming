package VIEW;

import java.util.Scanner;

public class LoginView {
    private Scanner input;
    public LoginView(){
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

    public void printSuccess(){
        System.out.println("success!!");
    }

    public void printFail(){
        System.out.println("fail!!");
    }
}
