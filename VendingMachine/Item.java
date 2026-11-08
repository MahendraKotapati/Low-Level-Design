package VendingMachine;

public class Item {

    private String id;
    private String name;
    private int price;


    Item(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }       

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public void setPrice(int price) {
        this.price = price;
    }
}
