package SERVICE;

import DAO.StoreDAO;

public class RegisterStoreService {
    private StoreDAO storeDao;
    public RegisterStoreService(){
        storeDao = new StoreDAO();
    }
    public void insertStore(String store_name_param, String introduce_param, String location_param, String store_phone_number_param, String open, String close, String owner){
        storeDao.insertStore(store_name_param, introduce_param, location_param, store_phone_number_param, open, close, owner);
    }
}
