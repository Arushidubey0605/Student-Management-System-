package student.management.model;

public class Student {
    private int id;
    private String firstName;
    private String surname;
    private int age;
    private String dateOfBirth;
    private char gender;
    private long contactNo;
    private int yearOfJoin;
    private String department;
    private String className;
    private String fatherName;
    private String motherName;

    public Student() {}

    public Student(String firstName, String surname, int age, String dateOfBirth,
                   char gender, long contactNo, int yearOfJoin,
                   String department, String className, String fatherName, String motherName) {
        this(0, firstName, surname, age, dateOfBirth, gender, contactNo, yearOfJoin, department, className, fatherName, motherName);
    }

    public Student(int id, String firstName, String surname, int age, String dateOfBirth,
                   char gender, long contactNo, int yearOfJoin,
                   String department, String className, String fatherName, String motherName) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNo = contactNo;
        this.yearOfJoin = yearOfJoin;
        this.department = department;
        this.className = className;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }
    public long getContactNo() { return contactNo; }
    public void setContactNo(long contactNo) { this.contactNo = contactNo; }
    public int getYearOfJoin() { return yearOfJoin; }
    public void setYearOfJoin(int yearOfJoin) { this.yearOfJoin = yearOfJoin; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }

    @Override
    public String toString() {
        return String.format("Id:%d | %s %s | Age:%d | Dept:%s | Class:%s",
                id, firstName, surname, age, department, className);
    }
}