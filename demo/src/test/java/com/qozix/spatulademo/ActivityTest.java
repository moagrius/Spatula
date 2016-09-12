package com.qozix.spatulademo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ActivityTest {

  @Test
  public void test_elementsBoundById() throws Exception {
    SimpleActivity activity = Robolectric.buildActivity(SimpleActivity.class).create().get();
    assertTrue(activity.linearLayout.getId() == R.id.simple_activity_linear_layout);
    assertTrue(activity.button.getId() == R.id.simple_activity_button);
    assertTrue(activity.textView.getId() == R.id.simple_activity_textview);
    assertTrue(activity.textView2.getId() == R.id.simple_activity_textview_2);
    assertTrue(activity.button2.getId() == R.id.simple_activity_button_nested);
  }

}
