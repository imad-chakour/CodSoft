import java.util.Scanner;

public class StudentGradeCalculator {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int num = scanner.nextInt();

        int somme = 0;

        for (int i = 1; i <= num; i++) {
            System.out.print("Enter marks obtained in subject " + i + " (out of 100): ");
            int note = scanner.nextInt();

            while (note < 0 || note > 100) {
                System.out.print("Invalid input. Please enter marks between 0 and 100: ");
                note = scanner.nextInt();
            }
            somme = somme +  note;
        }

        double moy = (double) somme / num;
        char grade;

        if (moy >= 90) {
            grade = 'A';
        } else if (moy >= 80) {
            grade = 'B';
        } else if (moy >= 70) {
            grade = 'C';
        } else if (moy >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("\n--- Results ---");
        System.out.println("Total Marks: " + somme);
        System.out.println("Average Percentage: " + String.format("%.2f", moy) + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }

}
