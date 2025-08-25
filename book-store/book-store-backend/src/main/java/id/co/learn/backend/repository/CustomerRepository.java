package id.co.learn.backend.repository;

import id.co.learn.backend.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select c from Customer c where c.deleted=false " +
            "and (" +
            " lower(c.name) like lower(concat('%', :sSearch, '%') ) or" +
            " lower(c.email) like lower(concat('%', :sSearch, '%') ) " +
            ")")
    Page<Customer> getPageable(String sSearch ,Pageable pageable);
}
