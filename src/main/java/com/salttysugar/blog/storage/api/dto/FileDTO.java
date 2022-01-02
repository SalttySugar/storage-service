package com.salttysugar.blog.storage.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("File")
public final class FileDTO {
    String id;
    String name;
    String extension;
    @JsonProperty("owner_id")
    String ownerId;
    @JsonProperty("uploaded_on")
    Date uploadedOn;
    long size;
}
