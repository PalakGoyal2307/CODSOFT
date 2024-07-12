import java.util.Random;
import java.util.Scanner;

public class NumberGuessing
{  
    public static void numberGame() 
    {  
        Scanner sc = new Scanner(System.in);  
        Random r = new Random();
        int secret_Number = r.nextInt(100) + 1;  
        int max_Attempts = 10;  
        int attempts, guess; 
        boolean play_Again;
        System.out.println("Let's start the Number Guessing Game!");  
        System.out.println("Guess the number between 1 and 100");  

        do
        {
            for (attempts = 0; attempts < max_Attempts; attempts++)
            {  
            System.out.println("Attempt " + (attempts + 1)); 
            System.out.println("Enter your guess:");
            guess = sc.nextInt();  

            if (secret_Number == guess) 
            {  
                System.out.println("Bravo! That's the right number.");  
                break;  
            } 

            else if (secret_Number > guess && attempts != max_Attempts - 1) 
            {  
                System.out.println("The secret number is greater than your guess.");  
            } 

            else if (secret_Number < guess && attempts != max_Attempts - 1) 
            {  
                System.out.println("The secret number is less than your guess.");  
            }  
        }  

        if (attempts == max_Attempts) 
        {  
            System.out.println("You have exhausted all " + max_Attempts + " attempts.");  
            System.out.println("The secret number was " + secret_Number);  
        }  

    System.out.print("Do you want to play another round? (yes/no): ");
            play_Again = sc.next().equalsIgnoreCase("yes");
    }while (play_Again);

    System.out.println("Thank you for playing the Guessing Game!");
        sc.close();
    }

     public static void main(String args[]) 
    {  
        numberGame();  
    }
     
}  