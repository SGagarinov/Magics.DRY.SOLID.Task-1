package shop.classes;

import shop.Interfaces.Basket;
import shop.Interfaces.Product;

import java.util.ArrayList;
import java.util.List;

public class BasketImpl extends ProductList implements Basket  {

    private Double fullPrice;

    //TODO Open-closed principle
    public void addProduct(ProductImpl product) {
        if (products == null)
            products = new ArrayList<>();
        products.add(product);
        System.out.println("Товар " + product.getName() + " в количестве " + product.getCount() + " добавлен в корзину");
    }

    public String getInfo() {
        if (products == null) return "Корзина пуста";
        return "Список продуктов в заказе: " + viewProduct(products) +
                "Стоимость: " + getFullPrice();
    }

    public void removeProduct(Long id) {
        if (products == null)
            products = new ArrayList<>();
        ProductImpl product = products.stream().filter(p -> p.getId() == id).findFirst().get();
        products.remove(product);
        System.out.println("Товар " + product.getName() + " в количестве " + product.getCount() + " удален из корзины");
    }

}
