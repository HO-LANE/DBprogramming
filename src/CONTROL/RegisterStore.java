package CONTROL;

import SERVICE.RegisterStoreService;
import VIEW.RegisterStoreView;

public class RegisterStore {
    private RegisterStoreView view;
    private RegisterStoreService service;
    public RegisterStore(){
        view = new RegisterStoreView();
        service = new RegisterStoreService();
    }
    public void execute(){
        String name = view.askStoreName();
        String introduce = view.askIntroduce();
        String location = view.askLocation();
        String storePhoneNumber = view.askStorePhoneNumber();
        String open = view.askOpen();
        String close = view.askClose();
        String owner = view.askOwner();

        service.insertStore(name, introduce, location, storePhoneNumber, open, close, owner);
    }
}
