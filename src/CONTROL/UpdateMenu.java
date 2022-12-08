package CONTROL;

import SERVICE.UpdateMenuService;
import VIEW.UpdateMenuView;

public class UpdateMenu {
    private UpdateMenuView view;
    private UpdateMenuService service;
    public UpdateMenu(){
        view = new UpdateMenuView();
        service = new UpdateMenuService();
    }
    public void updateMenuName(String oldMenu, String newMenu){
//        String oldMenu = view.askMenuName();
//        String newMenu = view.askMenuNewName();
        service.updateMenuName(oldMenu, newMenu);
    }

    public void updateMenuPrice(String menu, int newPrice){
//        String oldMenu = view.askMenuName();
//        int price = view.askPrice();
        service.updateMenuPrice(menu, newPrice);

    }

    public void updateMenuNameAndPrice(String oldMenu, String newMenu, int price){
//        String oldMenu = view.askMenuName();
//        String newMenu = view.askMenuNewName();
//        int price = view.askPrice();
        service.updateMenuNameAndPrice(oldMenu, newMenu, price);

    }
}
