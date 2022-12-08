package VIEW;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class RegisterMenuView {
    private Scanner input;
    public RegisterMenuView(){
        input = new Scanner(System.in);
    }
    public String askMenuName(){
        System.out.print("menu name: ");
        return input.nextLine().trim();
    }

    public String askCategory(){
        System.out.print("category: ");
        return input.next().trim();
    }

    public int askPrice(){
        System.out.print("price: ");
        return input.nextInt();
    }

    public int askQuantity(){
        System.out.print("quantity: ");
        return input.nextInt();
    }

    public int askStoreId(){
        System.out.print("store id 언젠가는 store name?: ");
        return input.nextInt();
    }
    public String askStoreName(){
        System.out.print("store name: ");
        String name = input.nextLine();
        if (name.equals(""))
            name = input.nextLine();
        return name.trim();
    }

    public List<Integer> askOptions(){
        System.out.print("원하는 옵션 번호 입력 없으면 -1를 입력");
        List<Integer> options = new ArrayList<>();
        for(int cur = input.nextInt(); cur != -1; cur = input.nextInt())
            options.add(cur);
        return options;
    }
}
