package VIEW;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class RegisterOrderView {
    private Scanner input;
    public RegisterOrderView(){
        input = new Scanner(System.in);
    }
    public String askMenuName(){
        System.out.print("menu name: ");
        return input.next().trim();
    }

    public List<Integer> askOptionNums(){
        System.out.print("option number: 0을 입력하면 종료");
        List<Integer> optionNums = new ArrayList<>();
        for(int current = input.nextInt(); current != 0; current = input.nextInt())
            optionNums.add(current);
        return optionNums;
    }
}
