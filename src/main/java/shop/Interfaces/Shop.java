package shop.Interfaces;

import shop.classes.ProductImpl;

import java.util.List;

public interface Shop {
    List<ProductImpl> filterByName(String name);
    List<ProductImpl> filterByCategory(String category);
    List<ProductImpl> filterByPrice(Double  min, Double max);

    void newProduct(ProductImpl product);
}
