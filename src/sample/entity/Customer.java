package sample.entity;

public class Customer {
    private int id;
    private String name;
    private int chairnum;
    private int totalItems;
    private int totalPay;
    private int status;

    public Customer(int id, String name, int chairnum, int totalItems, int totalPay, int status) {
        this.id = id;
        this.name = name;
        this.chairnum = chairnum;
        this.totalItems = totalItems;
        this.totalPay = totalPay;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChairnum() {
        return chairnum;
    }

    public void setChairnum(int chairnum) {
        this.chairnum = chairnum;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
