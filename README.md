# CodeView
### Android Code Highlighter

## Download

[![](https://jitpack.io/v/tiagohm/CodeView.svg)](https://jitpack.io/#tiagohm/CodeView)

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
compile 'com.github.tiagohm:CodeView:0.1.4
```

## Highlighters
* [HIGHLIGHT.JS v9.9.0](https://highlightjs.org/)
169 languages and 77 styles
* ~~[PRISM](http://prismjs.com/)
120 languages~~ (Removed)
* ~~[RAINBOW](https://craig.is/making/rainbows)
19 languages~~ (Removed)

## Other Links
* ~~[https://github.com/Blender3D/rainbow.linenumbers.js](https://github.com/Blender3D/rainbow.linenumbers.js)~~
* [https://github.com/wcoder/highlightjs-line-numbers.js/](https://github.com/wcoder/highlightjs-line-numbers.js/)

## Usage

Add view to your layout:
```xml
<br.tiagohm.codeview.CodeView
  android:id="@+id/code_view"
  app:zoom_enabled="true"
  android:layout_width="match_parent"
  android:layout_height="match_parent"/>
 ```
 ```java
 final CodeView cv = (CodeView)findViewById(R.id.code_view);

 //Using Highlight.js
 cv.setSyntaxHighlighter(new HightlightJs())
        .setCode("Your code")
        //HightlightJs.Languages.AUTO is slow!!!
        .setLanguage(HightlightJs.Languages.AUTO)
        .setTheme(HightlightJs.Themes.DRACULA)
        .setShowLineNumber(true)
        .setTextSize(12)
        .apply();
 ```

 Events:

 ```java
 //Interface
 new CodeView.OnHighlightListener()
 {
       @Override
       @JavascriptInterface
       public void onStartCodeHighlight()
       {   
          mProgressDialog = ProgressDialog.show(this, null, "Carregando...", true);
       }

       @Override
       @JavascriptInterface
       public void onFinishCodeHighlight()
       {
          mProgressDialog.dismiss();
       }
}
 ```

 ![](https://raw.githubusercontent.com/tiagohm/CodeView/master/1.png)
