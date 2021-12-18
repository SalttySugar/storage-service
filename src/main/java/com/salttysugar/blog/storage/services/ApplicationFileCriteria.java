package com.salttysugar.blog.storage.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationFileCriteria {
    String id;
    String name;
    List<String> ids;
    String extension;
    List<String> extensions;
}
