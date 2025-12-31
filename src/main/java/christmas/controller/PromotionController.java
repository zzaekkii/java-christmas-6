package christmas.controller;

import christmas.menu.Menu;
import christmas.menu.MenuType;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;

import static christmas.menu.MenuType.*;

public class PromotionController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private HashMap<String, Menu> menuList = new HashMap<>();

    public void run() {
        // 기본 환영 문구 출력
        welcome();

        // 사용자 입력(예약일, 주문)
        int orderDate = readAndValidateDate();
        String orderMenu = readAndValidateOrder();


        /// TODO: 기본 메뉴 등록
        initializeMenu();

        /// TODO: 주문 메뉴와 개수 추출
        extractOrderMenu();


        /// TODO: 결과 출력

    }

    private void welcome() {
        outputView.printWelcome();
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

    private void extractOrderMenu() {
    }
}
