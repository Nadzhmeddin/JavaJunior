package seminars.seminar_1;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class StreamBook {
    private static <T> T getRandom(List<? extends T> items) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(randomIndex);
    }

    public static void main(String[] args) {
        List<String> tags = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            tags.add("Tag #" + i);
        }

        List<String> authors = List.of("Tolstoy", "Gogol", "Duma", "Hugo","Bulgakov", "Martin");


        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Book book = new Book();
            book.setName("Book #" + i);
            book.setAuthor(getRandom(authors));

            LocalDate today = LocalDate.now();
            LocalDate randomDate =today.minusYears(ThreadLocalRandom.current().nextInt(100));
            book.setPublishDate(randomDate);

            book.setPages(ThreadLocalRandom.current().nextInt(100, 2500));
            book.setPrice(ThreadLocalRandom.current().nextInt(1_000, 100_000) * 1.0);


            int tagsCount = ThreadLocalRandom.current().nextInt(6);
            List<String> bookTags = new ArrayList<>();
            for(int j = 0; j < tagsCount; j++) {
                bookTags.add(getRandom(tags));
            }
            book.setTags(bookTags);

            books.add(book);
        }

//        System.out.println(findMostExpensiveBook(books));

//        System.out.println(printBooksWithLessPages(books));

//        System.out.println(findTags(books));

        System.out.println(groupByAuthor(books));

//        System.out.println(getHighPriceBook(books));
    }

    // Поиск самой дорогой книги
    static Book findMostExpensiveBook(List<Book> books) {
        Optional<Book> first = books.stream()
                .sorted((o1,o2) -> Double.compare(o2.getPrice(), o1.getPrice()))
                .peek(s -> System.out.println(s))
                .findFirst();

        return first.get();
    }

    // Поиск топ 5 самых коротких книг
    static List<Book> printBooksWithLessPages(List<Book> books) {
        return books.stream()
                .sorted((o1, o2) -> Integer.compare(o1.getPages(), o2.getPages()))
                .limit(5)
                .toList();
    }

    // Найти теги самых свежи книг
    static Set<String> findTags(List<Book> books) {
        return books.stream()
                .sorted((x,y) -> x.getPublishDate().compareTo(y.getPublishDate()))
                .limit(5) // Stream<Book>
                .flatMap(b -> b.getTags().stream())
                .collect(Collectors.toSet());

    }

    // Сгруппировать книги по авторам
    static Map<String, List<Book>> groupByAuthor(List<Book> books) {
        return books.stream()
                .collect(Collectors.groupingBy(it -> it.getAuthor()));
    }

    // Получить самую дорогую книгу по авторам
    static Map<String, Book> getHighPriceBook(List<Book> books) {
        return books.stream()
                .collect(Collectors.toMap(
                        b -> b.getAuthor(),
                        b -> b,
                        (a,b) -> {
                            if(a.getPrice() > b.getPrice()) {
                                return a;
                            }
                            return b;
                        }
                ));
    }





    static class Book {
        private String name;
        private String author;
        private LocalDate publishDate;
        private int pages;
        private double price;
        private List<String> tags;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public LocalDate getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(LocalDate publishDate) {
            this.publishDate = publishDate;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "name='" + name + '\'' +
                    ", author='" + author + '\'' +
                    ", publishDate=" + publishDate +
                    ", pages=" + pages +
                    ", price=" + price +
                    ", tags=" + tags +
                    '}';
        }
    }
}
