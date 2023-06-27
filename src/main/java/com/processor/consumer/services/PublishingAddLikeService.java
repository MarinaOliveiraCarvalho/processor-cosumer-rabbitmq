package com.processor.consumer.services;

import com.processor.consumer.dto.PublishingDto;
import com.processor.consumer.feignClient.integration.CrudPublishingIntegration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PublishingAddLikeService {

    @Autowired
    CrudPublishingIntegration crudPublishingIntegration;

    public void addLikeById(String idPublishing){
        try {
            log.info("Begin add like");
            PublishingDto publishingDto = crudPublishingIntegration.addById(UUID.fromString(idPublishing));
            log.info("success add like");
            log.info("likes for new value likes: {}", publishingDto.likes().toString());

        }catch (Exception e){
            log.error("ERROR on add like idPublishing: "+ idPublishing);
            throw new RuntimeException("ERROR on add like idPublishing: "+ idPublishing);
        }

    }
}
