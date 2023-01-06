package shop.classes;

import shop.Interfaces.Basket;
import shop.Interfaces.Buyer;
import shop.Interfaces.Order;
import shop.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BuyerImpl implements Buyer {

    private String lastName;
    private String name;
    private String patronymic;
    private Integer age;
    private Long phone;
    private String address;

    private BasketImpl basket;

    private List<OrderImpl> orders;


    public BuyerImpl() {

    }

    public BuyerImpl(String lastName, String name, String patronymic, Integer age, Long phone, String address) {
        this.lastName = lastName;
        this.name = name;
        this.patronymic = patronymic;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public void backOrder(Long id) {
       OrderImpl order = orders.stream()
               .filter(p -> p.getId() == id)
               .findFirst().get();
       order.setStatus(OrderStatus.CANCELLED);
    }

    @Override
    public void repeatOrder(Long id) {
        OrderImpl order = orders.stream().filter(p -> p.getId() == id).findFirst().get();
        OrderImpl newOrder = new OrderImpl((long) (orders.size() + 1), UUID.randomUUID(), order.getBasket(), order.getPrice(), order.getDeliveryTime(), order.getAddress(), OrderStatus.ACTIVE);
        orders.add(newOrder);
    }

    //Getters and Setters
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BasketImpl getBasket() {
        return basket;
    }

    public void setBasket(BasketImpl basket) {
        this.basket = basket;
    }

    public List<OrderImpl> getOrders() {
        if (orders == null)
            orders = new ArrayList<>();
        return orders;
    }

    public void setOrders(List<OrderImpl> orders) {
        if (orders == null)
            orders = new ArrayList<>();
        this.orders = orders;
    }
}
