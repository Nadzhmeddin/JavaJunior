package homeworks.homework_4.crud;

import homeworks.homework_4.entity.Post;
import homeworks.homework_4.entity.PostComment;
import homeworks.homework_4.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PostCommentCrudMethods {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        try(SessionFactory sessionFactory = configuration.buildSessionFactory()) {
//            createComment(sessionFactory, 2L, 2L, 3L, "Comment to Post #2 by User #3");
//            findCommentById(sessionFactory, 3L);
//            updateComment(sessionFactory, 10L, "Updated comment by CRUD Method");
//            deleteComment(sessionFactory, 11L);

        }
    }

    /**
     * Метод поиска комментария по заданному идентификатору от пользователя
     * @param sessionFactory открытая фабрика сессий
     * @param id заданный идентификатор от пользователя
     */
    public static void findCommentById(SessionFactory sessionFactory, Long id) {
        try(Session session = sessionFactory.openSession()) {
            PostComment postComment = session.find(PostComment.class, id);
            System.out.println("Найден комментарий к посту: " + postComment);
        }
    }

    /**
     * Метод создания комментария по заданному идентификатору Post, идентификатору PostComment и текст комментария
     * @param sessionFactory открытая фабрика сессий
     * @param post_id идентификатор Post
     * @param comment_id идентификатор PostComment
     * @param title текст комментария
     */
    public static void createComment(SessionFactory sessionFactory, Long comment_id, Long post_id, Long user_id, String title) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            PostComment createdPostComment = new PostComment();
            Post post = new Post();
            User user = new User();
            post.setId(post_id);
            user.setId(user_id);

            createdPostComment.setId(comment_id);
            createdPostComment.setPost(post);
            createdPostComment.setUser(user);
            createdPostComment.setText(title);
            session.persist(createdPostComment);
            transaction.commit();
            System.out.println("Создан новый комментарий: " + createdPostComment);
        }
    }

    /**
     * Метод изменения текста комментария PostComment
     * @param sessionFactory открытая фабрика сессий
     * @param id идентификатор PostComment
     * @param title новый текст, необходимый к вставке
     */
    public static void updateComment(SessionFactory sessionFactory, Long id, String title) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            PostComment updatedComment = session.find(PostComment.class, id);
            updatedComment.setText(title);
            session.merge(updatedComment);
            transaction.commit();
            System.out.println("Комментарий успешно обновлен: " + updatedComment);
        }
    }

    /**
     * Метод удаления комментария по заданному идентификатору
     * @param sessionFactory открытая фабрика сессий
     * @param id идентификатор комментария к удалению
     */
    public static void deleteComment(SessionFactory sessionFactory, Long id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            PostComment deletedComment = session.find(PostComment.class, id);
            session.remove(deletedComment);
            transaction.commit();
            System.out.println("Комментарий успешно удален: " + deletedComment);
        }
    }

}
