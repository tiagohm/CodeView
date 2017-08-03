# CodeView
### Android Code Highlighter

[![](https://jitpack.io/v/tiagohm/CodeView.svg)](https://jitpack.io/#tiagohm/CodeView)

## Install
Add it in your root `build.gradle` at the end of repositories:
```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
Add the dependency:
```gradle
compile 'com.github.tiagohm:CodeView:LATEST-VERSION
```

## Features
* Powered by Highlight.js
* 176 languages and 79 styles
* Wrap Line
* Language Detection
* Zoom (Pinch gesture)
* Line Number
* Line Count
* Highlight current line (by click/tap)
* Highlight line
* Tap event of lines (get line number and your content)

## Usage

Add view to your layout:
```xml
<br.tiagohm.codeview.CodeView
        android:id="@+id/codeView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:cv_font_size="14"
        app:cv_highlight_line_number="36"
        app:cv_show_line_number="true"
        app:cv_start_line_number="0"
        app:cv_wrap_line="true"
        app:cv_zoom_enable="true">
    </br.tiagohm.codeview.CodeView>
 ```
 ```java
 mCodeView = (CodeView)findViewById(R.id.codeView);

 mCodeView.setOnHighlightListener(this)
       .setOnHighlightListener(this)
       .setTheme(Theme.AGATE)
       .setCode(JAVA_CODE)
       .setLanguage(Language.JAVA)
       .setWrapLine(true)
       .setFontSize(14)
       .setZoomEnabled(true)
       .setShowLineNumber(true)
       .setStartLineNumber(9000)
       .apply();
 ```

## Other Methods
```java
mCodeView.highlightLineNumber(10);
mCodeView.toggleLineNumber();
mCodeView.getLineCount();
```

 Listeners:

 ```java
 //Interface
 new CodeView.OnHighlightListener()
 {
  @Override
  public void onStartCodeHighlight()
  {   
    mProgressDialog = ProgressDialog.show(this, null, "Carregando...", true);
  }

  @Override
  public void onFinishCodeHighlight()
  {
    if (mProgressDialog != null) {
      mProgressDialog.dismiss();
    }
  }

  @Override
  public void onLanguageDetected(Language language, int relevance) {
   Toast.makeText(this, "language: " + language + " relevance: " + relevance, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onFontSizeChanged(int sizeInPx) {
   Log.d("TAG", "font-size: " + sizeInPx + "px");
  }

  @Override
  public void onLineClicked(int lineNumber, String content) {
    Toast.makeText(this, "line: " + lineNumber + " html: " + content, Toast.LENGTH_SHORT).show();
  }
}
 ```

 ![](https://raw.githubusercontent.com/tiagohm/CodeView/master/1.png)
