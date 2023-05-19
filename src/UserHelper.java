import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class UserHelper {
    String fullName, username, password, confirmPassword, courseId, courseName;
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    int credits, totalMarks, passingCriteria, choice;
    Course course = null;
    Course course1 =null;
    static ArrayList<Student> studentsList = new ArrayList<>();
    ArrayList<Faculty> facultiesList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    UserHelper() {

    try {
        Scanner scanner = new Scanner(new File("students.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(",");
            studentsList.add(new Student(data[0], data[1], data[2]));

        }
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    try {
        Scanner scanner = new Scanner(new File("faculty.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(",");
            course1 = new Course(data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
            facultiesList.add(new Faculty(data[0], data[1], data[7],course1));
            //new Faculty(fullName, username, password, course);
        }
        CourseRegistrationprev();
        AddStudentMarksprev();
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    }


    void RegisterUser() {
        System.out.println("\n============");
        System.out.println("Registration");
        System.out.println("============");
        System.out.println("1. Faculty");
        System.out.println("2. Student");
        System.out.println("0. Home");


        System.out.println("Enter Your Choice : ");
        choice = sc.nextInt();

        if (choice == 0)
            return;

        if (choice < 0 || choice > 2) {
            RegisterUser();
            return;
        }

        // While Loop For Full Name
        sc.nextLine();
        while (true) {
            System.out.println("\nEnter Your Full Name - ");
            fullName = sc.nextLine();

            if (fullName.length() == 0) {
                System.out.println("Name Cannot Be An Empty Field...");
            } else if (!Pattern.matches("[a-zA-Z ]{" + fullName.length() + "}", fullName)) {
                System.out.println("\nName Must Only Contain Characters...");
            } else {
                break;
            }
        }

        // While Loop For Username
        while (true) {
            if (choice == 1) {
                System.out.println("\nEnter Your Username - ");
            } else {
                System.out.println("\nEnter Your Student Id - ");
            }
            username = sc.nextLine();
            if (choice == 2) {
                if (!Pattern.matches("[0-9]{9}", username)) {
                    System.out.println("Student Id Is Not Valid...");
                    continue;
                }

                boolean isStudIdAvailable = false;
                for (Student stud : studentsList) {
                    if (username.equals(stud.getUsername())) {
                        isStudIdAvailable = true;
                        break;
                    }
                }
                if (isStudIdAvailable) {
                    System.out.println("Student Id Already Exists...");
                    continue;
                }
            }
            if (username.length() == 0) {
                System.out.println("Username Cannot Be An Empty Field.");
            } else {
                break;
            }
        }

        if (choice == 1) {
            System.out.println("\nEnter Course Id - ");
            courseId = sc.nextLine();

            // Validation Length & Only Char
            System.out.println("\nEnter Course Name - ");
            courseName = sc.nextLine();

            System.out.println("\nEnter Total Credits - ");
            credits = sc.nextInt();

            System.out.println("\nEnter Total Marks - ");
            totalMarks = sc.nextInt();

            // validation passing criteria must be btwn 1-100
            System.out.println("\nEnter Passing Criteria In Percentage - ");
            passingCriteria = sc.nextInt();

            course = new Course(courseId, courseName, credits, totalMarks, passingCriteria);
        }

        // While Loop For Password
        while (true) {
            System.out.print("\nEnter Your Password - ");
            password = new String(System.console().readPassword());
            for (int i = 0; i < password.length(); i++) {
                System.out.print("*");
            }
            System.out.println();

            if (Pattern.matches(passwordPattern, password)) {
                break;
            } else {
                System.out.println("\nPassword Must Contain At Least 8 Characters And At Most 20 Characters.");
                System.out.println("Password Must Contain At Least One Digit.");
                System.out.println("Password Must Contain At Least One Uppercase Alphabet.");
                System.out.println("Password Must Contain At Least One Lowercase Alphabet.");
                System.out.println("Password Must Contain At Least One Special Character.");
                System.out.println("Password Cannot Contain Space");
            }
        }

        System.out.print("\nConfirm Password - ");
        confirmPassword = new String(System.console().readPassword());
        for (int i = 0; i < password.length(); i++) {
            System.out.print("*");
        }

        if (password.equals(confirmPassword)) {
            if (choice == 1) {
                Faculty faculty = new Faculty(fullName, username, password, course);
                facultiesList.add(faculty);
                try {
                    FileWriter writer = new FileWriter("faculty.txt", true);
                        writer.write("\n"+fullName + "," + username + "," + courseId + "," + courseName + "," + Integer.toString(credits) + "," + Integer.toString(totalMarks) + "," + Integer.toString(passingCriteria) + "," + password);
                        
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Student student = new Student(fullName, username, password);
                studentsList.add(student);
                try {
                    FileWriter writer = new FileWriter("students.txt", true);
                        writer.write("\n"+student.fullName + "," + student.username + "," + student.password);
                        
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\n\nUser Registered Successfully...");
        } else {
            System.out.println("\n\nBoth Password Must Be Same\nTry Again...");
            RegisterUser();
        }
    }

    void SignIn() {
        System.out.println("\n=======");
        System.out.println("Sign In");
        System.out.println("=======");
        System.out.println("1. Faculty");
        System.out.println("2. Student");
        System.out.println("0. Home");
        System.out.println("Enter Your Choice : ");
        choice = sc.nextInt();

        if (choice == 0)
            return;

        if (choice < 0 || choice > 2) {
            SignIn();
            return;
        }

        // While Loop For Username
        sc.nextLine();
        while (true) {
            if (choice == 1)
                System.out.println("\nEnter Your Username - ");
            else
                System.out.println("\nEnter Your Student Id - ");
            username = sc.nextLine();

            if (username.length() == 0) {
                System.out.println("Username Cannot Be An Empty Field...");
            } else {
                break;
            }
        }

        System.out.print("\nEnter Your Password - ");
        password = new String(System.console().readPassword());
        for (int i = 0; i < password.length(); i++) {
            System.out.print("*");
        }

        System.out.println();
        if (choice == 1) {
            Faculty u = null;
            int index = -1;
            for (Faculty user : facultiesList) {
                index++;
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    u = user;
                    break;
                }
            }
            if (u != null) {
                System.out.println("\nWelcome " + u.getFullName());
                if (u.getStudents().size() == 0)
                    CourseRegistration(u);
                FacultyMenu(u);
            } else {
                System.out.println("\nIncorrect Password");
                while (true) {
                    System.out.println("\n1. Try Again");
                    System.out.println("2. Forgot Password");
                    System.out.println("0. Home Menu");
                    System.out.print("\nEnter Your Choice ( 0 - 2 ) : ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    if (choice == 1) {
                        SignIn();
                        break;
                    } else if (choice == 2) {
                        ForgetPassword(1);
                        break;
                    } else if (choice == 0) {
                        break;
                    } else {
                        System.out.println("\nEnter Valid Option...");
                    }
                }
            }
        } else {
            Student u = null;
            int index = -1;
            for (Student user : studentsList) {
                index++;
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    u = user;
                    break;
                }
            }
            if (u != null) {
                System.out.println("\nWelcome " + u.getFullName());
                ViewStudentDetails(u);
                System.out.println("Press Enter to continue");
                sc.nextLine();
            } else {
                System.out.println("\nIncorrect Password");
                while (true) {
                    System.out.println("\n1. Try Again");
                    System.out.println("2. Forgot Password");
                    System.out.println("0. Home Menu");
                    System.out.print("\nEnter Your Choice ( 0 - 2 ) : ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    if (choice == 1) {
                        SignIn();
                        break;
                    } else if (choice == 2) {
                        ForgetPassword(2);
                        break;
                    } else if (choice == 0) {
                        break;
                    } else {
                        System.out.println("\nEnter Valid Option...");
                    }
                }
            }
        }
    }

    void CourseRegistrationprev() {

        // File file = new File("data.txt");
        // Scanner scanner = new Scanner(file);
        // while (scanner.hasNextLine()) {
        //     String line = scanner.nextLine();
        //     String[] data = line.split(",");
        //     studid = data[0];
        //     obtainedMarks = Integer.parseInt(data[1]);


        try {
            Faculty u = null;
            String facultyname=null;
            File file = new File("addStu.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                facultyname=data[0];
                long studentid = Long.parseLong(data[1]);

                
                for (Faculty user : facultiesList) {
                    if (facultyname.equals(user.getUsername())) {
                        u = user;
                        break;
                    }
                }

                if(u!=null) {
                for (Student stud : studentsList) {
                    if (stud.getUsername().equals(Long.toString(studentid))) {
                        stud.addCourse(u.getCourse());
                        u.addStudent(stud);
                        break;
                    }
                }
            }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    void ForgetPassword(int userType) {
        // If Condition For Faculty's Forget Password
        if (userType == 1) {
            Faculty u = null;
            while (true) {
                u = null;
                System.out.println("\nEnter Your Full Name - ");
                fullName = sc.nextLine();

                for (Faculty user : facultiesList) {
                    if (fullName.equals(user.getFullName())) {
                        u = user;
                        break;
                    }
                }

                if (u != null) {
                    while (true) {
                        System.out.print("\nEnter Your New Password - ");
                        password = new String(System.console().readPassword());
                        for (int i = 0; i < password.length(); i++) {
                            System.out.print("*");
                        }
                        System.out.println();

                        if (Pattern.matches(passwordPattern, password)) {
                            break;
                        } else {
                            System.out.println(
                                    "\nPassword Must Contain At Least 8 Characters And At Most 20 Characters.");
                            System.out.println("Password Must Contain At Least One Digit.");
                            System.out.println("Password Must Contain At Least One Uppercase Alphabet.");
                            System.out.println("Password Must Contain At Least One Lowercase Alphabet.");
                            System.out.println("Password Must Contain At Least One Special Character.");
                            System.out.println("Password Cannot Contain Space");
                        }
                    }

                    System.out.print("\nConfirm New Password - ");
                    confirmPassword = new String(System.console().readPassword());
                    for (int i = 0; i < password.length(); i++) {
                        System.out.print("*");
                    }

                    if (password.equals(confirmPassword)) {
                        u.setPassword(password);
                        System.out.println("\n\nPassword Changed Successfully...");
                        break;
                    } else {
                        System.out.println("\n\nBoth Password Must Be Same\nTry Again...");
                        sc.nextLine();
                    }
                } else {
                    System.out.println("\nUser Not Found...\nTry Again...");
                }
            }
        } else {
            // Else Condition For Student's Forget Password
            Student u = null;
            while (true) {
                u = null;
                System.out.println("\nEnter Your Full Name - ");
                fullName = sc.nextLine();

                for (Student user : studentsList) {
                    if (fullName.equals(user.getFullName())) {
                        u = user;
                        break;
                    }
                }

                if (u != null) {
                    while (true) {
                        System.out.print("\nEnter Your New Password - ");
                        password = new String(System.console().readPassword());
                        for (int i = 0; i < password.length(); i++) {
                            System.out.print("*");
                        }
                        System.out.println();

                        if (Pattern.matches(passwordPattern, password)) {
                            break;
                        } else {
                            System.out.println(
                                    "\nPassword Must Contain At Least 8 Characters And At Most 20 Characters.");
                            System.out.println("Password Must Contain At Least One Digit.");
                            System.out.println("Password Must Contain At Least One Uppercase Alphabet.");
                            System.out.println("Password Must Contain At Least One Lowercase Alphabet.");
                            System.out.println("Password Must Contain At Least One Special Character.");
                            System.out.println("Password Cannot Contain Space");
                        }
                    }

                    System.out.print("\nConfirm New Password - ");
                    confirmPassword = new String(System.console().readPassword());
                    for (int i = 0; i < password.length(); i++) {
                        System.out.print("*");
                    }

                    if (password.equals(confirmPassword)) {
                        u.setPassword(password);
                        System.out.println("\n\nPassword Changed Successfully...");
                        break;
                    } else {
                        System.out.println("\n\nBoth Password Must Be Same\nTry Again...");
                        sc.nextLine();
                    }
                } else {
                    System.out.println("\nUser Not Found...\nTry Again...");
                }
            }
        }
    }

    void CourseRegistration(Faculty fac) {
        System.out.println("\n=====================================");
        System.out.println("List Of Students Registered In System");
        System.out.println("=====================================");

        if (studentsList.size() > 0) {
            System.out.println("Student ID\t \tStudent Name\n");
            int i = 0;
            for (Student stud : studentsList) {
                boolean isRegistered = false;
                for (Course CS : stud.getCourses()) {
                    if (CS == fac.getCourse()) {
                        isRegistered = true;
                        break;
                    }
                }
                if (!isRegistered) {
                    i++;
                    System.out.println(stud.getUsername() + "\t-\t" + stud.getFullName());
                }
            }
            if (i == 0) {
                System.out.println("No Students Are Left To Be Registered In This Course...");
                // System.out.println("Press Enter Key To Countinue...");
                // sc.nextLine();
                // sc.nextLine();
                return;
            }
        } else if (studentsList.size() <= 0) {
            System.out.println("No Students Are Registered...");
            return;
        }

        System.out.println("\nEnter Student ID You Want To Register : ");
        System.out.println("Enter 0 To Stop...");
        long studentid;

        // While Loop For Adding Selected Student To StudentList of Faculty Object
        while (true) {
            System.out.println("Student ID : ");
            studentid = sc.nextLong();
            if (studentid == 0)
                break;

            boolean flag = false;
            for (Student stud : studentsList) {
                if (stud.getUsername().equals(Long.toString(studentid))) {
                    try {
                        FileWriter writer = new FileWriter("addStu.txt", true);
                            writer.write("\n"+fac.getUsername() + "," + stud.getUsername());
                            
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    flag = true;
                    stud.addCourse(fac.getCourse());
                    fac.addStudent(stud);
                    break;
                }
            }
            if (!flag)
                System.out.println("Student Not Found...");
        }


    }

    void ViewStudentMarks(Faculty fac) {
        if (fac.getStudents().size() <= 0) {
            System.out.println("No Students Found...");
        } else {
            System.out.println(
                    "Student ID\tStudent Name\tObt. Marks\tTotal Marks\tGrade\tAttained Credits\tTotal Credits");
            for (Student stud : fac.getStudents()) {
                for (CourseScore CS : stud.getScoreCard().getCourseScores()) {
                    if (CS.getCourse().equals(fac.getCourse())) {
                        System.out.print(stud.getUsername() + "\t" + stud.getFullName());
                        System.out.println("\t\t" + CS.getObtainedMarks() + "\t\t" + CS.getCourse().getTotalMarks()
                                + "\t\t" + CS.getGrade() + "\t" + CS.getAttainedCredits() + "\t\t\t"
                                + CS.getCourse().getCredits());
                    }
                }
            }
        }
        // System.out.println("Press Enter To Continue...");
        // sc.nextLine();
        // sc.nextLine();
    }

    void DisplayStudents(Faculty fac) {
        System.out.println("\n===============");
        System.out.println("Student Details");
        System.out.println("===============");
        System.out.println("\n---------------------------");
        System.out.println("Student Id\tStudent Name");
        System.out.println("---------------------------");
        for (Student stud : fac.getStudents()) {
            System.out.println(stud.getUsername() + "\t" + stud.getFullName());
            System.out.println("---------------------------");
        }

        // System.out.println("Press Enter To Continue...");
        // sc.nextLine();
        // sc.nextLine();
    }

    char CalculateGrade(float Percentage, Course course) {
        float passingCriteria = course.getPassingCriteria();
        float remainingCriteria = 100 - passingCriteria;
        float gradeA = 100 - (remainingCriteria / 3);
        float gradeB = 100 - (remainingCriteria / 3) * 2;
        float gradeC = 100 - remainingCriteria;

        if (Percentage <= 100 && Percentage > gradeA)
            return 'A';
        else if (Percentage <= gradeA && Percentage > gradeB)
            return 'B';
        else if (Percentage <= gradeB && Percentage >= gradeC)
            return 'C';
        else
            return 'F';
    }

    int CalculateCredit(char grade, Course course) {
        if (grade == 'F')
            return 0;
        else
            return course.getCredits();
    }

    int getRank(Faculty fac, Student stud)
    {
        ArrayList<Student> student = new ArrayList<>(fac.getStudents());
        Collections.sort(student, (s1, s2) -> Float.compare(s2.getScoreCard().getOverallpercentage(), s1.getScoreCard().getOverallpercentage()));
    
        int i=0;
        for(Student s : student)
        {
            i++;
            if(s==stud)
                break;
        }
        return i;
    }
    
    void calculatePercentile(Faculty fac)
    {
        for (Student s : fac.getStudents())
        {
            int rank = getRank(fac, s);
            float perc = ((float)(fac.getStudents().size() - rank + 1)/(float)fac.getStudents().size())*100;
            s.getScoreCard().setPercentile(perc);
        }
    }
    void calculateOverallGrade(Student stud)
    {
        ScoreCard SC = stud.getScoreCard();
        int counter=0;
        float overallper=0;

        for (CourseScore CS : SC.getCourseScores())
        {
            counter++;
            overallper += CS.getPercentage();
        }
        overallper = overallper/counter;
        stud.getScoreCard().setOverallpercentage(overallper);

        if(overallper<=100 && overallper>=80)
        {
            SC.setOverallGrade('A');
        }
        else if (overallper<80 && overallper>=60)
        {
            SC.setOverallGrade('B');
        }
        else if(overallper<60 && overallper>=40)
        {
            SC.setOverallGrade('C');
        }
        else
        {
            SC.setOverallGrade('F');
        }
    }

    void ViewStudetsWithoutMarks(Faculty fac) {
        System.out.println("\n===============");
        System.out.println("Student Details");
        System.out.println("===============");

        if (fac.getStudents().size() <= 0) {
            System.out.println("No Students Are Registered Currently...");
            return;
        }

        System.out.println("\n---------------------------");
        System.out.println("Student Id\tStudent Name");
        System.out.println("---------------------------");

        int i = 0;

        for (Student stud : fac.getStudents()) {
            boolean isMarksExist = false;
            for (CourseScore CS : stud.getScoreCard().getCourseScores()) {
                if (CS.getCourse() == fac.getCourse()) {
                    isMarksExist = true;
                    break;
                }
            }
            if (!isMarksExist) {
                i++;
                System.out.println(stud.getUsername() + "\t" + stud.getFullName());
                System.out.println("---------------------------");
            }
        }
        if (i == 0) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("No Student's Marks Are Left To Be Added...");
        }

    }

    void AddStudentMarksprev()
    {
        Faculty u=null;
        String FacName=null;
        String studid=null;
        int obtainedMarks=0;
        int flag=0;
        try {
            File file = new File("updateMarks.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                FacName=data[0];
                studid = data[1];
                obtainedMarks = Integer.parseInt(data[2]);

                for (Faculty user : facultiesList) {
                    if (FacName.equals(user.getUsername())) {
                        u = user;
                        break;
                    }
                }

        for (Student user : studentsList) {
            if (studid.equals(user.getUsername())) {
                flag=1;
                break;
            }
        }
            System.out.flush();
            ViewStudetsWithoutMarks(u);

            if (studid.equals("0"))
                return;

            while (true) {
                if (obtainedMarks < 0) {
                    continue;
                } else if (obtainedMarks > u.getCourse().getTotalMarks()) {
                    continue;
                } else
                    break;
            }
            float Percentage = (obtainedMarks * 100) / (float) (u.getCourse().getTotalMarks());

            char grade = CalculateGrade(Percentage, u.getCourse());
            CourseScore CS = new CourseScore(u.getCourse(), obtainedMarks, grade,
                    CalculateCredit(grade, u.getCourse()), Percentage);
            if(u!=null) {
            for (Student stud : u.getStudents())
            {
                if (studid.equals(stud.getUsername()))
                {
                    stud.getScoreCard().addCourseScore(CS);
                    calculateOverallGrade(stud);
                    calculatePercentile(u);
                    break;
                }
            }
        }
    }
    scanner.close();
    } catch (FileNotFoundException e) {
       System.out.println("An error occurred.");
     e.printStackTrace();
    }
}


    void AddStudentMarks(Faculty fac)
    {
        String studid;
        int obtainedMarks;
        int flag=0;
        System.out.println("Enter Student Id - ");
        studid = sc.nextLine();

        for (Student user : studentsList) {
            if (studid.equals(user.getUsername())) {
                flag=1;
                break;
            }
        }
        
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ViewStudetsWithoutMarks(fac);
            System.out.println("Enter 0 To Stop...\n");

            sc.nextLine();
            System.out.println("Enter Student Id - ");
            studid = sc.nextLine();

            if (studid.equals("0"))
                return;

            while (true) {
                System.out.println("Enter Obtained Marks : ");
                obtainedMarks = sc.nextInt();
                if (obtainedMarks < 0) {
                    System.out.println("Obtained Marks Can't Be Less Than 0...");
                    continue;
                } else if (obtainedMarks > fac.getCourse().getTotalMarks()) {
                    System.out.println("Obtained Marks Can't Be Greater Than Total Marks...");
                    continue;
                } else
                    break;
            }
            float Percentage = (obtainedMarks * 100) / (float) (fac.getCourse().getTotalMarks());

            char grade = CalculateGrade(Percentage, fac.getCourse());
            CourseScore CS = new CourseScore(fac.getCourse(), obtainedMarks, grade,
                    CalculateCredit(grade, fac.getCourse()), Percentage);

            for (Student stud : fac.getStudents())
            {
                if (studid.equals(stud.getUsername()))
                {
                    try {
                        FileWriter writer = new FileWriter("updateMarks.txt", true);
                            writer.write("\n"+fac.getUsername() + "," + stud.getUsername() + "," + obtainedMarks);
                            
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stud.getScoreCard().addCourseScore(CS);
                    calculateOverallGrade(stud);
                    calculatePercentile(fac);
                    break;
                }
            }
        }
    }



    void UpdateStudentMarks(Faculty fac) {
        ViewStudentMarks(fac);

        String studid;
        int obtainedMarks;

        sc.nextLine();
        System.out.println("Enter Student Id - ");
        studid = sc.nextLine();

        while (true) {
            System.out.println("Enter Obtained Marks : ");
            obtainedMarks = sc.nextInt();
            if (obtainedMarks < 0) {
                System.out.println("Obtained Marks Can't Be Less Than 0...");
                continue;
            } else if (obtainedMarks > fac.getCourse().getTotalMarks()) {
                System.out.println("Obtained Marks Can't Be Greater Than Total Marks...");
                continue;
            } else
                break;
        }
        float Percentage = (obtainedMarks * 100) / (float) (fac.getCourse().getTotalMarks());

        char grade = CalculateGrade(Percentage, fac.getCourse());

        for (Student stud : fac.getStudents()) {
            if (studid.equals(stud.getUsername())) {
                for (CourseScore C : stud.getScoreCard().getCourseScores()) {
                    if (C.getCourse() == fac.getCourse())
                    {
                        C.setObtainedMarks(obtainedMarks);
                        C.setGrade(grade);
                        C.setPercentage(Percentage);
                        calculateOverallGrade(stud);
                        calculatePercentile(fac);
                        break;
                    }
                }
                break;
            }
        }
    }


    void DeleteStudent(Faculty fac) {
        DisplayStudents(fac);
        String studid;

        sc.nextLine();
        System.out.println("Enter Student Id - ");
        studid = sc.nextLine();

        for (Student stud : fac.getStudents()) {
            if (studid.equals(stud.getUsername())) {
                stud.removeCourse(fac.getCourse());
                for (CourseScore C : stud.getScoreCard().getCourseScores()) {
                    if (C.getCourse() == fac.getCourse()) {
                        stud.getScoreCard().removeCourseScore(C);
                        break;
                    }
                }
                fac.removeStudent(stud);
                break;
            }
        }
    }

    void FacultyMenu(Faculty fac) {
        int choice;

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\n==============");
            System.out.println("Faculty - Menu");
            System.out.println("==============");

            System.out.println("\n1. Add Students Marks");
            System.out.println("2. View Students Marks");
            System.out.println("3. Update Students Marks");
            System.out.println("4. Add Students");
            System.out.println("5. Delete Students");
            System.out.println("6. Display Students");
           // System.out.println("7. actiavte");
            System.out.println("0. Exit");

            System.out.println("Enter Your Choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    AddStudentMarks(fac);
                    System.out.println("Press Enter Key To Countinue...");
                    sc.nextLine();
                    break;
                case 2:

                    ViewStudentMarks(fac);
                    System.out.println("Press Enter Key To Countinue...");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 3:
                    UpdateStudentMarks(fac);
                    System.out.println("Press Enter Key To Countinue...");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 4:
                    CourseRegistration(fac);
                    System.out.println("Press Enter Key To Countinue...");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 5:
                    DeleteStudent(fac);
                    System.out.println("Press Enter Key To Countinue...");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 6:
                    DisplayStudents(fac);
                    System.out.println("Press Enter Key To Countinue...");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                // case 7: 
                //     CourseRegistrationprev();
                //     AddStudentMarksprev();
                //     break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid Selection :");
                    break;
            }
        }

    }

    void ViewStudentDetails(Student stud)
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("\n================");
        System.out.println("   Score Card   ");
        System.out.println("================");

        System.out.println("\n--------------------------");
        System.out.println("Student Id : " + stud.getUsername());
        System.out.println("Student Name : " + stud.getFullName());
        System.out.println("--------------------------");

        System.out.println("\n===============");
        System.out.println("    Courses    ");
        System.out.println("===============");

        calculateOverallGrade(stud);

        if (stud.getCourses().size() > 0)
        {
            System.out.println("Course Id\tCourse Name\tTotal Credits");
            for (Course course : stud.getCourses())
            {
                System.out.println(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getCredits());
            }
        }
        else
        {
            System.out.println("Student Is Not Registered In Any Courses...");
        }

        System.out.println("\n===============");
        System.out.println("     Marks     ");
        System.out.println("===============");

        System.out.println("Course Id\tCourse Name\tObt. Marks\tTotal Marks\tGrade\tCredits\tPercentage");
        int overallcred=0;

        for (CourseScore CS : stud.getScoreCard().getCourseScores())
        {
            System.out.println(CS.getCourse().getCourseId()+"\t\t"+CS.getCourse().getCourseName()+"\t\t"+CS.getObtainedMarks()+"\t\t"+
            CS.getCourse().getTotalMarks()+"\t\t"+CS.getGrade()+"\t"+CS.getAttainedCredits()+"\t"+CS.getPercentage());

            overallcred += CS.getAttainedCredits();
        }
        System.out.println("");
        System.out.println("Percentile : " + stud.getScoreCard().getPercentile());
        System.out.println("Overall Percentage : " + stud.getScoreCard().getOverallpercentage());
        System.out.println("Overall Grade : " + stud.getScoreCard().getOverallGrade());
        System.out.println("Overall Credits : " + overallcred);
        System.out.println("");
    }
}

