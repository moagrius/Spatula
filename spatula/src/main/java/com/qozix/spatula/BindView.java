package com.qozix.spatula;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by michaeldunn on 7/14/16.
 *
 * Annotation interface.  By using `value`, we send and receive a single, unnamed argument.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindView {
  /**
   * The single value passed to the annotation, which should be an integer id of a View.
   */
  int value();
}
