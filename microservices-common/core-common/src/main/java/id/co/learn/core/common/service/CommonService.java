package id.co.learn.core.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommonService<T, Z> {
    Long getNum();

    T save (T entity) throws Exception;

    T getById (Long id) throws Exception;

    Boolean delete (Z pk) throws Exception;

    List<T> findAll() throws Exception;

    Page<T> getPageable(String sSearch, int startPage, int pageSize, Sort sort) throws Exception;
}
