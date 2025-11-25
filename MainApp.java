package student.management.app;

import student.management.dao.LoginDAO;
import student.management.dao.StudentDAO;
import student.management.exceptions.InvalidInputException;
import student.management.model.Student;

import java.util.*;
import java.util.concurrent.*;

public class MainApp {

    private static final Scanner sc = new Scanner(System.in);
    private static final LoginDAO loginDAO = new LoginDAO();
    private static final StudentDAO studentDAO = new StudentDAO();
    private static final List<Student> studentCache = new ArrayList<>();

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        try {
            scheduleAutoRefresh();
            System.out.println("----- Student Management System -----");
            System.out.print("Username: ");
            String user = sc.nextLine().trim();
            System.out.print("Password: ");
            String pass = sc.nextLine().trim();

            if (!loginDAO.validateUser(user, pass)) {
                System.out.println("Invalid credentials. Exiting.");
                scheduler.shutdownNow();
                return;
            }
            System.out.println("Login successful. Welcome " + user + "!");
            loadCache();
            menuLoop();
        } catch (Exception e) {
            System.err.println("Fatal: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scheduler.shutdown();
        }
    }

    private static void scheduleAutoRefresh() {
        Runnable autoTask = () -> {
            try {
                synchronized (studentCache) {
                    studentCache.clear();
                    studentCache.addAll(studentDAO.getAll());
                }
                System.out.println("[Background] Cache refreshed, size=" + studentCache.size());
            } catch (Exception e) {
                System.err.println("[Background] Auto-refresh error: " + e.getMessage());
            }
        };
        scheduler.scheduleAtFixedRate(autoTask, 30, 60, TimeUnit.SECONDS);
    }

    private static void loadCache() throws Exception {
        List<Student> list = studentDAO.getAll();
        synchronized (studentCache) {
            studentCache.clear();
            studentCache.addAll(list);
        }
    }

    private static void menuLoop() throws Exception {
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students (cache)");
            System.out.println("3. View Students (DB sorted by Id)");
            System.out.println("4. Delete Student by Id");
            System.out.println("5. Update Student by Id");
            System.out.println("6. Sort cache by FirstName");
            System.out.println("7. Exit");
            System.out.print("Choose: ");
            int ch = Integer.parseInt(sc.nextLine().trim());
            switch (ch) {
                case 1: addStudentFlow(); break;
                case 2: viewCache(); break;
                case 3: viewFromDB(); break;
                case 4: deleteFlow(); break;
                case 5: updateFlow(); break;
                case 6: sortCacheByFirstName(); break;
                case 7: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void addStudentFlow() {
        try {
            Student s = readStudentFromConsole(false);
            studentDAO.insert(s);
            System.out.println("Inserted with Id: " + s.getId());
            loadCache();
        } catch (Exception e) {
            System.err.println("Insert failed: " + e.getMessage());
        }
    }

    private static Student readStudentFromConsole(boolean expectId) throws InvalidInputException {
        try {
            int id = 0;
            if (expectId) {
                System.out.print("Enter Id: ");
                id = Integer.parseInt(sc.nextLine().trim());
            }
            System.out.print("First Name: "); String fn = sc.nextLine().trim();
            System.out.print("Surname: "); String sn = sc.nextLine().trim();
            System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine().trim());
            System.out.print("DOB (YYYY-MM-DD): "); String dob = sc.nextLine().trim();
            System.out.print("Gender (M/F/O): "); char g = sc.nextLine().trim().charAt(0);
            System.out.print("Contact No: "); long contact = Long.parseLong(sc.nextLine().trim());
            System.out.print("Year Of Join: "); int yoj = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Department: "); String dept = sc.nextLine().trim();
            System.out.print("Class: "); String cls = sc.nextLine().trim();
            System.out.print("Father Name: "); String fa = sc.nextLine().trim();
            System.out.print("Mother Name: "); String mo = sc.nextLine().trim();

            return expectId ?
                    new Student(id, fn, sn, age, dob, g, contact, yoj, dept, cls, fa, mo) :
                    new Student(fn, sn, age, dob, g, contact, yoj, dept, cls, fa, mo);
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input. " + e.getMessage());
        }
    }

    private static void viewCache() {
        synchronized (studentCache) {
            if (studentCache.isEmpty()) {
                System.out.println("Cache empty.");
                return;
            }
            for (Student s : studentCache) System.out.println(s);
        }
    }

    private static void viewFromDB() {
        try {
            List<Student> list = studentDAO.getAll();
            list.sort(Comparator.comparingInt(Student::getId));
            for (Student s : list) System.out.println(s);
        } catch (Exception e) {
            System.err.println("Cannot load from DB: " + e.getMessage());
        }
    }

    private static void deleteFlow() {
        try {
            System.out.print("Enter Id to delete: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            boolean ok = studentDAO.deleteById(id);
            System.out.println(ok ? "Deleted." : "No row with Id found.");
            loadCache();
        } catch (Exception e) {
            System.err.println("Delete failed: " + e.getMessage());
        }
    }

    private static void updateFlow() {
        try {
            System.out.print("Enter Id to update: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            List<Student> list = studentDAO.getAll();
            Student target = null;
            for (Student s : list) if (s.getId() == id) { target = s; break; }
            if (target == null) { System.out.println("No such Id."); return; }
            System.out.println("Current: " + target);
            System.out.println("Enter new values (leave blank to keep current):");

            System.out.print("First Name ["+target.getFirstName()+"]: "); String fn = sc.nextLine().trim(); if (!fn.isEmpty()) target.setFirstName(fn);
            System.out.print("Surname ["+target.getSurname()+"]: "); String sn = sc.nextLine().trim(); if (!sn.isEmpty()) target.setSurname(sn);
            System.out.print("Age ["+target.getAge()+"]: "); String ageStr = sc.nextLine().trim(); if (!ageStr.isEmpty()) target.setAge(Integer.parseInt(ageStr));
            boolean ok = studentDAO.update(target);
            System.out.println(ok ? "Updated." : "Update failed.");
            loadCache();
        } catch (Exception e) {
            System.err.println("Update failed: " + e.getMessage());
        }
    }

    private static void sortCacheByFirstName() {
        synchronized (studentCache) {
            studentCache.sort(Comparator.comparing(Student::getFirstName, String.CASE_INSENSITIVE_ORDER));
            System.out.println("Sorted cache by first name:");
            viewCache();
        }
    }
}