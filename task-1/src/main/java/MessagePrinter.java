public class MessagePrinter {

    public void printGuessState(int bulls, int cows) {
        System.out.println("БЫКИ:\uD83D\uDC02 " + bulls);
        System.out.println("КОРОВЫ:\uD83D\uDC04 " + cows);
    }

    public void printWinMessage() {
        System.out.println("Вы победили!");
    }

    public void printLoseMessage() {
        System.out.println("Вы проиграли!");
    }

    public void printHelloMessage() {
        System.out.println("""
                Компьютер задумывает четыре различные цифры из 0,1,2,...9. Вы делаете ходы, чтобы узнать эти цифры и их порядок.
                
                Каждый ход состоит из четырёх цифр, 0 может стоять на первом месте.
                
                В ответ компьютер показывает число отгаданных цифр, стоящих на своих местах (число быков) и число отгаданных цифр, стоящих не на своих местах (число коров).
                """
        );
    }

    public void printStepMessage(int len){
        System.out.println("Введите " + len + " уникальные цифры: ");
    }

    public void printAnswer(String secretNumber) {
        System.out.println("Правильный ответ: " + secretNumber);
    }

    public void printError(String errorMessage) {
        System.out.println("Ошибка: " + errorMessage);
    }
}
