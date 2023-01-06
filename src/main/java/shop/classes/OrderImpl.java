package shop.classes;

import shop.Interfaces.Basket;
import shop.Interfaces.Order;
import shop.enums.OrderStatus;

import java.util.UUID;

public class OrderImpl implements Order {

    private Long id;
    private UUID tracking;
    private Basket basket;
    private Double price;
    private Integer deliveryTime;
    private String address;

    private OrderStatus status;

    public OrderImpl(Long id, UUID tracking, Basket basket, Double price, Integer deliveryTime, String address, OrderStatus status) {
        this.id = id;
        this.tracking = tracking;
        this.basket = basket;
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTracking(UUID tracking) {
        this.tracking = tracking;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getOrderInfo() {
        return "Номер заказа: " + id +
                "\nТрек-номер: " + tracking +
                "\nСтоимость заказа: " + price +
                "\nСрок доставки: " + deliveryTime +
                "\nАдрес доставки: " + address +
                "\nСтатус: " + status.getName() +
                "\n-----------------\n";
    }

    @Override
    public UUID getTracking() {
        return null;
    }
}
