package org.alex_hashtag;

import java.io.IOException;
import java.util.Scanner;


public class SimpleWordle
{
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException
    {
        Words.init();

        String targetWord = Words.selectTargetWord();
        byte attempts = 5;
        boolean isCorrect = false;


        // Initialize available letters
        String availableLetters = "abcdefghijklmnopqrstuvwxyz";

        while (attempts > 0 && !isCorrect)
        {
            printKeyboard(availableLetters);
            System.out.println("Enter your guess:");
            String guess = scanner.nextLine().toLowerCase();

            // Check if the word is valid
            if (!isValidWord(guess))
            {
                System.out.println("Invalid word, try again.");
                continue;
            }

            // Process the guess
            String result = processGuess(guess, targetWord);
            System.out.println(result);

            // Check if the guess was correct
            if (guess.equals(targetWord))
            {
                System.out.println("Congratulations! You've guessed the word correctly!");
                isCorrect = true;
            }
            else
            {
                attempts--;
                System.out.println("Attempts left: " + attempts);
                availableLetters = updateAvailableLetters(availableLetters, guess);
            }
        }

        if (!isCorrect)
        {
            System.out.println("Game Over! The correct word was: " + targetWord);
        }

        scanner.close();
    }

    private static void printKeyboard(String letters)
    {
        System.out.println("Available letters:");
        for (char c : letters.toCharArray())
        {
            System.out.print(c + " ");
        }
        System.out.println("\n");
    }

    private static boolean isValidWord(String word)
    {
        return Words.properties.containsValue(word);
    }

    private static String processGuess(String guess, String targetWord)
    {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < guess.length(); i++)
        {
            if (guess.charAt(i) == targetWord.charAt(i))
            {
                display.append("\033[32m").append(guess.charAt(i)).append("\033[0m");  // Green
            }
            else if (targetWord.indexOf(guess.charAt(i)) >= 0)
            {
                display.append("\033[33m").append(guess.charAt(i)).append("\033[0m");  // Yellow
            }
            else
            {
                display.append("\033[37m").append(guess.charAt(i)).append("\033[0m");  // Gray
            }
        }
        return display.toString();
    }

    private static String updateAvailableLetters(String availableLetters, String guess)
    {
        StringBuilder updatedLetters = new StringBuilder();
        for (char c : availableLetters.toCharArray())
        {
            if (guess.indexOf(c) == -1)
            {
                updatedLetters.append(c);
            }
        }
        return updatedLetters.toString();
    }
}
