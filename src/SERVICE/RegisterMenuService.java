package SERVICE;

import DAO.AvailableOptionDAO;
import DAO.MenuDAO;
import DAO.StoreDAO;

public class RegisterMenuService {
    private MenuDAO menuDAO;
    private StoreDAO storeDAO;
    private AvailableOptionDAO availableDAO;

    public RegisterMenuService(){
        menuDAO = new MenuDAO();
        storeDAO = new StoreDAO();
        availableDAO = new AvailableOptionDAO();
    }
    public void insertMenu(String menu_name, String category, int price, int quantity, int store_id){
        menuDAO.insertMenu(menu_name, category, price, quantity, store_id);
    }
    public void insertAvailableOption(int option_num, int menu_id){
        availableDAO.insertOption(option_num, menu_id);
    }

    public int getStoreId(String storeName){
        return storeDAO.selectStoreId(storeName);
    }
}
