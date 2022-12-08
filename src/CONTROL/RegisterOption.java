package CONTROL;

import SERVICE.RegisterOptionService;
import VIEW.RegisterOptionView;

public class RegisterOption {
    private RegisterOptionView view;
    private RegisterOptionService service;
    public RegisterOption(){
        view = new RegisterOptionView();
        service = new RegisterOptionService();
    }
    public void execute(){
        String optionName = view.askOptionName();
        int price = view.askPrice();
        int optionNum = view.askOptionNum();
        service.insertOption(optionName, price, optionNum);
    }
}
