package seminars.seminar_4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "author_book")
public class AuthorBook {

    @Id
    private Long id;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Book book;
}
