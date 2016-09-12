package com.qozix.spatulademo;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qozix.spatula.BindView;
import com.qozix.spatula.Spatula;

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

  @Test
  public void test_singleViewRootBoundById() throws Exception {
    SingleViewActivity activity = Robolectric.buildActivity(SingleViewActivity.class).create().get();
    assertTrue(activity.textView.getId() == R.id.single_view_activity_textview);
  }

  @Test
  public void test_viewGroupOnArbitraryObject() throws Exception {
    SimpleActivity activity = Robolectric.buildActivity(SimpleActivity.class).create().get();
    ObjectDecoratedWithViews decoratedObject = new ObjectDecoratedWithViews();
    View root = LayoutInflater.from(activity).inflate(R.layout.viewgroup_nested_views, null);
    Spatula.bind(decoratedObject, root);
    assertTrue(decoratedObject.relativeLayout.getId() == R.id.relativelayout);
    assertTrue(decoratedObject.linearLayout.getId() == R.id.linearlayout);
    assertTrue(decoratedObject.textView.getId() == R.id.textview_bottom_row);
    assertTrue(decoratedObject.button.getId() == R.id.button);
  }

  private static class ObjectDecoratedWithViews {
    @BindView(R.id.relativelayout)
    public RelativeLayout relativeLayout;
    @BindView(R.id.linearlayout)
    public LinearLayout linearLayout;
    @BindView(R.id.textview_bottom_row)
    public TextView textView;
    @BindView(R.id.button)
    public Button button;
  }

}
