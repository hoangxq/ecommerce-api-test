package com.demo.web.exception.handle;

import com.demo.web.dto.response.utils.ErrorResponse;
import com.demo.web.dto.response.utils.Response;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Response> unknownErrorHandler(Exception e) {
        log.error("Unexpected Exception", e);
        String errMsg = "Unknow Error";
        return ResponseUtils.internalErr(
                ErrorResponse.of(ServiceError.UNEXPECTED_EXCEPTION.getMessageKey(), errMsg));
    }

}
