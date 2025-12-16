package app.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils 
{
    public static int getValidInt(Scanner scanner, String message) 
    {
        while (true) 
        {
            System.out.print(message);
            try 
            {
                int n = scanner.nextInt();
                scanner.nextLine(); // clear newline
                return n;
            } catch (InputMismatchException e) 
            {
                System.out.println("⚠️ Please enter a number.");
                scanner.nextLine(); // discard bad token
            }
        }
    }
}
