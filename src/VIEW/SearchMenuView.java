package VIEW;

import java.util.List;
import java.util.Scanner;
public class SearchMenuView {
    Scanner input;
    public SearchMenuView(){
        input = new Scanner(System.in);
    }
    public String askStoreName(){
        System.out.print("store name : ");
        return input.next().trim();
    }

    public String printMenu(String category, String menuName, int price, int quantity, List<String> optionNames){
        String result = "";
        result += "[ " + category + "]";
        result += " 메뉴명: " + menuName + "price : " + price + " quantity : " + quantity;
        for (String optionName: optionNames) {
            result += " " + optionNames;
        }
        result += "\n";
        return result;
//        System.out.println("[ " + category + "]");
//        System.out.print(" 메뉴명: " + menuName + "price : " + price + " quantity : " + quantity);
//        for(String optionName: optionNames)
//            System.out.println(" " + optionNames);
    }
}
