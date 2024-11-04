package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameContextTest {

    @Test
    void parseIntBudget_ValidInput_ReturnsParsedBudget() {
        GameContext gameContext = new GameContext();
        int budget = gameContext.parseIntBudget("3000");
        assertEquals(3000, budget);
    }

    @Test
    void parseIntBudget_NullInput_ThrowsException() {
        GameContext gameContext = new GameContext();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gameContext.parseIntBudget(null);
        });
        assertEquals("[ERROR] 입력 값이 비어있습니다.", exception.getMessage());
    }

    @Test
    void parseIntBudget_NonNumericInput_ThrowsException() {
        GameContext gameContext = new GameContext();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gameContext.parseIntBudget("abcd");
        });
        assertEquals("[ERROR] 숫자(정수) 형식으로 입력해주세요.", exception.getMessage());
    }

    @Test
    void parseIntBudget_NegativeInput_ThrowsException() {
        GameContext gameContext = new GameContext();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gameContext.parseIntBudget("-1000");
        });
        assertEquals("[ERROR] 0 또는 음수는 입력할 수 없습니다.", exception.getMessage());
    }

    @Test
    void parseIntBudget_NotThousandUnit_ThrowsException() {
        GameContext gameContext = new GameContext();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gameContext.parseIntBudget("1500");
        });
        assertEquals("[ERROR] 1000원 단위로 입력해주세요.", exception.getMessage());
    }

    @Test
    void parseIntBudget_ZeroInput_ThrowsException() {
        GameContext gameContext = new GameContext();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gameContext.parseIntBudget("0");
        });
        assertEquals("[ERROR] 0 또는 음수는 입력할 수 없습니다.", exception.getMessage());
    }

    @Test
    void makeLottoNumbers_shouldGenerateSixUniqueNumbers() {
        GameContext gameContext = new GameContext();
        List<Integer> lottoNumbers = gameContext.makeLottoNumbers();

        assertEquals(6, lottoNumbers.size());
        long uniqueCount = lottoNumbers.stream().distinct().count();
        assertEquals(6, uniqueCount);
        assertTrue(lottoNumbers.stream().allMatch(num -> num >= 1 && num <= 45));
    }
}
