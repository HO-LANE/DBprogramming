package SERVICE;

import DAO.StoreDAO;
import DTO.StoreDTO;

import java.util.List;

public class SearchStoreService {
    private StoreDAO storeDao;
    public SearchStoreService(){
        storeDao = new StoreDAO();
    }
    public List<StoreDTO> selectAllStore(){
        return storeDao.selectAllStore();
    }

    public List<StoreDTO> selectAcceptedStore(){
        return storeDao.selectAcceptedStore();
    }
}
