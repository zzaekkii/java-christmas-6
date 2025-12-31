package christmas.Order;

import christmas.menu.Menu;

import java.util.List;
import java.util.Map;

public class Order {

    // 12월 전용으로 설계하여 "일자"만 저장
    private final int date;
    private final Map<Menu, Integer> orderMenuList;

    public Order(int date, Map<Menu, Integer> orderMenuList) {
        this.date = date;
        this.orderMenuList = orderMenuList;
    }

    // 일, 월, 화, 수, 목, 금, 토
    private static final int[] WEEK_PATTERN = {3, 4, 5, 6, 7, 1, 2};
    private static final int CHRISTMAS = 25;

    public boolean isWeekend() {
        for (int i = 0; i <= 24; i += 7) {
            // 금(5), 토(6)가 주말
            if (date == WEEK_PATTERN[5] + i || date == WEEK_PATTERN[6] + i) {
                return true;
            }
        }

        return false;
    }

    public boolean isSpecial() {
        if (date == CHRISTMAS) {
            return true;
        }

        for (int i = 0; i <= 24; i += 7) {
            // 일요일(0)과 크리스마스가 특별 할인하는 날
            if (date == WEEK_PATTERN[0] + i) {
                return true;
            }
        }

        return false;
    }

    public int getDate() {
        return date;
    }

    public Map<Menu, Integer> getOrderMenuList() {
        return orderMenuList;
    }
}
