package sample.entity;

public class Foods {
    private int id;
    private String name;
    private String desc;
    private int price;
    private float rate;
    private String img;
    private int status;

    public Foods(int id, String name, String desc, int price, float rate, String img, int status) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.rate = rate;
        this.img = img;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
