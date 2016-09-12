package com.qozix.spatulademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qozix.spatula.BindView;
import com.qozix.spatula.Spatula;

/**
 * Created by michaeldunn on 9/12/16.
 */
public class SimpleActivity extends AppCompatActivity {

  @BindView(R.id.simple_activity_linear_layout)
  public LinearLayout linearLayout;
  @BindView(R.id.simple_activity_button)
  public Button button;
  @BindView(R.id.simple_activity_textview)
  public TextView textView;
  @BindView(R.id.simple_activity_textview_2)
  public TextView textView2;
  @BindView(R.id.simple_activity_button_nested)
  public Button button2;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple);
    Spatula.bind(this);
  }

}
