package com.salttysugar.blog.storage.file.core.filetype.resolver;

import com.salttysugar.blog.storage.common.FunctionalUtils;
import com.salttysugar.blog.storage.file.constant.FileType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.FileNameUtils;

import java.util.Collection;
import java.util.Optional;

import static com.salttysugar.blog.storage.common.FunctionalUtils.idenity;

@RequiredArgsConstructor
public class GenericFileTypeResolver implements FileTypeResolver {
    protected final Collection<String> matchers;
    protected final FileType matchedType;

    protected boolean test(String ext) {
        return matchers.stream().anyMatch(s -> s.equals(ext));
    }


    @Override
    public FileType resolve(String filename) {
//        Optional.of(filename)
//                .map(FileNameUtils::getExtension)
//                .map(this::test)
//                .filter(Boolean::booleanValue)
//                .map(idenity());

        String extension = FileNameUtils.getExtension();

    }
}
