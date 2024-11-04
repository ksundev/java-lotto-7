package lotto;

import camp.nextstep.edu.missionutils.Console;

public class GameContext {
    private int budget;
    private int lottoQuantity;

    public GameContext() {
    }

    public void start() {
        System.out.println("구입금액을 입력해 주세요.");
        String inputBudget = Console.readLine();
        budget = parseIntBudget(inputBudget);
        lottoQuantity = budget / 1000;
    }

    int parseIntBudget(String inputBudget) {
        if (inputBudget == null) {
            throw new IllegalArgumentException("[ERROR] 입력 값이 비어있습니다.");
        }
        int budget;
        try {
            budget = Integer.parseInt(inputBudget);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자(정수) 형식으로 입력해주세요.");
        }
        if (budget < 0) {
            throw new IllegalArgumentException("[ERROR] 음수는 입력할 수 없습니다.");
        }
        if (budget % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 1000원 단위로 입력해주세요.");
        }
        return budget;
    }

    public int getLottoQuantity() {
        return lottoQuantity;
    }
}
