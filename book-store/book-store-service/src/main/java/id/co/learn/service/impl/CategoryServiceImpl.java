package id.co.learn.service.impl;

import id.co.learn.backend.model.Category;
import id.co.learn.backend.repository.CategoryRepository;
import id.co.learn.core.common.utility.DataTableObject;
import id.co.learn.service.CategoryService;
import id.co.learn.service.wrapper.CategoryWrapper;
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
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    private Category toEntity(CategoryWrapper categoryWrapper) {
        Category model = new Category();

        BeanUtils.copyProperties(categoryWrapper, model);

        return model;
    }

    private CategoryWrapper toWrapper(Category model) {
        CategoryWrapper wrapper = new CategoryWrapper();

        BeanUtils.copyProperties(model, wrapper);

        return wrapper;
    }

    private List<CategoryWrapper> toWrapperList(List<Category> models) {
        List<CategoryWrapper> wrappers = new ArrayList<>();

        for (Category model : models) {
            wrappers.add(toWrapper(model));
        }

        return wrappers;
    }

    @Override
    public Long getNum() {
        logger.debug("getNum() called");
        return categoryRepository.count();
    }

    @Override
    public CategoryWrapper save(CategoryWrapper entity) throws Exception {
        logger.info("save() called");
        return toWrapper(categoryRepository.save(toEntity(entity)));
    }

    @Override
    public CategoryWrapper getById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(this::toWrapper).orElse(null);
    }

    @Override
    public Boolean delete(Long pk) throws Exception {
        logger.info("delete() called");
        Optional<Category> category = categoryRepository.findById(pk);
        if(category.isPresent()) {
            Category model = category.get();
            model.setDeleted(true);

            categoryRepository.save(model);

            return true;
        }
        return false;
    }

    @Override
    public List<CategoryWrapper> findAll() throws Exception {
        return toWrapperList(categoryRepository.findAll());
    }

    @Override
    public Page<CategoryWrapper> getPageable(String sSearch, int startPage, int pageSize, Sort sort) throws Exception {
        logger.info("getPageable()");
        int page = DataTableObject.getPageFromStartAndLength(startPage, pageSize);
        try {
            Page<Category> pageableModel = categoryRepository.getPageable(sSearch, PageRequest.of(page, pageSize, sort));
            List<CategoryWrapper> bookWrapperList = toWrapperList(pageableModel.getContent());
            return new PageImpl<>(bookWrapperList, PageRequest.of(page, pageSize), pageableModel.getTotalElements());
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("Get Customer Error");
        }
    }
}
