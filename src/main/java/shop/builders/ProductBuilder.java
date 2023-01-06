package shop.builders;

import shop.classes.ProductImpl;

public class ProductBuilder {
    private Long id;
    private String name;
    private Double price;
    private Integer count;
    private String category;
    private Integer rating;
    private String maker;

    public ProductBuilder(Long id, String name, Double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public ProductBuilder withCount(Integer count) {
        this.count = count;
        return this;
    }

    public ProductBuilder withRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public ProductBuilder withMaker(String maker) {
        this.maker = maker;
        return this;
    }

    public ProductImpl build() {
        return new ProductImpl(this);
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public String getCategory() {
        return category;
    }

    public Integer getRating() {
        return rating;
    }

    public String getMaker() {
        return maker;
    }

    public Long getId() {
        return id;
    }
}
