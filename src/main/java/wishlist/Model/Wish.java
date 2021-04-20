package wishlist.Model;

public class Wish {

    //Attributes for Wishcreation and displaying Wishlist
    private int id;
    private String name;
    private String price;
    private String url;

    public Wish(String name, String price, String url){
        this.name=name;
        this.price=price;
        this.url=url;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Name: "+name + "\nPrice: " + price + "\nURL: "+url+"\n\n";
    }
}
