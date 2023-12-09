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
        boolean isrunning = true;
        List<String> chatlog = new ArrayList<>();
        String userInput = scanner.next();
        while (isrunning) {
            while (!(userInput.equalsIgnoreCase(STOP) || userInput.equalsIgnoreCase(OUT))) {
                chatlog.add("Пользователь : " + userInput + System.lineSeparator());
                String botResponse = returnRandomPhrase(readPhrases());
                System.out.println(botResponse);
                chatlog.add("Бот : " + botResponse + System.lineSeparator());
                userInput = scanner.next();
            }
            if (userInput.equalsIgnoreCase(OUT)) {
                chatlog.add("Пользователь : " + userInput + System.lineSeparator());
                isrunning = false;
            }
            if (userInput.equalsIgnoreCase(STOP)) {
                chatlog.add("Пользователь : " + userInput + System.lineSeparator());
                userInput = scanner.nextLine();
                while (!(userInput.equalsIgnoreCase(CONTINUE)
                        || userInput.equalsIgnoreCase(OUT))) {
                    userInput = scanner.nextLine();
                    chatlog.add("Пользователь : " + userInput + System.lineSeparator());
                }
            }
        }
        saveLog(chatlog);

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

    private String returnRandomPhrase(List<String> botWords) {
        int index = new Random().nextInt(botWords.size());
        return botWords.get(index);
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))) {
            bufferedWriter.write(log.toString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src/data/chatlog.txt", "src/data/botAnswers.txt");
        cc.run();
    }

}
