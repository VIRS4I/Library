import library.LibraryManagementSystem;
import library.items.*;
import library.persons.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static LibraryManagementSystem library = new LibraryManagementSystem();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = false;
        while (!flag) {
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "добавить книгу":
                    addBook();
                    break;
                case "удалить книгу":
                    removebook();
                    break;
                case "обновить количество книг":
                    updateBook();
                    break;
                case "добавить читателя":
                    addReader();
                    break;
                case "удалить читателя":
                    removeReader();
                    break;
                case "вернуть книгу":
                    returnBook();
                    break;
                case "поиск книг":
                    searchLibraryItems();
                    break;
                case "поиск читателей":
                    searchReaders();
                    break;
                case "взять книгу":
                    borrowBook();
                case "end", "конец":
                    flag = true;
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }

}
    private static void addBook() {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();
        System.out.print("Введите год издания книги: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Введите количество экземпляров: ");
        int copies = scanner.nextInt();
        System.out.println("Книга художественная ? да/нет");
        String ch = scanner.nextLine().toLowerCase();
        if (ch.equalsIgnoreCase("да")){
            System.out.print("Введите жанр книги: ");
            String genre = scanner.nextLine();
            Book book = new FictionBook(title, author, year, isbn, genre);
            library.addBook(book, copies);
        }else{
            System.out.print("Введите предметную область книги: ");
            String subjectArea = scanner.nextLine();
            Book book = new NonFictionBook(title, author, year, isbn, subjectArea);
            library.addBook(book, copies);
        }
        System.out.println("Книга добавлена.");
    }
    private static void removebook(){
        System.out.print("Введите ISBN книги: ");
        String isbn = scanner.nextLine();
        LibraryItem item = library.searchLibraryItems(isbn).stream().findFirst().orElse(null);
        if (item != null) {
            library.removeBook(item);
            System.out.println("Книга удалена.");
        } else {
            System.out.println("Книга не найдены.");
        }
    }
    private static void updateBook(){
        System.out.print("Введите ISBN книги: ");
        String isbn = scanner.nextLine();
        LibraryItem item = library.searchLibraryItems(isbn).stream().findFirst().orElse(null);
        System.out.print("Введите новое количество книг: ");
        int col = scanner.nextInt();
        scanner.nextLine();
        if (item != null) {
            library.updateBook(item, col);
            System.out.println("Колличество книг изменено ");
        } else {
            System.out.println("Книга не найдены.");
        }
    }

    private static void addReader() {
        System.out.print("Введите имя читателя: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию читателя: ");
        String lastName = scanner.nextLine();
        System.out.print("Введите номер читательского билета: ");
        String readerTicket = scanner.nextLine();
        System.out.print("Введите дату рождения (ГГГГ-ММ-ДД): ");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Читатель является студентом или преподавателем? (s/p): ");
        String type = scanner.nextLine();

        Reader reader;
        if (type.equalsIgnoreCase("s")) {
            System.out.print("Введите номер студенческого билета: ");
            String studentId = scanner.nextLine();
            reader = new Student(firstName, lastName, dateOfBirth, readerTicket, studentId);
        } else {
            System.out.print("Введите департамент: ");
            String department = scanner.nextLine();
            reader = new Professor(firstName, lastName, dateOfBirth, readerTicket, department);
        }
        library.addReader(reader);
    }

    private static void removeReader(){
        System.out.print("Введите номер читательского билета: ");
        String readerTicket = scanner.nextLine();
        Reader reader = library.searchReaders(readerTicket).stream().findFirst().orElse(null);

        if (reader != null) {
            library.removeReader(reader);
            System.out.println("Читатель удалён ");
        } else {
            System.out.println("Читатель не найдены.");
        }
    }

    private static void borrowBook() {
        System.out.print("Введите номер читательского билета: ");
        String readerTicket = scanner.nextLine();
        System.out.print("Введите ISBN книги: ");
        String isbn = scanner.nextLine();

        Reader reader = library.searchReaders(readerTicket).stream().findFirst().orElse(null);
        LibraryItem item = library.searchLibraryItems(isbn).stream().findFirst().orElse(null);

        if (reader != null && item != null) {
            boolean success = library.borrowItem(reader, item);
            if (success) {
                System.out.println("Книга выдана.");
            } else {
                System.out.println("Недостаточно экземпляров.");
            }
        } else {
            System.out.println("Читатель или книга не найдены.");
        }
    }

    private static void returnBook() {
        System.out.print("Введите номер читательского билета: ");
        String readerTicket = scanner.nextLine();
        System.out.print("Введите ISBN книги: ");
        String isbn = scanner.nextLine();

        Reader reader = library.searchReaders(readerTicket).stream().findFirst().orElse(null);
        LibraryItem item = library.searchLibraryItems(isbn).stream().findFirst().orElse(null);

        if (reader != null && item != null) {
            library.returnItem(reader, item);
            System.out.println("Книга возвращена.");
        } else {
            System.out.println("Читатель или книга не найдены.");
        }
    }

    private static void searchLibraryItems() {
        System.out.print("Введите поисковый запрос: ");
        String query = scanner.nextLine();

        List<LibraryItem> results = library.searchLibraryItems(query);

        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            for (LibraryItem item : results) {
                System.out.println(item);
            }
        }
    }

    private static void searchReaders() {
        System.out.print("Введите поисковый запрос: ");
        String query = scanner.nextLine();

        List<Reader> results = library.searchReaders(query);

        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            for (Reader reader : results) {
                System.out.println(reader);
            }
        }
    }
}