package com.wsd.infrastructure.resolver;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author wsd
 * @date 2021年08月10日 17:11
 **/
public class LocalDateSerializer extends JsonSerializer<LocalDate> {
    private static final String YMD = "yyyy-MM-dd";

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (localDate == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeObject(DateTimeFormatter.ofPattern(YMD).format(localDate));
        }
    }
}
