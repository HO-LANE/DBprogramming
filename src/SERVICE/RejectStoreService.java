package SERVICE;

import DAO.StoreDAO;
public class RejectStoreService {
    private StoreDAO storeDao;
    public RejectStoreService(){
        storeDao = new StoreDAO();
    }
    public void rejectStore(String store_id_param){
        storeDao.rejectStore(store_id_param);
    }
}
