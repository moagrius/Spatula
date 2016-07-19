# Spatula
Spatula is a very simple View and Click binder using Java annotations.

> ButterKnives are infinitely sharper than Spatulas...

# Why?
We all love Square's stuff, but ButterKnife started taking itself too seriously when it demanded 4 lines and a plugin in my module's gradle.

# How?
Super simple...  There are 2 binding annoations, both take a resource ID:
```
@BindView(R.id.whatever)
@OnClick(R.id.whater)
```
The first works on any `View` reference, the second works on a `View.OnClickListener` or a method that takes a single `View` parameter.

To process, use any of the static `bind` methods.
```
Spatula.bind(someActivityOrViewGroup);
```
It might look like this in an application:
```
public class MyActivity extends Activity {
  
  // bind a view
  @BindView(R.id.button_go)
  private Button mButtonGo;
  
  public void onCreate(Bundle b){
    super.onCreate(b);
    Spatula.bind(this);
    mButtonGo.setText("Works!");
  }
  
  // bind a listener
  @OnClick(R.id.button_listener)
  private View.OnClickListener mOnClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Log.d(getClass().getSimpleName(), "mOnClickListener");
    }
  }
  
  // bind a method
  @OnClick(R.id.button_method)
  private void onClickMethod(View view) {
    Log.d(getClass().getSimpleName(), "onClickMethod");
  }
  
}
```

Of course, you need to add it to your gradle:
```
compile 'com.qozix:spatula:1.0'
```

# But...
- Yep, this this uses _runtime_ annotation processing, which is going to be technically much slower than compile time using the APT plugin.  That said, you're still probably looking at an average of 0 to 1 milliseconds for a medium-sized instance with several bindings.
- Yep, all the restrictions of ButterKnife apply (and more).
- We get around restricted access members by temporarily setting them accessible, updating, then setting them back (yep, ick!)

# So...?
This is intended to be super simple, super lightweight, super easy to use or modify, and super easy to read.  It's not trying to solve the most important problems, it's not the most performant version of this functionality.  

**It's simple.**
