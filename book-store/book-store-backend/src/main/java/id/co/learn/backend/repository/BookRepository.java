package id.co.learn.backend.repository;

import id.co.learn.backend.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByAuthorsContainingIgnoreCase(String authors, Pageable pageable);

    Page<Book> findByPublisherContainingIgnoreCase(String publisher, Pageable pageable);

    Page<Book> findByAvailableIsTrue(Boolean isAvail, Pageable pageable);

    @Query(value = "select b from Book b where b.deleted=false " +
            "and (" +
            " lower(b.title) like lower(concat('%', :sSearch, '%'))" +
            "or lower(b.author) like lower(concat('%', :sSearch, '%')) " +
            "or lower(b.publisher) like lower(concat('%', :sSearch, '%')) " +
            ")")
    Page<Book> getPageable(String sSearch, Pageable pageable);

}
