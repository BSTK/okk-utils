package dev.bstk.okkutil.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class Json {

  private static final String ERROR_MESSAGE = "Unable to parse!";
  private static final ObjectMapper JSON_MAPPER = new ObjectMapper()
    .registerModule(new JavaTimeModule());

  private Json() { }

  public static <T> T toObject(final byte[] payload, final Class<T> clazz) {
    try {
      return JSON_MAPPER.readValue(payload, clazz);
    } catch (IOException ex) {
      throw new IllegalArgumentException(ERROR_MESSAGE, ex);
    }
  }

  public static <T> T toObject(final Object object, final Class<T> clazz) {
    try {
      final var payload = toString(object);
      return JSON_MAPPER.readValue(payload, clazz);
    } catch (IOException ex) {
      throw new IllegalArgumentException(ERROR_MESSAGE, ex);
    }
  }

  public static String toString(final Object object) {
    try {
      return JSON_MAPPER.writeValueAsString(object);
    } catch (IOException ex) {
      throw new IllegalArgumentException(ERROR_MESSAGE, ex);
    }
  }

  public static ObjectMapper mapper() {
    return JSON_MAPPER;
  }
}
