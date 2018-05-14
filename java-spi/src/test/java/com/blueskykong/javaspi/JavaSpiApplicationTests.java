package com.blueskykong.javaspi;

import com.blueskykong.javaspi.exception.ObjectSerializerException;
import com.blueskykong.javaspi.serializer.ObjectSerializer;
import com.blueskykong.javaspi.service.SerializerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaSpiApplicationTests {

    @Autowired
    private SerializerService serializerService;

    @Test
    public void serializerTest() throws ObjectSerializerException {
        ObjectSerializer objectSerializer = serializerService.getObjectSerializer();
        System.out.println(objectSerializer.getSchemeName());
        byte[] arrays = objectSerializer.serialize(Arrays.asList("1", "2", "3"));
        ArrayList list = objectSerializer.deSerialize(arrays, ArrayList.class);
        Assert.assertArrayEquals(Arrays.asList("1", "2", "3").toArray(), list.toArray());
    }
}
