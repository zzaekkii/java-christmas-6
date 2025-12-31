package christmas.Order;

import christmas.menu.Menu;
import christmas.menu.MenuType;

import java.util.Map;

import static christmas.menu.MenuType.DESSERT;
import static christmas.menu.MenuType.MAIN;

public class Order {

    // 12월 전용으로 설계하여 "일자"만 저장
    private final int day;
    private final Map<Menu, Integer> orderMenuList;

    public Order(int day, Map<Menu, Integer> orderMenuList) {
        this.day = day;
        this.orderMenuList = orderMenuList;
    }

    // 일, 월, 화, 수, 목, 금, 토
    private static final int[] WEEK_PATTERN = {3, 4, 5, 6, 7, 1, 2};
    private static final int CHRISTMAS = 25;

    public boolean isWeekend() {
        for (int i = 0; i <= 24; i += 7) {
            // 금(5), 토(6)가 주말
            if (day == WEEK_PATTERN[5] + i || day == WEEK_PATTERN[6] + i) {
                return true;
            }
        }

        return false;
    }

    public boolean isSpecial() {
        if (day == CHRISTMAS) {
            return true;
        }

        for (int i = 0; i <= 24; i += 7) {
            // 일요일(0)과 크리스마스가 특별 할인하는 날
            if (day == WEEK_PATTERN[0] + i) {
                return true;
            }
        }

        return false;
    }

    public int getDay() {
        return day;
    }

    public Map<Menu, Integer> getOrderMenuList() {
        return orderMenuList;
    }

    public int getOriginalTotalPrice() {
        int total = 0;

        for (Map.Entry<Menu, Integer> entry : orderMenuList.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }

        return total;
    }

    public int getMainCount() {
        int count = 0;

        for (Map.Entry<Menu, Integer> entry : orderMenuList.entrySet()) {
            if (MAIN.equals(entry.getKey().getType())) {
                count += entry.getValue();
            }
        }

        return count;
    }

    public int getDessertCount() {
        int count = 0;

        for (Map.Entry<Menu, Integer> entry : orderMenuList.entrySet()) {
            if (DESSERT.equals(entry.getKey().getType())) {
                count += entry.getValue();
            }
        }

        return count;
    }
}
