package com.objcoding.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/10/13
 */
public class Channels {

    interface InputChannel {
        String CHANNEL_NAME = "inputChannel";
        @Input(CHANNEL_NAME)
        SubscribableChannel input();
    }

    interface OutputChannel {
        String CHANNEL_NAME = "outputChannel";
        @Output(CHANNEL_NAME)
        MessageChannel output();
    }
}
