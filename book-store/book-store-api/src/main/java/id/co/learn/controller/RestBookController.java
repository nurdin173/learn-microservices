package id.co.learn.controller;

import id.co.learn.core.common.base.RestBaseController;
import id.co.learn.core.common.responses.CommonResponse;
import id.co.learn.core.common.responses.CommonResponseGenerator;
import id.co.learn.service.BookService;
import id.co.learn.service.wrapper.BookWrapper;
import id.co.learn.service.wrapper.request.RequestWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")
@Tag(name = "Book Services")
@RequiredArgsConstructor
public class RestBookController extends RestBaseController {
    private static final Logger logger = LogManager.getLogger(RestBookController.class);
    private final CommonResponseGenerator<Object> comGen;

    private final BookService service;

    @PostMapping(value = "/pageable")
    public CommonResponse<Object> getPageable(HttpServletRequest request,
                                              @RequestBody RequestWrapper wrapper) {
        try {
            Page<BookWrapper> result = service.getPageable(wrapper.getSearch()
                    , wrapper.getPage(), wrapper.getLength(),
                    Sort.by(Sort.Direction.fromString(wrapper.getSortMethod()), wrapper.getSortBy()));
            return comGen.commonSuccessResponse(result, "Get Pageable");
        } catch (Exception e) {
            return comGen.commonErrorResponse("Error Occurred");
        }
    }

    @PostMapping(value = "/")
    public CommonResponse<Object> save(HttpServletRequest request,
                                       @RequestBody BookWrapper wrapper) {
        try {
            wrapper.setVersion(1);
            wrapper.setDeleted(false);
            return comGen.commonSuccessResponse(service.save(wrapper), "Save New Book");
        } catch (Exception e) {
            return comGen.commonErrorResponse("Error Occurred");
        }
    }

    @PutMapping(value = "/")
    public CommonResponse<Object> update(HttpServletRequest request,
                                         @RequestBody BookWrapper wrapper) {
        try {
            return comGen.commonSuccessResponse(service.update(wrapper), "Update Book");
        } catch (Exception e) {
            return comGen.commonErrorResponse("Error Occurred");
        }
    }

    @GetMapping(value = "/")
    public CommonResponse<Object> getById(HttpServletRequest request,
                                          @RequestParam Long id) {
        return null;
    }

    @DeleteMapping(value = "/")
    public CommonResponse<Object> delete(HttpServletRequest request, @RequestParam Long id) {
        return null;
    }
}
