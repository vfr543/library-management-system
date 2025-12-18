package library;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }
    // Вложенный статический класс для журнала операций
    public static class OperationLog {
        public enum OperationType {
            ADD_BOOK, BORROW, RETURN
        }

        public static class LogEntry {
            private OperationType type;
            private LocalDateTime time;
            private String message;

            public LogEntry(OperationType type, String message) {
                this.type = type;
                this.time = LocalDateTime.now();
                this.message = message;
            }

            @Override
            public String toString() {
                return String.format("[%s] %s: %s", time, type, message);
            }
        }

        private List<LogEntry> entries;

        public OperationLog() {
            entries = new ArrayList<>();
        }

        public void addEntry(OperationType type, String message) {
            entries.add(new LogEntry(type, message));
        }

        public void printLog() {
            System.out.println("=== Журнал операций ===");
            for (LogEntry entry : entries) {
                System.out.println(entry);
            }
            System.out.println("=======================");
        }
    }

    private OperationLog log = new OperationLog();

    public void addBook(Book book) {
        books.add(book);
        log.addEntry(OperationLog.OperationType.ADD_BOOK,
                "Добавлена: " + book.getTitle());
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean borrowBook(int id) {
        Book book = findBookById(id);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            log.addEntry(OperationLog.OperationType.BORROW,
                    "Выдана: " + book.getTitle());
            return true;
        }
        return false;
    }

    public boolean returnBook(int id) {
        Book book = findBookById(id);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            log.addEntry(OperationLog.OperationType.RETURN,
                    "Возвращена: " + book.getTitle());
            return true;
        }
        return false;
    }

    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    public void printOperationLog() {
        log.printLog();
    }
    public String getStatistics() {
        int total = books.size();
        int available = getAvailableBooks().size();
        int borrowed = total - available;

        return String.format("Статистика библиотеки:\n" +
                        "Всего книг: %d\n" +
                        "Доступно: %d\n" +
                        "Выдано: %d",
                total, available, borrowed);
    }


}