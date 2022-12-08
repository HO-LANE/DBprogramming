package SERVICE;

import DAO.MenuDAO;

public class UpdateMenuService {
    private MenuDAO menuDAO;
    public UpdateMenuService(){
        menuDAO = new MenuDAO();
    }
    public void updateMenuName(String oldMenuName, String newMenuName){
        menuDAO.updateMenuName(oldMenuName, newMenuName);
    }

    public void updateMenuPrice(String menuName, int price){
        menuDAO.updateMenuPrice(menuName, price);
    }

    public void updateMenuNameAndPrice(String oldMenuName, String newName, int price){
        menuDAO.updateMenuNameAndPrice(oldMenuName, newName, price);
    }
}
