package id.co.learn.service.impl;

import id.co.learn.backend.model.Book;
import id.co.learn.backend.repository.BookRepository;
import id.co.learn.core.common.utility.DataTableObject;
import id.co.learn.service.BookService;
import id.co.learn.service.wrapper.BookWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    private Book toEntity(BookWrapper wrapper) {
        Book model = new Book();

        BeanUtils.copyProperties(wrapper, model);

        return model;
    }

    private BookWrapper toWrapper(Book model) {
        BookWrapper wrapper = new BookWrapper();

        BeanUtils.copyProperties(model, wrapper);

        return wrapper;
    }

    private List<BookWrapper> toWrapperList(List<Book> model) {
        List<BookWrapper> wrapperList = new ArrayList<>();

        for (Book book : model) {
            wrapperList.add(toWrapper(book));
        }
        return wrapperList;
    }

    @Override
    public Long getNum() {
        logger.info("getNum()");
        return bookRepository.count();
    }

    @Override
    public BookWrapper save(BookWrapper entity)  {
        return toWrapper(bookRepository.save(toEntity(entity)));
    }

    @Override
    public BookWrapper getById(Long id) {
        logger.info("getById({})", id);
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::toWrapper).orElse(null);
    }

    @Override
    public Boolean delete(Long pk) throws Exception {
        logger.info("delete()");
        try {
            Optional<Book> book = bookRepository.findById(pk);
            if (book.isPresent()) {
                Book model = book.get();

                model.setDeleted(true);
                bookRepository.save(model);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("Delete Book Error");
        }
    }

    @Override
    public List<BookWrapper> findAll() {
        logger.info("findAll()");
        return toWrapperList(bookRepository.findAll());
    }

    @Override
    public Page<BookWrapper> getPageable(String sSearch, int startPage, int pageSize, Sort sort) throws Exception {
        logger.info("getPageable()");
        int page = DataTableObject.getPageFromStartAndLength(startPage, pageSize);
        try {
            Page<Book> pageableModel = bookRepository.getPageable(sSearch, PageRequest.of(page, pageSize, sort));
            List<BookWrapper> bookWrapperList = toWrapperList(pageableModel.getContent());
            return new PageImpl<>(bookWrapperList, PageRequest.of(page, pageSize), pageableModel.getTotalElements());
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("Get Book Error");
        }
    }

    @Override
    public BookWrapper update(BookWrapper bookWrapper) throws IllegalThreadStateException {
        logger.info("update()");
        if(bookWrapper.getId() == null){
            throw new IllegalStateException("Id cannot be null");
        }
        BookWrapper oldData = getById(bookWrapper.getId());

        BeanUtils.copyProperties(bookWrapper, oldData, "id", "version", "createdDate", "createdBy");

        return toWrapper(bookRepository.save(toEntity(oldData)));
    }
}
