package seminars.seminar_4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import seminars.seminar_4.entity.Author;
import seminars.seminar_4.entity.Book;

// JPA. Hibernate
public class JPAMain {
    public static void main(String[] args) {

        // ORM - Object Relation Model
        // JPA - (Jakarta Persistence Api) - спецификация ORM
        // Hibernate


        Configuration configuration = new Configuration();
        configuration.configure(); // метод считывания информации с hibernate.cfg.xml

        try(SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            // sessionFactory <-> connection
//            withSessionCRUD(sessionFactory);
            withSession(sessionFactory);

        }

    }

    private static void withSession(SessionFactory sessionFactory) {
//        try(Session session = sessionFactory.openSession()) {
//            Book book = session.find(Book.class, 1L);
//            System.out.println(book);
//        }

//        try (Session session = sessionFactory.openSession()){
//            Author author = session.find(Author.class, 1L);
//
//            for (Book book : author.getBookList()) {
//                System.out.println(book);
//            }
//        }
    }

    private static void withSessionCRUD(SessionFactory sessionFactory) {
        // session <-> statement
        try(Session session = sessionFactory.openSession()) {
            Author author = session.find(Author.class, 1L); // поиск и вывод объекта в БД
            System.out.println("Author 1: " + author);
        }

        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            // обязательно при операциях добавления, удаления и изменения, открывать и коммитить транзакцию.
            Author author = new Author();
            author.setId(2L);
            author.setName("Michael");
            session.persist(author); // добавление объекта в БД
            transaction.commit();
        }

        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Author toUpdate = session.find(Author.class, 1L); // сначала берем из БД объект
            toUpdate.setName("Esmira"); // обновляем имя
            session.merge(toUpdate); // метод обновления данных в БД
            transaction.commit();
        }

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Author deletedAuthor = session.find(Author.class, 2L); // получаем объект по заданному id из БД
            session.remove(deletedAuthor); // метод удаления объекта
            transaction.commit(); // сохранение транзакции
        }
    }
}
