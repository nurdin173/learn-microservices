package id.co.learn.backend.repository;

import id.co.learn.backend.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select c from Category c where c.deleted=false " +
            "and (" +
            " lower(c.name) like lower(concat('%', :sSearch, '%')) )")
    Page<Category> getPageable(String sSearch, Pageable pageable);
}
