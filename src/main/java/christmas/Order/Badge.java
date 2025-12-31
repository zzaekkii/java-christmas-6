package christmas.Order;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minDiscount;

    Badge(String name, int minDiscount) {
        this.name = name;
        this.minDiscount = minDiscount;
    }

    public static Badge fromDiscount(int totalDiscount) {
        Badge result = NONE;
        for (Badge badge : values()) {
            if (totalDiscount >= badge.minDiscount) {
                result = badge;
            }
        }

        return result;
    }

    public String getName() {
        return name;
    }
}
