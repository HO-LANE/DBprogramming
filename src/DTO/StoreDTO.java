package DTO;

//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter

public class StoreDTO {
    private int storeId;
    private String storeName;
    private String introduce;
    private String location;
    private String storeCallNumber;
    private String open;
    private String close;
    private int registed;
    private String owner;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStoreCallNumber() {
        return storeCallNumber;
    }

    public void setStoreCallNumber(String storeCallNumber) {
        this.storeCallNumber = storeCallNumber;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public int getRegisted() {
        return registed;
    }

    public void setRegisted(int registed) {
        this.registed = registed;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String toString(){
        return storeId + " " + storeName + " "  + introduce + " "  + location + " "  + storeCallNumber + " "  + open + " "  + close + " "  + owner + " " + getRegistedStatus() ;
    }

    public String getRegistedStatus(){
        switch(registed){
            case 0 : 
                return "wait";
            case 1 : 
                return "reject";
            default : 
                return "accept";
        }
    }
}
