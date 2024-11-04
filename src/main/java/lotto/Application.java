package lotto;

public class Application {
    public static void main(String[] args) {
        GameContext game = new GameContext();
        game.start();
        System.out.printf("\n%d개를 구매했습니다.\n", game.getLottoQuantity());
        game.makeLottos();
        game.printLottos();
        game.setWinning();
    }
}
