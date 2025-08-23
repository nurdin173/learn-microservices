package id.co.learn.core.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommonService<T, Z> {
    Long getNum();

    T save (T entity) throws Exception;

    T getById (Long id) throws Exception;

    Boolean delete (Z pk) throws Exception;

    List<T> getPageableList() throws Exception;

    Page<T> getPageableList(String sSearch, int startPage, int pageSize, Sort sort) throws Exception;
}
