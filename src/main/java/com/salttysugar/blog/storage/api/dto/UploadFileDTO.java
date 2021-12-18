package com.salttysugar.blog.storage.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salttysugar.blog.storage.model.Storable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public final class UploadFileDTO {
    String name;
    @NotNull(message = "could not find file. Please add \"file\" to your multipart/form-data request")
    Storable file;
    @NotEmpty(message = "field: \"owner_id\" cannot be empty")
    @JsonProperty("owner_id")
    String OwnerId;
}
