package christmas.controller;

import christmas.Order.Order;
import christmas.menu.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;
import java.util.Map;

import static christmas.menu.MenuType.*;

public class PromotionController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private HashMap<String, Menu> menuList = new HashMap<>();

    public void run() {
        // 기본 환영 문구 출력
        welcome();

        // 기본 메뉴 등록
        initializeMenu();

        // 입력한대로 주문 생성
        Order custumerOrder = readCustomerOrder();

        //

    }

    private void welcome() {
        outputView.printWelcome();
    }

    private Order readCustomerOrder() {
        // 사용자 입력(예약일, 주문)
        int orderDate = readAndValidateDate();

        while (true) {
            try {
                String orderMenu = readAndValidateOrder();

                // 주문 메뉴, 개수 추출
                Map<Menu, Integer> orderList = extractOrderMenu(orderMenu);

                return new Order(orderDate, orderList);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int readAndValidateDate() {
        while (true) {
            try {
                return inputView.readDate();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private String readAndValidateOrder() {
        while (true) {
            try {
                return inputView.readOrder();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Map<Menu, Integer> extractOrderMenu(String input) {
        // , 구분자로 먼저 분리
        String[] orders = input.split(",");

        // - 구분자로 분리
        Map<Menu, Integer> orderMenus = new HashMap<>();
        int drinkCount = 0;

        for (String order : orders) {
            if (order.isBlank()) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            String[] menuAndCount = order.split("-");

            if (menuAndCount.length != 2) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            // 메뉴 형식에 맞지 않는 경우
            if (!menuAndCount[0].matches("[A-Za-z]+")) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            if (!menuAndCount[1].matches("\\d+")) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }


            // 없는 메뉴를 입력한 경우
            if (!menuList.containsKey(menuAndCount[0])) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            Menu orderMenu = menuList.get(menuAndCount[0]);
            if (orderMenu.getType().equals(DRINK)) {
                drinkCount += 1;
            }

            // 중복된 메뉴를 입력한 경우
            if (orderMenus.containsKey(orderMenu)) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            // 메뉴 개수가 양수가 아닌 경우
            try {
                int count = Integer.parseInt(menuAndCount[1]);

                if (count < 1) {
                    throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
                }

                orderMenus.put(menuList.get(menuAndCount[0]), count);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }

        if (orderMenus.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        // 음료만 주문 불가능
        if (drinkCount == orderMenus.size()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        return orderMenus;
    }

    private void initializeMenu() {
        // 애피타이저
        menuList.put("양송이수프", new Menu("양송이수프", 6000, APPETIZER));
        menuList.put("타파스", new Menu("타파스", 5500, APPETIZER));
        menuList.put("시저샐러드", new Menu("시저샐러드", 8000, APPETIZER));

        // 메인
        menuList.put("티본스테이크", new Menu("티본스테이크", 55000, MAIN));
        menuList.put("바비큐립", new Menu("바비큐립", 54000, MAIN));
        menuList.put("해산물파스타", new Menu("해산물파스타", 35000, MAIN));
        menuList.put("크리스마스파스타", new Menu("크리스마스파스타", 25000, MAIN));

        // 디저트
        menuList.put("초코케이크", new Menu("초코케이크", 15000, DESSERT));
        menuList.put("아이스크림", new Menu("아이스크림", 5000, DESSERT));

        // 음료
        menuList.put("제로콜라", new Menu("제로콜라", 3000, DRINK));
        menuList.put("레드와인", new Menu("레드와인", 60000, DRINK));
        menuList.put("샴페인", new Menu("샴페인", 25000, DRINK));
    }
}
