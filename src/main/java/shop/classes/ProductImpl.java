package shop.classes;

import shop.Interfaces.Product;
import shop.builders.ProductBuilder;

//TODO Single Responsibility Principle
public class ProductImpl implements Product {

    private Long id;
    private String name;
    private Double price;
    private Integer count;
    private String category;
    private Integer rating;
    private String maker;

    public ProductImpl(Long id, String name, Double price, Integer count, String category, Integer rating, String maker) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.category = category;
        this.rating = rating;
        this.maker = maker;
    }

    public ProductImpl(ProductBuilder productBuilder) {
        this.id = productBuilder.getId();
        this.name = productBuilder.getName();
        this.price = productBuilder.getPrice();
        this.count = productBuilder.getCount();
        this.category = productBuilder.getCategory();
        this.rating = productBuilder.getRating();
        this.maker = productBuilder.getMaker();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "Наименование: " + this.name +
                "\nКатегория: " + this.category +
                "\nЦена: " + this.price +
                "\nДоступное количество: " + this.count +
                "\nРейтинг: " + this.rating +
                "\nПроизводитель: " + this.maker +
                "\n---------------------------\n";
    }
}
