package ua.nure.melnyk.entity;

public class Product {

    private Long id;
    private String title;
    private String description;
    private Integer price;
    private Long userId;
    private Integer amount = 1;

    public Product() {
    }

    public Product(String title, String description, Integer price, Long userId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}
