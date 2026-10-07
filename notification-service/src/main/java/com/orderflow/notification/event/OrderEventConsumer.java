package com.orderflow.notification.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);

    @KafkaListener(topics = "order-events", groupId = "notification-service")
    public void consume(OrderEvent event) {
        log.info("Order notification: orderId={}, userId={}, sku={}, quantity={}, status={}",
                event.orderId(), event.userId(), event.sku(), event.quantity(), event.status());
    }
}
