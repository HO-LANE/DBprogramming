package SERVICE;

import DAO.UserDAO;

public class LoginService {
    private UserDAO userDao;
    public LoginService(){
        userDao = new UserDAO();
    }
    public String findPasswd(String id_param){
        return userDao.findPasswd(id_param);
    }
}
