package API_Recap.ECommerceRecap;

public class OrderReq {
    private String country;
    private String productOrderedId;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getproductOrderedId() {
        return productOrderedId;
    }

    public void setproductOrderedId(String productId) {
        this.productOrderedId = productId;
    }
}
