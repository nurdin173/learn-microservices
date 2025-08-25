package id.co.learn.service.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import id.co.learn.core.common.wrapper.ReferenceBaseWrapper;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class CategoryWrapper extends ReferenceBaseWrapper {
    private String name;
}
