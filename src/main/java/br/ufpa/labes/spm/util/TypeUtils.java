package br.ufpa.labes.spm.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** TypeUtils */
public class TypeUtils {

  private static Logger log = LoggerFactory.getLogger(TypeUtils.class);

  public static Field lookupField(Object source, String fieldName) {
    log.debug("Class: {}", source.getClass().getSimpleName());
    try {
      return source.getClass().getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
      if (source.getClass().getSuperclass() == null) {
        return null;
      }
      return lookupField(source.getClass().getSuperclass(), fieldName);
    }
  }
}
