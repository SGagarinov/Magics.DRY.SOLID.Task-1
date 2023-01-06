package shop.classes;

import shop.Interfaces.Product;
import shop.Interfaces.Shop;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ShopImpl extends ProductList implements Shop {

    private final String helloMessage = "Приветствую покупатель! В магазине представлены следующие товары:";
    private String name;
    private String address;
    private String owner;

    public ShopImpl(String name, String address, List<ProductImpl> products, String owner) {
        this.name = name;
        this.address = address;
        this.products = products;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProducts(List<ProductImpl> products) {
        this.products = products;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public List<ProductImpl> filterByName(String name) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductImpl> filterByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().toLowerCase().contains(category.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductImpl> filterByPrice(Double min, Double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    @Override
    public void newProduct(ProductImpl product) {
        products.add(product);
    }

}
