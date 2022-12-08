package SERVICE;

import DAO.MenuOptionDAO;

public class RegisterOptionService {
    private MenuOptionDAO menuOptionDAO;
    public RegisterOptionService(){
        menuOptionDAO = new MenuOptionDAO();
    }
    public void insertOption(String option_name_param, int price_param, int option_num_param){
        menuOptionDAO.insertOption(option_name_param, price_param, option_num_param);
    }
}
