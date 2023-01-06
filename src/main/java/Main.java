import shop.Interfaces.Buyer;
import shop.Interfaces.Order;
import shop.Interfaces.Product;
import shop.Interfaces.Shop;
import shop.builders.ProductBuilder;
import shop.classes.*;
import shop.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final String EXIT_MESSAGE = "Приходите к нам снова!";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Создаём магазин
        ShopImpl shop = new ShopImpl("DNS", "г. Казань, ул. Баумана, д. 68", new ArrayList<>(), "Дмитрий Алексеев");
        //Заполняем товарами
        ProductImpl product1 = new ProductBuilder(12L, "Nokia T20", 16987.99, "Tablet").withCount(5).withRating(4).build();
        ProductImpl product2 = new ProductBuilder(21L, "PLA-пластик", 1299.00, "3D printing").withCount(1).withRating(3).build();
        ProductImpl product3 = new ProductBuilder(33L, "Xiaomi", 13839.00, "phone").withCount(3).withRating(5).withMaker("Xiaomi Inc").build();
        ProductImpl product4 = new ProductBuilder(7L, "Intel i5-12600K", 33000.00, "computer").withCount(5).withRating(5).withMaker("Intel").build();
        shop.newProduct(product1);
        shop.newProduct(product2);
        shop.newProduct(product3);
        shop.newProduct(product4);

        //Создаём покупателя
        BuyerImpl buyer = new BuyerImpl();
        System.out.println("Введите вашу фамилию");
        buyer.setLastName(scanner.nextLine());
        System.out.println("Введите ваше имя");
        buyer.setName(scanner.nextLine());
        System.out.println("Введите ваш возраст");
        buyer.setAge(Integer.parseInt(scanner.nextLine()));
        System.out.println("Введите ваш адрес");
        buyer.setAddress(scanner.nextLine());

        Boolean isExit = false;
        while (!isExit) {
            printMenu();
            int menuInput = Integer.parseInt(scanner.nextLine());
            switch (menuInput) {
                case 1:
                    System.out.println(shop.viewProduct(shop.getProducts()));
                    break;
                case 2:
                    printFilterMenu();
                    int filterInput = Integer.parseInt(scanner.nextLine());
                    switch (filterInput) {
                        case 1:
                            System.out.println("Введите наименование товара");
                            String name = scanner.nextLine();
                            System.out.println(shop.viewProduct(shop.filterByName(name)));
                            break;
                        case 2:
                            System.out.println("Введите наименование категории");
                            String categoty = scanner.nextLine();
                            System.out.println(shop.viewProduct(shop.filterByCategory(categoty)));
                            break;
                        case 3:
                            System.out.println("Введите минимальную цену");
                            Double min = Double.parseDouble(scanner.nextLine());
                            System.out.println("Введите максимальную цену");
                            Double max = Double.parseDouble(scanner.nextLine());
                            System.out.println(shop.viewProduct(shop.filterByPrice(min, max)));
                            break;
                    }
                    break;
                case 3:
                    if (buyer.getBasket() == null)
                        buyer.setBasket(new BasketImpl());

                    printBasketMenu();
                    Integer basketAction = Integer.parseInt(scanner.nextLine());
                    switch (basketAction) {
                        case 1:
                            System.out.println(buyer.getBasket().getInfo());
                            break;
                        case 2:
                            System.out.println("Введите id товара для добавления в корзину");
                            Long basketAddId = Long.parseLong(scanner.nextLine());
                            System.out.println("Введите количество товаров");
                            Integer count = Integer.parseInt(scanner.nextLine());
                            //Ищем товар в магазине
                            ProductImpl product = shop.getProducts().stream()
                                    .filter(p -> p.getId() == basketAddId)
                                    .findFirst()
                                    .get();
                            if (product.getCount() < count) {
                                System.out.println("Количества товаров в магазине недостаточно для заказа.");
                            }
                            else {
                                ProductImpl newProduct = new ProductImpl(product.getId(), product.getName(), product.getPrice(), count,
                                        product.getCategory(), product.getRating(), product.getMaker());
                                buyer.getBasket().addProduct(newProduct);
                                //Уменьшаем количество товаров в магазине
                                product.setCount(product.getCount() - count);
                            }
                            break;
                        case 3:
                            System.out.println("Введите id товара для удаления из корзины");
                            Long basketRemoveId = Long.parseLong(scanner.nextLine());
                            buyer.getBasket().removeProduct(basketRemoveId);
                            break;
                    }
                    break;
                case 4:
                    printOrderMenu();
                    Integer orderAction = Integer.parseInt(scanner.nextLine());
                    switch (orderAction) {
                        case 1:
                            if (buyer.getOrders() != null) {
                                for (OrderImpl order : buyer.getOrders()) {
                                    System.out.println(order.getOrderInfo());
                                }
                            } else System.out.println("У вас нет оформленных заказов");
                            break;
                        case 2:
                            if (buyer.getBasket() == null || buyer.getBasket().getProducts().size() == 0) {
                                System.out.println("В корзине нет товаров");
                            }
                            else {
                                System.out.println("Укажите номер для связи");
                                Long phone = Long.parseLong(scanner.nextLine());
                                OrderImpl newOrder = new OrderImpl((long) (buyer.getOrders().size() + 1), UUID.randomUUID(), buyer.getBasket(), buyer.getBasket().getFullPrice(),
                                        ThreadLocalRandom.current().nextInt(1, 6), buyer.getAddress(), OrderStatus.ACTIVE);
                                buyer.getOrders().add(newOrder);
                                //Очищаем корзину
                                buyer.setBasket(new BasketImpl());
                            }
                            break;
                        case 3:
                            buyer.backOrder(getOrderId(scanner));
                            break;
                        case 4:
                            buyer.repeatOrder(getOrderId(scanner));
                            break;
                    }
                    break;
                case 5:
                    isExit = true;
                    break;
            }
        }

        System.out.println(EXIT_MESSAGE);
    }

    public static void printMenu() {
        System.out.println("Введите номер операции");
        System.out.println("1. Доступные товары.");
        System.out.println("2. Фильтрация товаров.");
        System.out.println("3. Корзина.");
        System.out.println("4. Заказы.");
        System.out.println("5. Выход.");
    }

    public static void printFilterMenu() {
        System.out.println("Выберите тип фильтрации");
        System.out.println("1. По наименованию.");
        System.out.println("2. По категории.");
        System.out.println("3. По цене.");
        System.out.println("4. Назад.");
    }

    public static void printBasketMenu() {
        System.out.println("Выберите действия");
        System.out.println("1. Просмотр корзины.");
        System.out.println("2. Добавить товар в корзину.");
        System.out.println("3. Удалить товар из корзины");
    }

    public static void printOrderMenu() {
        System.out.println("1. Получить информацию о заказах.");
        System.out.println("2. Оформить заказ.");
        System.out.println("3. Отменить заказ.");
        System.out.println("4. Повторить заказ.");
    }

    public static Long getOrderId(Scanner scanner) {
        System.out.println("Введите номер заказа");
        return Long.parseLong(scanner.nextLine());
    }
}
