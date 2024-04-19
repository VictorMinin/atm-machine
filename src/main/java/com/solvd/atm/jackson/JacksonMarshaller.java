package com.solvd.atm.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class JacksonMarshaller {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(JacksonMarshaller.class);
    private static final String path = "src/main/resources/jackson-json/";

    public static <T> void writeFile(T object) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String fileName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Timestamp(System.currentTimeMillis())) + ".json";
            String filePath = path + fileName;
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), object);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public static <T> T readValue(String path, Class<T> type) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return (T) objectMapper.readValue(new File(path), type);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return null;
    }
}
