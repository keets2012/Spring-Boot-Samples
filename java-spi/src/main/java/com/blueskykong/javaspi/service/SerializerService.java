package com.blueskykong.javaspi.service;

import com.blueskykong.javaspi.serializer.JavaSerializer;
import com.blueskykong.javaspi.serializer.ObjectSerializer;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * @author keets
 * @data 2018/5/14.
 */
@Service
public class SerializerService {


    public ObjectSerializer getObjectSerializer() {
        ServiceLoader<ObjectSerializer> serializers = ServiceLoader.load(ObjectSerializer.class);

        final Optional<ObjectSerializer> serializer = StreamSupport.stream(serializers.spliterator(), false)
                .findFirst();

        return serializer.orElse(new JavaSerializer());
    }
}
