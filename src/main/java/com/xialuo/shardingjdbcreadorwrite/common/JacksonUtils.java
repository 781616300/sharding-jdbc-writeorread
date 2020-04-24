package com.xialuo.shardingjdbcreadorwrite.common;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


public class JacksonUtils {

  private static ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setSerializationInclusion(Include.NON_NULL);
  }

  /**
   * bean转JsonStr.
   **/
  public static String bean2Json(Object obj) throws IOException {
    return mapper.writeValueAsString(obj);
  }

  /**
   * bean转bean.
   **/
  public static <T> T bean2Bean(Object obj, Class<T> objClass)
      throws JsonParseException, JsonMappingException, IOException {
    String s = mapper.writeValueAsString(obj);
    return mapper.readValue(s, objClass);
  }

  /**
   * JsonStr转Json.
   **/
  public static JsonNode str2Json(String jsonStr) throws IOException {
    return mapper.readTree(jsonStr);
  }

  /**
   * JsonStr转bean.
   **/
  public static <T> T json2Bean(String jsonStr, Class<T> objClass)
      throws JsonParseException, JsonMappingException, IOException {
    return mapper.readValue(jsonStr, objClass);
  }

  /**
   * strJson寻找.
   **/
  public static JsonNode jsonGetValue(String strJson, String node) throws IOException {
    JsonNode rootNode = mapper.readTree(strJson);
    return rootNode.path(node);
  }

}
