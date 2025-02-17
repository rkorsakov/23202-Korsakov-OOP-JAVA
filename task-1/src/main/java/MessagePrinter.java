import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagePrinter {
    private static final Logger logger = LoggerFactory.getLogger(MessagePrinter.class);

    public void printGuessState(int bulls, int cows) {
        logger.info("БЫКИ: {} | КОРОВЫ: {}", bulls, cows);
    }

    public void printWinMessage() {
        logger.info("Вы победили!");
    }

    public void printLoseMessage() {
        logger.info("Вы проиграли!");
    }

    public void printHelloMessage() {
        logger.info("""
                Компьютер задумывает четыре различные цифры из 0,1,2,...9. Вы делаете ходы, чтобы узнать эти цифры и их порядок.
                Каждый ход состоит из четырёх цифр, 0 может стоять на первом месте.
                В ответ компьютер показывает число отгаданных цифр, стоящих на своих местах (число быков) и число отгаданных цифр, стоящих не на своих местах (число коров).
                """);
    }

    public void printStepMessage(int len) {
        logger.info("Введите {} уникальные цифры: ", len);
    }

    public void printAnswer(String secretNumber) {
        logger.info("Правильный ответ: {}", secretNumber);
    }

    public void printError(String errorMessage) {
        logger.error("Ошибка: {}", errorMessage);
    }
}
