# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/michaeldunn/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepattributes *Annotation*
-keep public class com.qozix.spatula.*
-keepclassmembers class com.qozix.spatula.BindView {
  int value();
}
-keepclassmembers class com.qozix.spatula.OnClick {
  int value();
}
-keepclassmembers class com.qozix.spatula.Spatula {
  public static void bind(***);
  public static void bind(***, ***);
}