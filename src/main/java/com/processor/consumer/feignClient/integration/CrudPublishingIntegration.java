package com.processor.consumer.feignClient.integration;

import com.processor.consumer.dto.PublishingDto;
import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(url = "${integration.crud.v1.host}", name = "CrudPublishingIntegration")
public interface CrudPublishingIntegration {

    @GetMapping("/likes/add/{idPublishing}")
    PublishingDto addById(@PathVariable("idPublishing") UUID idPublishing) throws IntegrationException;
}
