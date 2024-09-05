package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// Base class: Game
class Game {
    private int score;
    private int level;
    private int maxAttempts;

    // Constructor
    public Game(int level, int maxAttempts) {
        this.level = level;
        this.maxAttempts = maxAttempts;
        this.score = 0;
    }

    // Getters and Setters
    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }

    public int getLevel() {
        return level;
    }

    public void nextLevel() {
        this.level++;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
}

// Derived class: WordScrambleGame
class WordScrambleGame extends Game {
    private String[] wordList;
    private String currentWord;
    private String scrambledWord;
    private int attemptsLeft;

    // Constructor
    public WordScrambleGame(String[] wordList, int level, int maxAttempts) {
        super(level, maxAttempts);
        this.wordList = wordList;
    }

    // Method to scramble the word
    private String scrambleWord(String word) {
        char[] letters = word.toCharArray();
        Random random = new Random();
        for (int i = 0; i < letters.length; i++) {
            int randomIndex = random.nextInt(letters.length);
            char temp = letters[i];
            letters[i] = letters[randomIndex];
            letters[randomIndex] = temp;
        }
        return new String(letters);
    }

    // Method to start the game
    public void startGame() {
        for (int i = 0; i < getLevel(); i++) {
            setupLevel();
            System.out.println("Level " + (i + 1) + ": Unscramble the word - " + scrambledWord);
            attemptsLeft = getMaxAttempts();

            while (attemptsLeft > 0) {
                System.out.println("Your guess: ");
                Scanner scanner = new Scanner(System.in);
                String guess = scanner.nextLine();

                if (guess.equalsIgnoreCase(currentWord)) {
                    System.out.println("Correct! Moving to the next level.");
                    incrementScore();
                    break;
                } else {
                    attemptsLeft--;
                    System.out.println("Incorrect! Attempts left: " + attemptsLeft);
                }
            }

            if (attemptsLeft == 0) {
                System.out.println("Game Over! The correct word was: " + currentWord);
                break;
            }
            nextLevel();
        }
        System.out.println("Game Finished! Your final score: " + getScore());
    }

    // Method to set up the level
    private void setupLevel() {
        Random random = new Random();
        int randomIndex = random.nextInt(wordList.length);
        currentWord = wordList[randomIndex];
        scrambledWord = scrambleWord(currentWord);
    }
}

// Game Manager to handle game setup and flow
class GameManager {
    public static void main(String[] args) {
        String[] wordList = {"television", "playstation", "console", "controller", "games", "internet", "disc"};
        WordScrambleGame game = new WordScrambleGame(wordList, 1, 3);
        game.startGame();
    }
}
