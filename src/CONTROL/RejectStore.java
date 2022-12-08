package CONTROL;

import SERVICE.RejectStoreService;
import VIEW.RejectStoreView;
public class RejectStore {
    private RejectStoreView view;
    private RejectStoreService service;
    public RejectStore(){
        view = new RejectStoreView();
        service = new RejectStoreService();
    }
    public void execute(){
        String id = view.askId();
        service.rejectStore(id);
    }
}
