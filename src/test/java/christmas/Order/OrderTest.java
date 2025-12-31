package christmas.Order;

import christmas.menu.Menu;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static christmas.menu.MenuType.*;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void 주말이_아니면_false() {
        Order testOrder = new Order(5, null);

        assertThat(testOrder.isWeekend()).isFalse();
    }

    @Test
    void 주말이면_true() {
        Order testOrder = new Order(16, null);

        assertThat(testOrder.isWeekend()).isTrue();
    }

    @Test
    void 크리스마스는_별이_표시된_날() {
        Order testOrder = new Order(25, null);

        assertThat(testOrder.isSpecial()).isTrue();
    }

    @Test
    void 일요일은_별이_표시된_날() {
        Order testOrder = new Order(17, null);

        assertThat(testOrder.isSpecial()).isTrue();
    }

    @Test
    void 평일은_별이_표시되지_않음() {
        Order testOrder = new Order(18, null);

        assertThat(testOrder.isSpecial()).isFalse();
    }

    @Test
    void 주문할_메뉴의_총가격_정상() {
        Map<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(new Menu("a", 3_000, APPETIZER), 2);
        orderMenus.put(new Menu("b", 25_000, MAIN), 1);
        orderMenus.put(new Menu("choco", 8_000, DESSERT), 3);

        Order order = new Order(8, orderMenus);
        int expected = (3_000 * 2) + (25_000 * 1) + (8_000 * 3);

        assertThat(order.getOriginalTotalPrice()).isEqualTo(expected);
    }

    @Test
    void 주문할_메인_메뉴의_개수를_가져온다() {
        Map<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(new Menu("a", 3_000, APPETIZER), 2);
        orderMenus.put(new Menu("b", 25_000, MAIN), 1);
        orderMenus.put(new Menu("choco", 8_000, DESSERT), 3);

        Order order = new Order(8, orderMenus);
        int expected = 1;

        assertThat(order.getMainCount()).isEqualTo(expected);
    }

    @Test
    void 주문할_디저트의_개수를_가져온다() {
        Map<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(new Menu("a", 3_000, APPETIZER), 2);
        orderMenus.put(new Menu("b", 25_000, MAIN), 1);
        orderMenus.put(new Menu("choco", 8_000, DESSERT), 3);

        Order order = new Order(8, orderMenus);
        int expected = 3;

        assertThat(order.getDessertCount()).isEqualTo(expected);
    }
}