package christmas.view;

import christmas.Order.Badge;
import christmas.menu.Menu;

import java.util.Map;

public class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printWelcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE_PREFIX + message);
    }

    public void printResultStart(int day) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printMenus(Map<Menu, Integer> orderMenuList) {
        System.out.println("\n<주문 메뉴>");

        for (Map.Entry<Menu, Integer> entry : orderMenuList.entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue() + "개");
        }
    }

    public void printOriginalTotalPrice(int originalTotalPrice) {
        System.out.println("\n<할인 전 총주문 금액>");

        System.out.println(String.format("%,d", originalTotalPrice) + "원");
    }

    public void printGift(boolean hasGift, String giftItem, int giftCount) {
        System.out.println("\n<증정 메뉴>");

        if (hasGift) {
            System.out.println(giftItem + " " + giftCount + "개");
            return;
        }

        System.out.println("없음");
    }

    public void printAdvantages(Map<String, Integer> advantageList) {
        System.out.println("\n<혜택 내역>");

        if (advantageList.isEmpty()) {
            System.out.println("없음");
            return;
        }

        for (Map.Entry<String, Integer> entry : advantageList.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.format("%,d", entry.getValue()) + "원");
        }
    }

    public void printTotalDiscount(int totalDiscount) {
        System.out.println("\n<총혜택 금액>");

        System.out.println(String.format("%,d", totalDiscount) + "원");
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>");

        System.out.println(String.format("%,d", totalPrice) + "원");
    }

    public void printBadge(Badge badge) {
        System.out.println("\n<12월 이벤트 배지>");

        System.out.println(badge.getName());
    }
}
