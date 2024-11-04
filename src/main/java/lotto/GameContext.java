package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.stream.Collectors;

public class GameContext {
    private int budget;
    private int lottoQuantity;
    private List<Lotto> lottos;
    private List<Integer> winningNumbers;

    public GameContext() {
        lottos = new ArrayList<>();
    }

    public void start() {
        System.out.println("구입금액을 입력해 주세요.");
        String inputBudget = Console.readLine();
        budget = parseIntBudget(inputBudget);
        lottoQuantity = budget / 1000;
    }

    public void makeLottos() {
        for (int i = 0; i < lottoQuantity; i++) {
            Lotto lotto = new Lotto(makeLottoNumbers());
            lottos.add(lotto);
        }
    }

    public void setWinning() {
        winningNumbers = inputWinningNumbers();
    }

    private List<Integer> inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요. (1-45, 중복되지 않게, 쉼표로 구분)");
        String input = Console.readLine();
        List<Integer> winningNumbers = parseNumbers(input);
        validateWinningNumbers(winningNumbers);
        return winningNumbers;
    }

    private List<Integer> parseNumbers(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력 값이 비어있습니다.");
        }

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(this::parseSingleNumber) // parseSingleNumber 메소드를 사용하여 유효성 검사 수행
                .collect(Collectors.toList());
    }

    private void validateWinningNumbers(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }

        Set<Integer> uniqueNumbers = new HashSet<>(winningNumbers);
        if (uniqueNumbers.size() != winningNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복된 숫자가 있습니다.");
        }

        for (int number : winningNumbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1에서 45 사이의 숫자여야 합니다.");
            }
        }
    }

    private int parseSingleNumber(String numberStr) {
        try {
            int number = Integer.parseInt(numberStr);
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 번호는 1에서 45 사이의 숫자여야 합니다.");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자(정수) 형식으로 입력해주세요.");
        }
    }

    private int parseIntBudget(String inputBudget) {
        if (inputBudget == null || inputBudget.trim().isEmpty()) {
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

    private List<Integer> makeLottoNumbers() {
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
