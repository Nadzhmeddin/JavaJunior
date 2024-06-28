package seminars.seminar_4.entity;


import jakarta.persistence.*;

import javax.security.sasl.AuthorizeCallback;
import java.util.List;

// create table author(
// id bigint primary key
// name varchar(256)
// )

@Entity
@Table(name = "author")
public class Author {


    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "author")
//    @JoinTable(
//            name = "book",
//            joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = @JoinColumn(name = "author_id"))
//    @ManyToMany(fetch = FetchType.EAGER) // EAGER - означает загрузка сразу вместе с автором
//    @JoinTable(
//            name = "author_book",
//            joinColumns = @JoinColumn(name = "author_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id")
//    )
//    private List<Book> bookList;


    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Book> getBookList() {
//        return bookList;
//    }
//
//    public void setBookList(List<Book> bookList) {
//        this.bookList = bookList;
//    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
