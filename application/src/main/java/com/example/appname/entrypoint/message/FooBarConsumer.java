package com.example.appname.entrypoint.message;

import com.example.appname.model.FooBar;
import com.example.appname.usecase.SaveFooBar;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FooBarConsumer {

    private SaveFooBar saveFooBar;

    @SqsListener(value = "${sqs.queue.add-foobar}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    private void consumeFromSQS(@NotificationMessage final FooBarMessage fooBarMessage) {
        FooBar fooBar = fooBarMessage.toDomain();
        saveFooBar.execute(fooBar);
    }

}
