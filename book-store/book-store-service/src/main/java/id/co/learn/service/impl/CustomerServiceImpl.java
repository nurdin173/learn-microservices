package id.co.learn.service.impl;

import id.co.learn.backend.model.Customer;
import id.co.learn.backend.repository.CustomerRepository;
import id.co.learn.core.common.utility.DataTableObject;
import id.co.learn.service.CustomerService;
import id.co.learn.service.wrapper.CustomerWrapper;
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
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    private Customer toEntity(CustomerWrapper customerWrapper) {
        Customer model = new Customer();

        BeanUtils.copyProperties(customerWrapper, model);

        return model;
    }

    private CustomerWrapper toWrapper(Customer customer) {
        CustomerWrapper wrapper = new CustomerWrapper();

        BeanUtils.copyProperties(customer, wrapper);

        return wrapper;
    }

    private List<CustomerWrapper> toWrapperList(List<Customer> customers) {
        List<CustomerWrapper> wrappers = new ArrayList<>();
        for (Customer customer : customers) {
            wrappers.add(toWrapper(customer));
        }

        return wrappers;
    }

    @Override
    public Long getNum() {
        return customerRepository.count();
    }

    @Override
    public CustomerWrapper save(CustomerWrapper entity) throws Exception {
        return toWrapper(customerRepository.save(toEntity(entity)));
    }

    @Override
    public CustomerWrapper getById(Long id) throws Exception {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(this::toWrapper).orElse(null);
    }

    @Override
    public Boolean delete(Long pk) throws Exception {
        Optional<Customer> customer = customerRepository.findById(pk);
        if(customer.isPresent()){
            Customer model = customer.get();
            model.setDeleted(true);

            customerRepository.save(model);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CustomerWrapper> findAll() throws Exception {
        logger.info("findAll()");
        return toWrapperList(customerRepository.findAll());
    }

    @Override
    public Page<CustomerWrapper> getPageable(String sSearch, int startPage, int pageSize, Sort sort) throws Exception {
        logger.info("getPageable()");
        int page = DataTableObject.getPageFromStartAndLength(startPage, pageSize);
        try {
            Page<Customer> pageableModel = customerRepository.getPageable(sSearch, PageRequest.of(page, pageSize, sort));
            List<CustomerWrapper> bookWrapperList = toWrapperList(pageableModel.getContent());
            return new PageImpl<>(bookWrapperList, PageRequest.of(page, pageSize), pageableModel.getTotalElements());
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("Get Customer Error");
        }
    }
}
