package shop.enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum OrderStatus {
    ACTIVE("Активный"),
    COMPLETED("Выполнен"),
    CANCELLED("Отменён");

    private String name;

    private static final Map<String,OrderStatus> ENUM_MAP;

    OrderStatus (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // Build an immutable map of String name to enum pairs.
    // Any Map impl can be used.

    static {
        Map<String,OrderStatus> map = new ConcurrentHashMap<String, OrderStatus>();
        for (OrderStatus instance : OrderStatus.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static OrderStatus get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
