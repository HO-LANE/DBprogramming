package CONTROL;

import DTO.MenuDTO;
import SERVICE.SearchMenuService;
import VIEW.SearchMenuView;

import java.util.ArrayList;
import java.util.List;
public class SearchMenu {
    private SearchMenuView view;
    private SearchMenuService service;
    public SearchMenu(){
        view = new SearchMenuView();
        service = new SearchMenuService();
    }
    public String printAllMenu(String storeName){
//        String storeName = view.askStoreName();
        int storeId = service.getStoreId(storeName);
        List<String> categorys = service.getCategorys(storeId);
        List<MenuDTO> menus;
        List<Integer> optionNums;
        List<String> optionNames;

        String str = "";

        for (String category : categorys){
            menus = service.selectAllMenuByCatetory(category);
            for(MenuDTO menu: menus) {
                optionNums = service.getOptionNum(menu.getMenuId());
                optionNames = new ArrayList<>();
                for(Integer optionNum : optionNums){
                    optionNames.add(service.getOptionName(optionNum));
                }
                    str = view.printMenu(category, menu.getMenuName(), menu.getPrice(), menu.getQuantity(), optionNames);
            }
        }
        return str;
    }
}
