package com.epam.student.krynytskyi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;


public final class JSONSerializer {

    private static final String CONTENT_TYPE = "application/json";
    private static final Logger LOGGER = Logger.getLogger(JSONSerializer.class);
    private ObjectMapper mapper = new ObjectMapper();

    public String getContentType() {
        return CONTENT_TYPE;
    }


    public void serialize(OutputStream stream, Object o) {
        try {
            mapper.writeValue(stream, o);
        } catch (Exception e) {
            LOGGER.warn("Cannot serialize object.", e);
            throw new RuntimeException("Cannot serialize object", e);
        }
    }


    public <T> T deserialize(InputStream stream, Class<T> c) {
        try {
            return mapper.readValue(stream, c);
        } catch (Exception e) {
            LOGGER.warn("Cannot deserialize object", e);
            throw new RuntimeException("Cannot deserialize object", e);
        }
    }

}
