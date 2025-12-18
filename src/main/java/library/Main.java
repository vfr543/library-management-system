package library;

public class Main {
    public static void main(String[] args) {
        // Создание библиотеки
        Library library = new Library();

        // Добавление книг
        library.addBook(new Book(1, "Война и мир",
                "Л.Н. Толстой", 1869, "978-5-17-090335-2"));
        library.addBook(new Book(2, "Преступление и наказание",
                "Ф.М. Достоевский", 1866, "978-5-17-090336-9"));
        library.addBook(new Book(3, "Анна Каренина",
                "Л.Н. Толстой", 1877, "978-5-17-090337-6"));

        // Поиск книг
        System.out.println("Книги Толстого:");
        for (Book book : library.findBooksByAuthor("Л.Н. Толстой")) {
            System.out.println("  " + book.getTitle());
        }

        // Выдача и возврат книги
        System.out.println("\nВыдача книги ID 1:");
        if (library.borrowBook(1)) {
            System.out.println("  Книга выдана успешно");
        }

        System.out.println("\nДоступные книги:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println("  " + book.getTitle());
        }

        // Журнал операций
        library.printOperationLog();
    }
}