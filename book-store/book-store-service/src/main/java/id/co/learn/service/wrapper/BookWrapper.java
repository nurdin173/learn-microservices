package id.co.learn.service.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import id.co.learn.core.common.wrapper.ReferenceBaseWrapper;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class BookWrapper extends ReferenceBaseWrapper {
    private String title;
    private String author;
    private String publisher;
    private Boolean available;
    private BigDecimal price;
}
