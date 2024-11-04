package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameContext {
    private int budget;
    private int lottoQuantity;
    private List<Lotto> lottos;

    public GameContext() {
        lottos = new ArrayList<>();
    }

    public void start() {
        System.out.println("구입금액을 입력해 주세요.");
        String inputBudget = Console.readLine();
        budget = parseIntBudget(inputBudget);
        lottoQuantity = budget / 1000;
    }

    public void progress() {
        for (int i = 0; i < lottoQuantity; i++) {
            Lotto lotto = new Lotto(makeLottoNumbers());
            lottos.add(lotto);
        }
        printLottos();
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
        if (budget <= 0) {
            throw new IllegalArgumentException("[ERROR] 0 또는 음수는 입력할 수 없습니다.");
        }
        if (budget % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 1000원 단위로 입력해주세요.");
        }
        return budget;
    }

    public int getLottoQuantity() {
        return lottoQuantity;
    }

    List<Integer> makeLottoNumbers() {
        Set<Integer> numberSet = new HashSet<>();
        while (numberSet.size() < 6) {
            numberSet.add(Randoms.pickNumberInRange(1, 45));
        }
        return new ArrayList<>(numberSet);
    }

    public void printLottos() {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }
}
