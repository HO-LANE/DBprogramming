package VIEW;

import java.util.List;
import java.util.Scanner;
public class SearchOrdersView {
    Scanner input;
    public SearchOrdersView(){
        input = new Scanner(System.in);
    }
    public String printAll(){
        System.out.print("store name : ");
        return input.next().trim();
    }

    public void printMenu(String category, String menuName, int price, int quantity, List<String> optionNames){
        System.out.println("[ " + category + "]");
        System.out.print(" 가게명: " + menuName + "price : " + " quantity : " + quantity);
        for(String optionName: optionNames)
            System.out.println(" " + optionNames);
    }

}
