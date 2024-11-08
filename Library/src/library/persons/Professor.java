package library.persons;

import library.persons.Reader;

public class Professor extends Reader {
    private String department;

    public Professor(String firstName, String lastName, String dateOfBirth, String readerTicket, String department) {
        super(firstName, lastName, dateOfBirth, readerTicket);
        this.department = department;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", readerTicket='" + readerTicket + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
