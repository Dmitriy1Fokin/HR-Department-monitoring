package ru.fds.hrdepartmentmonitoring.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeinClientErrorDecoder implements ErrorDecoder {
    @SneakyThrows
    @Override
    public Exception decode(String s, Response response) {
        int httpStatusCode = response.status();

        if(httpStatusCode == 404){
            throw new NotFoundException(response.body().toString());
        }
        String message = response.body().toString();
        return new Exception("unknown error: " + message);
    }
}
