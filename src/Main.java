import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String choice;
        Scanner sc = new Scanner(System.in);

        UserHelper obj = new UserHelper();
        //obj.CourseRegistrationprev();
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\n============================");
            System.out.println("--------- E Campus ---------");
            System.out.println("============================");
            System.out.println("1. Registration ");
            System.out.println("2. Sign In ");
            System.out.println("0. Exit");
            System.out.println("Enter Your Choice: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    obj.RegisterUser();
                    break;
                case "2":
                    obj.SignIn();
                    break;
                case "0":
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid Selection..");
                    sc.nextLine();
                    break;
            }
        }
    }
}