package SERVICE;

import DAO.StoreDAO;
public class AcceptStoreService {
    private StoreDAO storeDao;
    public AcceptStoreService(){
        storeDao = new StoreDAO();
    }
    public void acceptStore(String store_id_param){
        storeDao.acceptStore(store_id_param);
    }
}
