import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Project Title : Student Result Management System
 * Language      : Java
 * Developed By  : Monisha
 * Department    : Computer Science and Engineering
 * Description   : This project stores student details, calculates grades,
 *                 displays results, and saves the result to a text file.
 */

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Heading
        System.out.println("===========================================");
        System.out.println("      COLLEGE STUDENT RESULT MANAGEMENT");
        System.out.println("===========================================");
        System.out.println();

        // Register Number
        System.out.print("Enter Register Number : ");
        String registerNumber = input.nextLine();

        // Student Name
        System.out.print("Enter Student Name : ");
        String studentName = input.nextLine();

        System.out.println();

        // Department Menu
        System.out.println("Select Department");
        System.out.println("1. CSE");
        System.out.println("2. IT");
        System.out.println("3. ECE");
        System.out.println("4. EEE");
        System.out.println("5. MECH");
        System.out.println("6. CIVIL");

        System.out.print("Enter your choice : ");
        int departmentChoice = input.nextInt();

        String department;

        switch (departmentChoice) {

            case 1:
                department = "CSE";
                break;

            case 2:
                department = "IT";
                break;

            case 3:
                department = "ECE";
                break;

            case 4:
                department = "EEE";
                break;

            case 5:
                department = "MECH";
                break;

            case 6:
                department = "CIVIL";
                break;

            default:
                System.out.println("Invalid Department Choice!");
                return;
        }

        System.out.println();
        System.out.println("Department : " + department);
        System.out.println();

        // Semester
        System.out.print("Enter Semester (1-8) : ");
        int semester = input.nextInt();

        if (semester < 1 || semester > 8) {
            System.out.println("Invalid Semester!");
            return;
        }

        Student student = new Student(
                registerNumber,
                studentName,
                department,
                semester
        );

        System.out.println();

        String[] subjects = getSubjects(department, semester);

        // Number of Subjects
        int numberOfSubjects = 5;

        // Array to store marks
        Subject[] subjectList = new Subject[numberOfSubjects];

        // Pass / Fail
        String result;

        // Input Marks
        for (int i = 0; i < numberOfSubjects; i++) {

            System.out.print("Enter Marks for " + subjects[i] + " : ");
            int mark = input.nextInt();

            subjectList[i] = new Subject(subjects[i], mark);

            if (subjectList[i].mark < 40) {
                result = "FAIL";
            }
        }

        // Calculate Total
        int total = 0;
        int highestMark = -1;
        String highestSubject = "";
        int lowestMark = 101;
        String lowestSubject = "";
        boolean allSubjectsPassed = true;

        for(int i = 0; i < numberOfSubjects; i++) {
            total += subjectList[i].mark;
            if (subjectList[i].mark > highestMark) {

                highestMark = subjectList[i].mark;
                highestSubject = subjectList[i].subjectName;

            }
            if (subjectList[i].mark < lowestMark) {

                lowestMark = subjectList[i].mark;
                lowestSubject = subjectList[i].subjectName;

            }

            if (subjectList[i].mark < 35) {
                allSubjectsPassed = false;
            }
        }
        // Calculate Percentage
        double percentage = (total / 500.0) * 100;

        // Grade Calculation
        String grade;

        if (percentage >= 90) {
            grade = "A+";
        }
        else if (percentage >= 80) {
            grade = "A";
        }
        else if (percentage >= 70) {
            grade = "B";
        }
        else if (percentage >= 60) {
            grade = "C";
        }
        else if (percentage >= 50) {
            grade = "D";
        }
        else {
            grade = "F";
        }

        if (allSubjectsPassed) {
            result = "PASS";
        }
        else {
            result = "FAIL";
        }

        // Result Card
        System.out.println();
        System.out.println("\n========================================");
        System.out.println("        STUDENT RESULT CARD");
        System.out.println("========================================");

        System.out.println("Register Number : " + student.registerNumber);
        System.out.println("Student Name    : " + student.studentName);
        System.out.println("Department      : " + student.department);
        System.out.println("Semester        : " + student.semester);

        System.out.println("----------------------------------------");
        System.out.println("Subjects and Marks");
        System.out.println("----------------------------------------");
        System.out.println("------------------------------------------");
        System.out.printf("%-35s %-8s %-6s\n", "Subject", "Mark", "Grade");
        System.out.println("------------------------------------------");

        for (int i = 0; i < subjectList.length; i++) {

            String subjectGrade = getSubjectGrade(subjectList[i].mark);

            System.out.printf("%-35s %-8d %-6s\n",
                    subjectList[i].subjectName,
                    subjectList[i].mark,
                    subjectGrade);
        }

        System.out.println("----------------------------------------");

        System.out.println("Total           : " + total);
        System.out.printf("Percentage      : %.2f%%\n", percentage);
        System.out.println("Grade           : " + grade);
        System.out.println("Result          : " + result);

        System.out.println();

        System.out.println("Highest Mark    : " + highestMark);
        System.out.println("Highest Subject : " + highestSubject);

        System.out.println();

        System.out.println("Lowest Mark     : " + lowestMark);
        System.out.println("Lowest Subject  : " + lowestSubject);

        System.out.println("========================================");

        input.close();
    }

    public static String[] getSubjects(String department, int semester) {

        String[] subjects = new String[5];

        if (department.equals("CSE") && semester == 1) {

            subjects[0] = "Mathematics I";
            subjects[1] = "Physics";
            subjects[2] = "Programming in C";
            subjects[3] = "Engineering Graphics";
            subjects[4] = "English";

        }
        else if (department.equals("CSE") && semester == 2) {

            subjects[0] = "Mathematics II";
            subjects[1] = "Data Structures";
            subjects[2] = "Digital Electronics";
            subjects[3] = "Object Oriented Programming";
            subjects[4] = "Environmental Science";

        }
        else if (department.equals("CSE") && semester == 3) {

            subjects[0] = "Discrete Mathematics";
            subjects[1] = "Java Programming";
            subjects[2] = "Computer Organization";
            subjects[3] = "Data Structures";
            subjects[4] = "Software Engineering";

        }
        else if (department.equals("CSE") && semester == 4) {

            subjects[0] = "Java Programming";
            subjects[1] = "Database Management System";
            subjects[2] = "Operating Systems";
            subjects[3] = "Computer Networks";
            subjects[4] = "Software Engineering";

        }
        else if (department.equals("CSE") && semester == 5) {

            subjects[0] = "Theory of Computation";
            subjects[1] = "Compiler Design";
            subjects[2] = "Computer Graphics";
            subjects[3] = "Microprocessors";
            subjects[4] = "Artificial Intelligence";
        }
        else {
            subjects[0] = "Subject 1";
            subjects[1] = "Subject 2";
            subjects[2] = "Subject 3";
            subjects[3] = "Subject 4";
            subjects[4] = "Subject 5";
        }

        return subjects;
    }
    public static String getSubjectGrade(int mark) {

        if (mark >= 90) {
            return "A+";
        } else if (mark >= 80) {
            return "A";
        } else if (mark >= 70) {
            return "B";
        } else if (mark >= 60) {
            return "C";
        } else if (mark >= 50) {
            return "D";
        } else if (mark >= 40) {
            return "E";
        } else {
            return "F";
        }

    }
    public static void saveResultToFile(
            Student student,
            Subject[] subjectList,
            int total,
            double percentage,
            String grade,
            String result,
            int highestMark,
            String highestSubject,
            int lowestMark,
            String lowestSubject
    ) {

        try {

            FileWriter writer = new FileWriter("StudentResult.txt");

            writer.write("========== STUDENT RESULT ==========\n\n");

            writer.write("Register Number : " + student.registerNumber + "\n");
            writer.write("Student Name    : " + student.studentName + "\n");
            writer.write("Department      : " + student.department + "\n");
            writer.write("Semester        : " + student.semester + "\n\n");
            writer.write("----------------------------------------\n");
            writer.write("Subjects and Marks\n");
            writer.write("----------------------------------------\n");

            for (int i = 0; i < subjectList.length; i++) {

                String subjectGrade = getSubjectGrade(subjectList[i].mark);

                writer.write(
                        subjectList[i].subjectName +
                                " : " +
                                subjectList[i].mark +
                                " (" +
                                subjectGrade +
                                ")\n"
                );

            }
            writer.write("\n----------------------------------------\n");

            writer.write("Total           : " + total + "\n");
            writer.write("Percentage      : " + String.format("%.2f", percentage) + "%\n");
            writer.write("Grade           : " + grade + "\n");
            writer.write("Result          : " + result + "\n\n");

            writer.write("Highest Mark    : " + highestMark + "\n");
            writer.write("Highest Subject : " + highestSubject + "\n");

            writer.write("Lowest Mark     : " + lowestMark + "\n");
            writer.write("Lowest Subject  : " + lowestSubject + "\n");

            writer.write("\n========================================\n");
            writer.write("Generated by Student Result Management System");

            writer.close();

            System.out.println("Result saved successfully!");

        } catch (IOException e) {

            System.out.println("Error while saving the file.");

        }

    }
    public static void readResultFile() {

        try {

            File file = new File("StudentResult.txt");
            Scanner fileReader = new Scanner(file);

            System.out.println("\n===== SAVED RESULT =====\n");

            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Result file not found!");
        }

    }
}