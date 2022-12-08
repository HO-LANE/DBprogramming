package CONTROL;

import SERVICE.AcceptStoreService;
import VIEW.AcceptStoreView;
public class AcceptStore {
    private AcceptStoreView view;
    private AcceptStoreService service;
    public AcceptStore(){
        view = new AcceptStoreView();
        service = new AcceptStoreService();
    }
    public void execute(){
        String id = view.askId();
        service.acceptStore(id);
    }
}
