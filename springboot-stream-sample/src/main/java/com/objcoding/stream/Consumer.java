package com.objcoding.stream;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;

/**
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/7/5
 */
@Slf4j
@EnableBinding(Channels.InputChannel.class)
public class Consumer {

    @StreamListener(Channels.InputChannel.CHANNEL_NAME)
    public void consume(@Payload String payload,
                        @Header(AmqpHeaders.CHANNEL) Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {

        try {
            log.info("consume: {}", payload);
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
