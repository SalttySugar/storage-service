package com.salttysugar.blog.storage.file.domain.model.storable;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
public class StorableFile implements Storable {
    private final File file;
    @Override
    public String getFileName() {
        return file.getName();
    }

    @SneakyThrows
    @Override
    public Mono<Void> moveTo(Path path) {
        return Mono.create(sink -> {
            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();

            channel.write(buffer, 0, null, new CompletionHandler<>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    sink.success();
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    sink.error(exc);
                }
            });
        });
    }
}
