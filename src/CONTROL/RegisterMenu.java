package CONTROL;

import SERVICE.RegisterMenuService;
import VIEW.RegisterMenuView;

import java.util.List;

public class RegisterMenu {
    private RegisterMenuView view;
    private RegisterMenuService service;
    public RegisterMenu(){
        view = new RegisterMenuView();
        service = new RegisterMenuService();
    }
    public void execute(){
        String name = view.askMenuName();
        String category = view.askCategory();
        int price = view.askPrice();
        int quantity = view.askQuantity();
        // int id = view.askStoreId();
        
        String storeName = view.askStoreName();
        int id = service.getStoreId(storeName);
        List<Integer> optionNums = view.askOptions();
        service.insertMenu(name, category, price, quantity, id);
        for(Integer optionNum : optionNums)
            service.insertAvailableOption(optionNum, 1);
    }
}
