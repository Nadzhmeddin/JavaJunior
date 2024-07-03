package homeworks.homework_4.crud;

import homeworks.homework_4.entity.Post;
import homeworks.homework_4.entity.PostComment;
import homeworks.homework_4.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserCrudMethods {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        try(SessionFactory sessionFactory = configuration.buildSessionFactory()) {
//            createUser(sessionFactory, 6L, "Artem");
//            findUserById(sessionFactory, 1L);
//            updateUser(sessionFactory, 5L, "Vladimir");
//            deleteUser(sessionFactory, 6L);
//            getAllPostByUserId(sessionFactory, 4L);
            getAllPostCommentsByUserId(sessionFactory, 4L);
        }
    }

    /**
     * Метод поиска пользователя по заданному идентификатору
     * @param sessionFactory открытая фабрика сессий
     * @param id идентификатор пользователя для поиска
     */
    public static void findUserById(SessionFactory sessionFactory, Long id) {
        try(Session session = sessionFactory.openSession()) {
            User findedUser = session.find(User.class, id);
            System.out.println("По заданному id найден User: " + findedUser);
        }
    }

    /**
     * Метод создания пользователя
     * @param sessionFactory открытая фабрика сессий
     * @param id новый идентификатор пользователя
     * @param name новое имя пользователя
     */
    public static void createUser(SessionFactory sessionFactory, Long id, String name) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User createdUser = new User();
            createdUser.setId(id);
            createdUser.setName(name);
            session.persist(createdUser);
            transaction.commit();
            System.out.println("Добавлен новый User: " + createdUser);
        }
    }

    /**
     * Метод обновления имени пользователя по заданному идентификатору
     * @param sessionFactory открытая фабрика сессий
     * @param id идентификатор пользователя, которого необходимо обновить
     * @param name новое имя пользователя
     */
    public static void updateUser(SessionFactory sessionFactory, Long id, String name) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User updatedUser = session.find(User.class, id);
            updatedUser.setName(name);
            session.merge(updatedUser);
            transaction.commit();
            System.out.println("Пользователь обновлен: " + updatedUser);
        }
    }

    /**
     * Метод удаления пользователя по заданному идентификатору
     * @param sessionFactory открытая фабрика сессий
     * @param id заданный идентификатор пользователя
     */
    public static void deleteUser(SessionFactory sessionFactory, Long id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User deletedUser = session.find(User.class, id);
            session.remove(deletedUser);
            transaction.commit();
            System.out.println("Данный пользователь удален: " + deletedUser);
        }
    }

    /**
     * Метод выгрузки всех публикаций user по идентификатору
     * @param sessionFactory открытая фабрика сессий
     * @param user_id заданный идентификатор пользователя
     */
    public static void getAllPostByUserId(SessionFactory sessionFactory, Long user_id) {
        try(Session session = sessionFactory.openSession()) {
            User user = session.find(User.class, user_id);
            List<Post> allPostByUser = user.getPostList();
            for (Post post : allPostByUser) {
                System.out.printf("Все посты юзера %s: %s\n", user_id, post);
            }
        }
    }

    /**
     * Метод выгрузки всех комментариев user по идентификатору
     * @param sessionFactory открытая фабрика сессий
     * @param user_id заданный идентификатор пользователя
     */
    public static void getAllPostCommentsByUserId(SessionFactory sessionFactory, Long user_id) {
        try(Session session = sessionFactory.openSession()) {
            User user  = session.find(User.class, user_id);
            List<PostComment> comments = user.getPostCommentList();
            for (PostComment comment : comments) {
                System.out.printf("Все комментарии юзера %s: %s\n", user_id, comment);
            }
        }
    }
}
