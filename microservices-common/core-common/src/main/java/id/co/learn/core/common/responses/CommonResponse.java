package id.co.learn.core.common.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class CommonResponse<T> {
    private String status;
    private String message;
    private String detail;
    private T datas;
}
