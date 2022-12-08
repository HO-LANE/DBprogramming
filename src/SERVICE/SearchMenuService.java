package SERVICE;

import DAO.AvailableOptionDAO;
import DAO.MenuDAO;
import DAO.MenuOptionDAO;
import DAO.StoreDAO;
import DTO.MenuDTO;

import java.util.List;

public class SearchMenuService {
    private StoreDAO storeDao;
    private MenuDAO menuDao;
    private AvailableOptionDAO availableDao;
    private MenuOptionDAO menuOptionDao;

    public SearchMenuService(){
        storeDao = new StoreDAO();
        menuDao = new MenuDAO();
        availableDao = new AvailableOptionDAO();
        menuOptionDao = new MenuOptionDAO();
    }
    
    public int getStoreId(String storeName){
        return storeDao.selectStoreId(storeName);
    }

    public List<String> getCategorys(int storeId){
        return menuDao.selectAllCategory(storeId);
    }

    public List<MenuDTO> selectAllMenuByCatetory(String category){
        return menuDao.selectAllMenuByCatetory(category);
    }

    public List<Integer> getOptionNum(int menuId){
        return availableDao.selectOptionNum(menuId);
    }

    public String getOptionName(int optionNum){
        return menuOptionDao.selectMenuOptionName(optionNum);
    }
}
