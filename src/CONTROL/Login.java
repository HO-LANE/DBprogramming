package CONTROL;

import SERVICE.LoginService;
import VIEW.LoginView;

public class Login {
    private LoginView view;
    private LoginService service;
    public Login(){
        view = new LoginView();
        service = new LoginService();
    }
    public int execute(String id, String passwd){
//        String id = view.askId();
//        String passwd = view.askPassWd();
        int status = 0;

        String DB_passwd = service.findPasswd(id);
        if (DB_passwd == null || DB_passwd.equals(DB_passwd)){
            view.printFail();
        } else {
            view.printSuccess();
        }
        return status;
    }
}
