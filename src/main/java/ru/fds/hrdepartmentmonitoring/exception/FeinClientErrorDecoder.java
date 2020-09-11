package ru.fds.hrdepartmentmonitoring.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeinClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        int httpStatusCode = response.status();

        if(httpStatusCode >= 400){
            throw new NotFoundException(response.body().toString());
        }
        String message = response.body().toString();
        return new Exception("unknown error: " + message);
    }
}
