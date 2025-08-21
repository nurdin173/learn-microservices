package id.co.learn.core.common.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
public class CommonResponseGenerator<T> {
    public CommonResponse<T> commonSuccessResponse(T wrapper, String detail) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setStatus("200");
        commonResponse.setDetail(detail);
        commonResponse.setMessage("Success");
        commonResponse.setDatas(wrapper);

        return commonResponse;
    }

    public CommonResponse<T> commonFailureResponse(String detail) {
        CommonResponse<T> commonResponse = new CommonResponse<>();

        commonResponse.setStatus("400");
        commonResponse.setDetail(detail);
        commonResponse.setMessage("Failure");

        return commonResponse;
    }

    public CommonResponse<T> commonErrorResponse(String detail) {
        CommonResponse<T> commonResponse = new CommonResponse<>();

        commonResponse.setStatus("500");
        commonResponse.setDetail(detail);
        commonResponse.setMessage("Error");

        return commonResponse;
    }

    public CommonResponse<T> commonBadRequestResponse() {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setStatus("400");
        commonResponse.setDetail("An Error Occurred");
        commonResponse.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return commonResponse;
    }
}
