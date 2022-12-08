package SERVICE;

import DAO.UserDAO;

public class RegisterUserService {
    private UserDAO userDao;
    public RegisterUserService(){
        userDao = new UserDAO();
    }
    public void insertUser(String id_param, String passwd_param, String name_param, String phone_num_param){
        userDao.insertUser(id_param, passwd_param, name_param, phone_num_param);
    }
}
