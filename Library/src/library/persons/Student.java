package library.persons;

public class Student extends Reader {
    private String studentId;

    public Student(String firstName, String lastName, String dateOfBirth, String readerTicket, String studentId) {
        super(firstName, lastName, dateOfBirth, readerTicket);
        this.studentId = studentId;
    }


    @Override
    public String toString() {
        return "Student{" + "firstName='" + firstName + ", lastName='" + lastName + ", readerTicket='" + readerTicket + ", studentId='" + studentId + '}';
    }
}

