package shop.Interfaces;

import java.util.List;

//TODO Interface segregation principle, вместо реализации всех методов у Buyer создан отдельный интерфейс для каждого объекта реальной жизни
public interface Basket {
    Double getFullPrice();
}
