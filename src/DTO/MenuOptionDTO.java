package DTO;

//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@ToString
public class MenuOptionDTO {
    private int optionId;
    private String optionName;
    private int price;
    private int optionNum;

    @Override
    public String toString() {
        return "MenuOptionDTO{" +
                "optionId=" + optionId +
                ", optionName='" + optionName + '\'' +
                ", price=" + price +
                ", optionNum=" + optionNum +
                '}';
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }
}
