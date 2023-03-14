package com.wsd.infrastructure.resolver;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wsd.infrastructure.em.AbstractEnum;

import java.io.IOException;


/**
 * 序列化，返回数字到前端
 *
 * @author wsd
 * @date 2021-06-21
 */
public class BaseEnumSerializer extends JsonSerializer<AbstractEnum> {
    @Override
    public void serialize(AbstractEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeObject(value.getValue());
        }
    }
}
