package com.zlrx.retry.demo.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface RetryService {

    @Retryable(maxAttempts = 4, value = RuntimeException.class, backoff = @Backoff(delay = 500))
    void save();

    @Recover
    void recover(RuntimeException e);

    void read();

}
