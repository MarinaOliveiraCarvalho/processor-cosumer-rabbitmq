package com.processor.consumer.consumer;

import com.processor.consumer.dto.PublishingAddLikeDto;
import com.processor.consumer.feignClient.integration.CrudPublishingIntegration;
import com.processor.consumer.services.PublishingAddLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddLikeConsumer {

    @Autowired
    PublishingAddLikeService publishingAddLikeService;

    @RabbitListener(queues = {"${api.rabbitmq.publishing.add-like.queue}"})
    public void receiveCreatedAccountEvent(PublishingAddLikeDto publishingAddLikeDto) {
        try {
            log.info("**********************************************************");
            log.info("RabbitListener ---------------");
            log.info("New event add like arrive  PublishingAddLikeDto {}", publishingAddLikeDto.id().toString());

            log.info(publishingAddLikeDto.id());
            publishingAddLikeService.addLikeById(publishingAddLikeDto.id());

            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (Exception be) {
            log.error("ERROR Critical receive message add Like PublishingAddLikeDto {}", publishingAddLikeDto.id().toString());
            log.error(be.getMessage(), be);
        }
    }
}
