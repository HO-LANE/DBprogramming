package CONTROL;

import SERVICE.RegisterUserService;
import VIEW.RegisterUserView;

public class RegisterUser {
    private RegisterUserView view;
    private RegisterUserService service;
    public RegisterUser(){
        view = new RegisterUserView();
        service = new RegisterUserService();
    }
    public void execute(String id, String passwd, String name, String askPhoneNum){
//        String id = view.askId();
//        String passwd = view.askPassWd();
//        String name = view.askName();
//        String askPhoneNum = view.askPhoneNum();

        service.insertUser(id, passwd, name, askPhoneNum);
    }
}
