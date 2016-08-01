package com.qozix.spatula;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by michaeldunn on 7/13/16.
 *
 * This is a simple version of view binding via annotation since ButterKnife started taking itself too seriously.
 */
public class Spatula {

  /**
   * Instances of this interface can be passed to the #bind method to determine how an ID is translated into a
   * View instance of the appropriate type.
   */
  public interface Finder {
    <T extends View> T findAndCast(int resourceId);
  }

  /**
   * Convenience class that uses Activity.findViewById and casts the return.
   */
  private static class FindInActivity implements Finder {
    private Activity mActivity;
    public FindInActivity(Activity activity){
      mActivity = activity;
    }
    /**
     * Helper method to pre-cast Views to the type of the declared instance.
     *
     * @param <T> The Class reference (View subclass) the View should be cast to.
     * @param id  The id of the View to be located within this Activity's view tree.
     * @return The View with the matching id.
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T findAndCast(int id) {
      return (T) mActivity.findViewById(id);
    }
  }

  /**
   * Convenience class that uses ViewGroup.findViewById and casts the returns.
   */
  private static class FindInView implements Finder {
    private ViewGroup mViewGroup;
    public FindInView(ViewGroup viewGroup){
      mViewGroup = viewGroup;
    }
    /**
     * Helper method to pre-cast Views to the type of the declared instance.
     *
     * @param <T> The Class reference (View subclass) the View should be cast to.
     * @param id  The id of the View to be located within this Activity's view tree.
     * @return The View with the matching id.
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T findAndCast(int id) {
      return (T) mViewGroup.findViewById(id);
    }
  }

  /**
   * The single-in method of binding.  Currently, it loops through all members of #instance and checks for annotations
   * attached to that member - it'd be more efficient to instead loop through annotations and find the associate member,
   * but that would require compile-time processing.
   *
   * Inaccessible (private, package-private, protected) members are temporarily set accessible in order to set the
   * value, otherwise only public members could be updated.
   *
   * @param instance Any object instance whose annotated members will be populated.
   * @param finder The Finder implementation used to convert a annoation's value (integer ID) into a View instance.
   */
  public static void bind(final Object instance, Finder finder){
    long startTime = System.currentTimeMillis();
    for (Field field : instance.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(BindView.class)) {
        Annotation annotation = field.getAnnotation(BindView.class);
        BindView bindView = (BindView) annotation;
        int resourceId = bindView.value();
        if (resourceId > 0) {
          try {
            boolean isAccessible = field.isAccessible();
            if (!isAccessible) {
              field.setAccessible(true);
            }
            field.set(instance, finder.findAndCast(resourceId));
            if (!isAccessible) {
              field.setAccessible(false);
            }
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        }
      }
      if (field.isAnnotationPresent(OnClick.class) && field.getType().isAssignableFrom(View.OnClickListener.class)) {
        Annotation annotation = field.getAnnotation(OnClick.class);
        OnClick onClick = (OnClick) annotation;
        int resourceId = onClick.value();
        if (resourceId > 0) {
          try {
            boolean isAccessible = field.isAccessible();
            if (!isAccessible) {
              field.setAccessible(true);
            }
            View view = finder.findAndCast(resourceId);
            view.setOnClickListener((View.OnClickListener) field.get(instance));
            if (!isAccessible) {
              field.setAccessible(false);
            }
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        }
      }
    }
    for (final Method method : instance.getClass().getDeclaredMethods()) {
      Log.d("S", method.getName() + " has " + method.getDeclaredAnnotations().length + " annotations");
      if (method.isAnnotationPresent(OnClick.class)) {
        Annotation annotation = method.getAnnotation(OnClick.class);
        OnClick onClick = (OnClick) annotation;
        int resourceId = onClick.value();
        if (resourceId > 0) {
          View view = finder.findAndCast(resourceId);
          view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
              try {
                boolean isAccessible = method.isAccessible();
                if (!isAccessible) {
                  method.setAccessible(true);
                }
                method.invoke(instance, view);
                if (!isAccessible) {
                  method.setAccessible(false);
                }
              } catch (IllegalAccessException e) {
                e.printStackTrace();
              } catch (InvocationTargetException e) {
                e.printStackTrace();
              }
            }
          });
        }
      }
    }
    Log.d(Spatula.class.getSimpleName(), "Spatula.bind took " + (System.currentTimeMillis() - startTime) + "ms");
  }

  /**
   * Convenience method that calls #bind with a Finder instance for use on a ViewGroup but decorates an arbitrary
   * Object instance.
   *
   * @inheritDoc
   */
  public static void bind(Object instance, ViewGroup viewGroup){
    bind(instance, new FindInView(viewGroup));
  }

  /**
   * Convenience method that calls #bind with a Finder instance for use on an Activity.
   *
   * @inheritDoc
   */
  public static void bind(final Activity activity){
    bind(activity, new FindInActivity(activity));
  }

  /**
   * Convenience method that calls #bind with a Finder instance for use on an ViewGroup.
   *
   * @inheritDoc
   */
  public static void bind(ViewGroup viewGroup){
    bind(viewGroup, viewGroup);
  }

}
