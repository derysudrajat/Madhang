package sample.entity;

public class Cart {
    private int id;
    private String img;
    private String name;
    private int totalPrice;
    private int qty;

    public Cart(int id, String img, String name, int totalPrice, int qty) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.totalPrice = totalPrice;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
