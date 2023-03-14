package com.wsd.infrastructure.resolver;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用途描述 时间格式化
 *
 * @author wsd
 * @date 2021-06-11
 */
public class DateDeserializer extends JsonDeserializer<Date> {
    /**
     * 默认格式化
     */
    private static ThreadLocal<SimpleDateFormat> ymd =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    private static ThreadLocal<SimpleDateFormat> ymdhms =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String source = p.getText().trim();
        if ("".equals(source)) {
            return null;
        }
        Date date = null;
        try {
            if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
                date = ymdhms.get().parse(source);
                ymdhms.remove();
            } else {
                date = ymd.get().parse(source);
                ymd.remove();
            }
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(source + "无法转换为时间只支持[yyyy-MM-dd]和[yyyy-MM-dd HH:mm:ss]的格式");
        }
    }
}
