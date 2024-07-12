import java.util.Scanner;

public class Grades
 {
    public static void main(String[] args)
     {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of subjects: ");
        int num = sc.nextInt();
        
        int[] marks = new int[num];
        int totalMarks = 0;
        
        for (int i = 0; i < num; i++)
        {
            System.out.print("Enter marks for subject " + (i + 1)+":" );
            marks[i] = sc.nextInt();
            totalMarks += marks[i];
        }
        
        double averagePercentage = totalMarks / num;
        char grade = calculateGrade(averagePercentage);
        
        System.out.println("\nResult of Student");

        System.out.println("Total Marks: " + totalMarks+"/"+num*100);

        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);

        System.out.println("Grade: " + grade);
        
        sc.close();
    }
    
     static char calculateGrade(double averagePercentage) 
     {
        if (averagePercentage >= 90) 
        {
            return 'A';
        } 
        else if (averagePercentage >= 80) 
        {
            return 'B';
        }
         else if (averagePercentage >= 70) 
        {
            return 'C';
        }
         else if (averagePercentage >= 60)
        {
            return 'D';
        } 
        else
        {
            return 'F';
        }
    }
}
