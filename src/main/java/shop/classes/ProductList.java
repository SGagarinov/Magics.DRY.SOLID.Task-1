package shop.classes;

import java.util.List;

public abstract class ProductList {

    protected List<ProductImpl> products;
    private Double fullPrice;

    //TODO Dependency inversion principle
    public List<ProductImpl> getProducts() {
        return products;
    }

    public String viewProduct(List<ProductImpl> products) {
        if (products.size() != 0) {
            StringBuilder builder = new StringBuilder();
            for (ProductImpl product : products) {
                if (product.getCount() != 0)
                    builder.append(product);
            }
            return builder.toString();
        }
        else return "Товары не найдены";
    }

    public Double getFullPrice() {
        Double sum = 0D;
        for (ProductImpl product: products) {
            sum += product.getPrice()*product.getCount();
        }
        return sum;
    }
}
