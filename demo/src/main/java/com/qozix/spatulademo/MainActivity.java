package com.qozix.spatulademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.qozix.spatula.BindView;
import com.qozix.spatula.OnClick;
import com.qozix.spatula.Spatula;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.textview_hello_world)
  private TextView mTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Spatula.bind(this);
    Log.d(MainActivity.class.getSimpleName(), "mTextView is not null? " + (mTextView != null));
  }

  @OnClick(R.id.button_click_listener)
  private View.OnClickListener mOnClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      Log.d(MainActivity.class.getSimpleName(), "mOnClickListener");
    }
  };

  @OnClick(R.id.button_click_method)
  private void onClickMethod(View view){
    Log.d(MainActivity.class.getSimpleName(), "onClickMethod");
  }

}
