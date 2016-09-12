package com.qozix.spatulademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.qozix.spatula.BindView;
import com.qozix.spatula.Spatula;

/**
 * Created by michaeldunn on 9/12/16.
 */
public class SingleViewActivity extends AppCompatActivity {

  @BindView(R.id.single_view_activity_textview)
  public TextView textView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_single_view);
    Spatula.bind(this);
  }

}
