package CONTROL;

import SERVICE.SearchStoreService;
import VIEW.SearchStoreView;

public class SearchStore {
    private SearchStoreView view;
    private SearchStoreService service;
    public SearchStore(){
        view = new SearchStoreView();
        service = new SearchStoreService();
    }
    public void printAllStore(){
        view.printAllStore(service.selectAllStore());
    }
    
    public String printAcceptedStore(){
        return view.printAcceptedStore(service.selectAcceptedStore());
    }
}
