package dev.bstk.okkutil.fixture;

import dev.bstk.okkutil.json.jackson.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Fixture {

  private static final Logger LOG = LoggerFactory.getLogger(Fixture.class);

  private static final String FIXTURE_PATH_START_PREFIX = "/";
  private static final String FIXTURE_PATH_JSON_EXYENSION = ".json";
  private static final String FIXTURE_PATH_DEFAULT_SOURCE_TEST = "src/test/resources";

  private Fixture() { }

  private static <T> T fixure(String pathFixture, final Class<T> clazz) {
    return fixure(FIXTURE_PATH_DEFAULT_SOURCE_TEST, pathFixture, clazz);
  }

  public static <T> T fixure(final String pathResource, String pathFixture, final Class<T> clazz) {
    try {
      if (Objects.isNull(pathFixture) || pathFixture.isEmpty() || pathFixture.isBlank()) {
        throw new IllegalArgumentException("Json file path cannot be null or empty!");
      }

      if (!pathFixture.startsWith(FIXTURE_PATH_START_PREFIX)) {
        LOG.warn("Fixture Path without the '/' prefix. Don't worry!");
        pathFixture += FIXTURE_PATH_START_PREFIX + pathFixture;
      }

      if (!pathFixture.endsWith(FIXTURE_PATH_JSON_EXYENSION)) {
        LOG.warn("Fixture Path without the '.json' extension. Don't worry!");
        pathFixture += pathFixture + FIXTURE_PATH_JSON_EXYENSION;
      }

      final var json = new File(String.format("%s%s", pathResource, pathFixture));
      return Json.mapper().readValue(json, clazz);
    } catch (IOException ex) {
      throw new IllegalArgumentException("Unable to locate Fixture json!", ex);
    }
  }
}
