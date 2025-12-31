package christmas.view;

import christmas.Order.Order;

public class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
    }

    public void printWelcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE_PREFIX + message);
    }

    public void printResultStart(Order custumerOrder) {
        int day = custumerOrder.getDay();
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }
}
