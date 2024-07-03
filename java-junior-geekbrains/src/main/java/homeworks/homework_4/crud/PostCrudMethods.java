package homeworks.homework_4.crud;

import homeworks.homework_4.entity.Post;
import homeworks.homework_4.entity.PostComment;
import homeworks.homework_4.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

public class PostCrudMethods {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
//            createPost(sessionFactory, 5L, 5L, "Post #5 by User #5");
//            findPostById(sessionFactory, 1L);
//            updatePost(sessionFactory, 5L, 4L,"Updated Post #5 by User#5 to User #4");
//            deletePostById(sessionFactory, 6L);
            getAllPostComment(sessionFactory, 2L);
        }
    }

    /**
     * Метод поиска Post по заданному id от пользователя
     * @param sessionFactory Фабрика
     * @param id заданный от пользователя
     */
    public static void findPostById(SessionFactory sessionFactory, Long id) {
        try (Session session = sessionFactory.openSession()){
            Post findedPost = session.find(Post.class, id);
            System.out.println("Найден пост: " + findedPost);
        }
    }

    /**
     * Метод создания Post
     * @param sessionFactory открытая фабрика сессий
     * @param post_id заданный идентификатор post
     * @param user_id заданный идентификатор user, кому принадлежит post
     * @param title заданное содержание Post
     */
    public static void createPost(SessionFactory sessionFactory, Long post_id, Long user_id, String title) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Post addedPost = new Post();
            User addedUser = new User();
            addedUser.setId(user_id);
            addedPost.setId(post_id);
            addedPost.setTitle(title);
            addedPost.setUser(addedUser);
            session.persist(addedPost);
            transaction.commit();
            System.out.println("Пост успешно добавлен: " +addedPost);
        }
    }

    /**
     * Метод изменения комментария
     * @param sessionFactory открытая фабрика
     * @param post_id идентификатор комментария полученный от пользователя
     * @param user_id идентификатор пользователя
     * @param title текст комментария, необходимый к изменению.
     */
    public static void updatePost(SessionFactory sessionFactory, Long post_id, Long user_id, String title) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Post updatedPost = session.find(Post.class, post_id);
            User updatedUser = new User();
            updatedUser.setId(user_id);
            updatedPost.setTitle(title);
            updatedPost.setUser(updatedUser);
            session.merge(updatedPost);
            transaction.commit();
            System.out.println("Пост успешно обновлен: " + updatedPost);
        }
    }

    /**
     * Метод удаления Post, а далее удаление PostComment по связанному id.
     * @param sessionFactory
     * @param id заданный от пользователя
     */
    public static void deletePostById(SessionFactory sessionFactory, Long id) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Post deletedPost = session.find(Post.class, id);
            session.remove(deletedPost);
            transaction.commit();
            System.out.println("Пост успешно удален: " + deletedPost);
        }
    }

    /**
     * Метод выгрузки всех комментариев
     * @param sessionFactory открытая фабрика сессий
     * @param post_id заданный идентификатор Post
     */
    public static void getAllPostComment(SessionFactory sessionFactory, Long post_id) {
        try(Session session = sessionFactory.openSession()) {
            Post post = session.find(Post.class, post_id);
            List<PostComment> comments = post.getPostCommentList();
            for (PostComment comment : comments) {
                System.out.printf("Комментарии к посту %s: %s\n", post_id, comment);
            }
        }
    }
}
