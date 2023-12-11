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
        boolean isRunning = true;
        String ln = System.lineSeparator();
        List<String> chatlog = new ArrayList<>();
        List<String> phrases = readPhrases();
        while (isRunning) {
            String userInput = scanner.next();
            chatlog.add("Пользователь : " + userInput + ln);
            if (!(STOP.equalsIgnoreCase(userInput) || OUT.equalsIgnoreCase(userInput))) {
                String botResponse = returnRandomPhrase(phrases);
                System.out.println(botResponse);
                chatlog.add("Бот : " + botResponse + ln);
            }
            if (STOP.equalsIgnoreCase(userInput)) {
                while (!(CONTINUE.equalsIgnoreCase(userInput)
                        || OUT.equalsIgnoreCase(userInput))) {
                    userInput = scanner.next();
                    chatlog.add("Пользователь : " + userInput + ln);
                }
            }
            if (OUT.equalsIgnoreCase(userInput)) {
                isRunning = false;
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
