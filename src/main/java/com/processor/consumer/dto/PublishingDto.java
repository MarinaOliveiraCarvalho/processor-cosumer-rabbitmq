package com.processor.consumer.dto;

import java.util.UUID;

public record PublishingDto(UUID id, Long likes, String comments, String img){
}
