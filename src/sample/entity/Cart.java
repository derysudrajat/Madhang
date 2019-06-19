package sample.entity;

public class Cart {
    private int id;
    private int totalPrice;
    private int qty;
    private String url;

    public Cart(int id, int totalPrice, int qty, String url) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.qty = qty;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
