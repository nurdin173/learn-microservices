package id.co.learn.service.impl;

import id.co.learn.backend.model.Book;
import id.co.learn.backend.repository.BookRepository;
import id.co.learn.core.common.utility.DataTableObject;
import id.co.learn.service.BookService;
import id.co.learn.service.wrapper.BookWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private Logger logger = LogManager.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

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
    public BookWrapper save(BookWrapper entity) throws Exception {
        return toWrapper(bookRepository.save(toEntity(entity)));
    }

    @Override
    public BookWrapper getById(Long id) throws Exception {
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
    public List<BookWrapper> getPageableList() throws Exception {
        logger.info("getPageableList()");
        return toWrapperList(bookRepository.findAll());
    }

    @Override
    public Page<BookWrapper> getPageableList(String sSearch, int startPage, int pageSize, Sort sort) throws Exception {
        logger.info("getPageableList()");
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
}
