package homeworks.homework_4;

public class HomeworkMain {
    public static void main(String[] args) {
        /**
         * Используя hibernate, создать таблицы:
         * 1. Post(публикация) (Id, text)
         * 2. PostComment(комментарий к публикации) (id, text, post_id)
         * Написать стандартные CRUD-методы: создание, загрузка, изменение, удаление.
         *
         * Доп задание:
         *
         * 1. В сущностях Post и PostComment добавить поля timestamp с датами.
         * 2. Создать таблицу Users(id, name) и в сущностях Post и PostComment добавить ссылку на User.
         * 3. Реализовать методы:
         *      * Загрузить все комментарии публикации (id Post -> allPostComment())
         *      * Загрузить все посты по id User (в метод передается id -> allPostByUser()))
         *      * ** Загрузить все комментарии по id User (в метод передается id -> allCommentByUser())
         *      * *** По id User загрузить Users, под чьими публикациями он оставлял комменты (userId -> List<User>)
         *
         * Всего получается таблиц: User; Post; PostComment; Тут всё один ко многим.
         * Post - ссылка на юзера кто пост написал (user_id)
         * postComment - ссылка на юзера, кто комментарий написал (user_id), также есть ссылка на post, под которым написан комментарий
         *
         * Замечание: Можно использовать любую БД!
         */
    }
}
