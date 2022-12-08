package VIEW;

import DTO.StoreDTO;

import java.util.List;

public class SearchStoreView {
    public SearchStoreView(){
    }
    public void printAllStore(List<StoreDTO> dtos){
        System.out.println("all store ");
        for (StoreDTO dto : dtos)
            System.out.println(dto.toString());
        
    }

    public String printAcceptedStore(List<StoreDTO> dtos){
        String result = "";
        result += "store name ";
        for (StoreDTO dto : dtos) {
            result += dto.toString();
        }
        return result;
    }
}
