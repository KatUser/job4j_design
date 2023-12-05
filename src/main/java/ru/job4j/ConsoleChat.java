package ru.job4j;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path; /*для записи логов*/
        this.botAnswers = botAnswers; /*для ответов бота*/
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
       // String input = scanner.nextLine();
        printRandomPhrase(readPhrases());



    }

    private List<String> readPhrases() {
        List<String> botWords = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers))) {
            String word;
            while ((word = bufferedReader.readLine()) != null) {
                botWords.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botWords;
    }

    private void printRandomPhrase(List<String> botWords) {
        int index = new Random().nextInt(botWords.size());
        System.out.println(botWords.get(index));
    }

    private void saveLog(List<String> log) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(path, true))) {
            printWriter.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src/data/chatlog.txt", "src/data/botAnswers.txt");
        cc.run();
    }

}
