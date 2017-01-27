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
compile 'com.github.tiagohm:CodeView:0.1.2
```

## Highlighters
* [HIGHLIGHT.JS](https://highlightjs.org/)
169 languages and 77 styles
* [PRISM](http://prismjs.com/)
120 languages
* [RAINBOW](https://craig.is/making/rainbows)
19 languages

## Other Links
* [https://github.com/Blender3D/rainbow.linenumbers.js](https://github.com/Blender3D/rainbow.linenumbers.js)
* [https://github.com/wcoder/highlightjs-line-numbers.js/](https://github.com/wcoder/highlightjs-line-numbers.js/)

## Usage

Add view to your layout:
```xml
<br.tiagohm.codeview.CodeView
  android:id="@+id/code_view"
  android:layout_width="match_parent"
  android:layout_height="match_parent"/>
 ```
 ```java
 final CodeView cv = (CodeView)findViewById(R.id.code_view);

 //Using Highlight.js
 cv.setSyntaxHighlighter(new HightlightJs())
        .setCode("Your code")
        .setLanguage(HightlightJs.Languages.JAVA)
        .setTheme(HightlightJs.Themes.DRACULA)
        .setShowLineNumber(true)
        .setTextSize(12)
        .apply();
 //Or using Prism.js
 cv.setSyntaxHighlighter(new Prism())
        .setCode("Your code")
        .setLanguage(Prism.Languages.JAVA)
        .setTheme(Prism.Themes.SOLARIZED_LIGHT)
        .setShowLineNumber(true)
        .setTextSize(12)
        .apply();
//Or using Rainbow
cv.setSyntaxHighlighter(new Rainbow())
       .setCode("Your code")
       .setLanguage(Rainbow.Languages.JAVA)
       .setTheme(Rainbow.Themes.GITHUB)
       .setShowLineNumber(true)
       .setTextSize(12)
       .apply();
 ```

 Highlight.js
 ![](https://raw.githubusercontent.com/tiagohm/CodeView/master/1.png)
 Prism.js
 ![](https://raw.githubusercontent.com/tiagohm/CodeView/master/3.png)
 Rainbow
 ![](https://raw.githubusercontent.com/tiagohm/CodeView/master/2.png)
