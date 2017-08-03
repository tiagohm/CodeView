package br.tiagohm.codeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeView extends WebView {

    public interface OnHighlightListener {
        void onStartCodeHighlight();

        void onFinishCodeHighlight();

        void onLanguageDetected(Language language, int relevance);

        void onFontSizeChanged(int sizeInPx);

        void onLineClicked(int lineNumber, String content);
    }

    private String code = "";
    private String escapeCode;
    private Theme theme;
    private Language language;
    private float fontSize = 16;
    private boolean wrapLine = false;
    private OnHighlightListener onHighlightListener;
    private ScaleGestureDetector pinchDetector;
    private boolean zoomEnabled = false;
    private boolean showLineNumber = false;
    private int startLineNumber = 1;
    private int lineCount = 0;
    private int highlightLineNumber = -1;

    public CodeView(Context context) {
        this(context, null);
    }

    public CodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //Inicialização.
        init(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isZoomEnabled()) {
            pinchDetector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CodeView, 0, 0);
        //Define os atributos
        setWrapLine(attributes.getBoolean(R.styleable.CodeView_cv_wrap_line, false));
        setFontSize(attributes.getInt(R.styleable.CodeView_cv_font_size, 14));
        setZoomEnabled(attributes.getBoolean(R.styleable.CodeView_cv_zoom_enable, false));
        setShowLineNumber(attributes.getBoolean(R.styleable.CodeView_cv_show_line_number, false));
        setStartLineNumber(attributes.getInt(R.styleable.CodeView_cv_start_line_number, 1));
        highlightLineNumber = attributes.getInt(R.styleable.CodeView_cv_highlight_line_number, -1);
        attributes.recycle();

        pinchDetector = new ScaleGestureDetector(context, new PinchListener());

        setWebChromeClient(new WebChromeClient());
        getSettings().setJavaScriptEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setLoadWithOverviewMode(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    /**
     * Define um listener.
     */
    public CodeView setOnHighlightListener(OnHighlightListener listener) {
        //Definir um listener.
        if (listener != null) {
            //Definir um novo listener
            if (onHighlightListener != listener) {
                onHighlightListener = listener;
                //Adiciona o objeto que atenderá os eventos js e disparará o listener definido.
                addJavascriptInterface(new Object() {
                    @JavascriptInterface
                    public void onStartCodeHighlight() {
                        if (onHighlightListener != null) {
                            onHighlightListener.onStartCodeHighlight();
                        }
                    }

                    @JavascriptInterface
                    public void onFinishCodeHighlight() {
                        if (onHighlightListener != null) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    fillLineNumbers();
                                    showHideLineNumber(isShowLineNumber());
                                    highlightLineNumber(getHighlightLineNumber());
                                }
                            });
                            onHighlightListener.onFinishCodeHighlight();
                        }
                    }

                    @JavascriptInterface
                    public void onLanguageDetected(String name, int relevance) {
                        if (onHighlightListener != null) {
                            onHighlightListener.onLanguageDetected(Language.getLanguageByName(name), relevance);
                        }
                    }

                    @JavascriptInterface
                    public void onLineClicked(int lineNumber, String content) {
                        if (onHighlightListener != null) {
                            onHighlightListener.onLineClicked(lineNumber, content);
                        }
                    }
                }, "android");
            }
        }
        //Remover o listener.
        else {
            removeJavascriptInterface("android");
        }
        return this;
    }

    /**
     * Obtém o tamanho da fonte do texto em pixels.
     */
    public float getFontSize() {
        return fontSize;
    }

    /**
     * Define o tamanho da fonte do texto em pixels.
     */
    public CodeView setFontSize(float fontSize) {
        if (fontSize < 8) fontSize = 8;
        this.fontSize = fontSize;
        if (onHighlightListener != null) {
            onHighlightListener.onFontSizeChanged((int) fontSize);
        }
        return this;
    }

    /**
     * Obtém o código exibido.
     */
    public String getCode() {
        return code;
    }

    /**
     * Define o código que será exibido.
     */
    public CodeView setCode(String code) {
        if (code == null) code = "";
        this.code = code;
        this.escapeCode = Html.escapeHtml(code);
        return this;
    }

    /**
     * Obtém o tema.
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * Define o tema.
     */
    public CodeView setTheme(Theme theme) {
        this.theme = theme;
        return this;
    }

    /**
     * Obtém a linguagem.
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Define a linguagem.
     */
    public CodeView setLanguage(Language language) {
        this.language = language;
        return this;
    }

    /**
     * Verifica se está aplicando a quebra de linha.
     */
    public boolean isWrapLine() {
        return wrapLine;
    }

    /**
     * Define se aplicará a quebra de linha.
     */
    public CodeView setWrapLine(boolean wrapLine) {
        this.wrapLine = wrapLine;
        return this;
    }

    /**
     * Verifica se o zoom está habilitado.
     */
    public boolean isZoomEnabled() {
        return zoomEnabled;
    }

    /**
     * Define que o zoom estará habilitado ou não.
     */
    public CodeView setZoomEnabled(boolean zoomEnabled) {
        this.zoomEnabled = zoomEnabled;
        return this;
    }

    /**
     * Verifica se o número da linha está sendo exibido.
     */
    public boolean isShowLineNumber() {
        return showLineNumber;
    }

    /**
     * Define a visibilidade do número da linha.
     */
    public CodeView setShowLineNumber(boolean showLineNumber) {
        this.showLineNumber = showLineNumber;
        return this;
    }

    /**
     * Obtém o número da primeira linha.
     */
    public int getStartLineNumber() {
        return startLineNumber;
    }

    /**
     * Define o número da primeira linha.
     */
    public CodeView setStartLineNumber(int startLineNumber) {
        if (startLineNumber < 0) startLineNumber = 1;
        this.startLineNumber = startLineNumber;
        return this;
    }

    /**
     * Obtém a quantidade de linhas no código.
     */
    public int getLineCount() {
        return lineCount;
    }

    /**
     * Exibe ou oculta o número da linha.
     */
    public void toggleLineNumber() {
        showLineNumber = !showLineNumber;
        showHideLineNumber(showLineNumber);
    }

    /**
     * Aplica os atributos e exibe o código.
     */
    public void apply() {
        loadDataWithBaseURL("",
                toHtml(),
                "text/html",
                "UTF-8",
                "");
    }

    private String toHtml() {
        StringBuilder sb = new StringBuilder();
        //html
        sb.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n");
        //style
        sb.append("<link rel='stylesheet' href='").append(getTheme().getPath()).append("' />\n");
        sb.append("<style>\n");
        //body
        sb.append("body {");
        sb.append("font-size:").append(String.format("%dpx;", (int) getFontSize()));
        sb.append("margin: 0px; line-height: 1.2;");
        sb.append("}\n");
        //.hljs
        sb.append(".hljs {");
        sb.append("}\n");
        //pre
        sb.append("pre {");
        sb.append("margin: 0px; position: relative;");
        sb.append("}\n");
        //line
        if (isWrapLine()) {
            sb.append("td.line {");
            sb.append("word-wrap: break-word; white-space: pre-wrap; word-break: break-all;");
            sb.append("}\n");
        }
        //Outros
        sb.append("table, td, tr {");
        sb.append("margin: 0px; padding: 0px;");
        sb.append("}\n");
        sb.append("code > span { display: none; }");
        sb.append("td.ln { text-align: right; padding-right: 2px; }");
        sb.append("td.line:hover span {background: #661d76; color: #fff;}");
        sb.append("td.line:hover {background: #661d76; color: #fff; border-radius: 2px;}");
        sb.append("td.destacado {background: #ffda11; color: #000; border-radius: 2px;}");
        sb.append("td.destacado span {background: #ffda11; color: #000;}");
        sb.append("</style>");
        //scripts
        sb.append("<script src='file:///android_asset/highlightjs/highlight.js'></script>");
        sb.append("<script>hljs.initHighlightingOnLoad();</script>");
        sb.append("</head>");
        //code
        sb.append("<body>");
        sb.append("<pre><code class='").append(language.getLanguageName()).append("'>")
                .append(insertLineNumber(escapeCode))
                .append("</code></pre>\n");
        return sb.toString();
    }

    private void executeJavaScript(String js) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            evaluateJavascript("javascript:" + js, null);
        } else {
            loadUrl("javascript:" + js);
        }
    }

    private void changeFontSize(int sizeInPx) {
        executeJavaScript("document.body.style.fontSize = '" + sizeInPx + "px'");
    }

    private void fillLineNumbers() {
        executeJavaScript("var i; var x = document.querySelectorAll('td.ln'); for(i = 0; i < x.length; i++) {x[i].innerHTML = x[i].getAttribute('line');}");
    }

    private void showHideLineNumber(boolean show) {
        executeJavaScript(String.format(Locale.ENGLISH,
                "var i; var x = document.querySelectorAll('td.ln'); for(i = 0; i < x.length; i++) {x[i].style.display = %s;}",
                show ? "''" : "'none'"));
    }

    public int getHighlightLineNumber() {
        return highlightLineNumber;
    }

    public void highlightLineNumber(int lineNumber) {
        this.highlightLineNumber = lineNumber;
        executeJavaScript(String.format(Locale.ENGLISH,
                "var x = document.querySelectorAll('.destacado'); if(x && x.length == 1) x[0].classList.remove('destacado');"));
        if (lineNumber >= 0) {
            executeJavaScript(String.format(Locale.ENGLISH,
                    "var x = document.querySelectorAll(\"td.line[line='%d']\"); if(x && x.length == 1) x[0].classList.add('destacado');", lineNumber));
        }
    }

    private String insertLineNumber(String code) {
        Matcher m = Pattern.compile("(.*?)&#10;").matcher(code);
        StringBuffer sb = new StringBuffer();
        //Posição atual da linha.
        int pos = getStartLineNumber();
        //Quantidade de linhas.
        lineCount = 0;
        //Para cada linha encontrada, encapsulá-la dentro uma linha de uma tabela.
        while (m.find()) {
            m.appendReplacement(sb,
                    String.format(Locale.ENGLISH,
                            "<tr><td line='%d' class='hljs-number ln'></td><td line='%d' onclick='android.onLineClicked(%d, this.textContent);' class='line'>$1 </td></tr>&#10;",
                            pos, pos, pos));
            pos++;
            lineCount++;
        }

        return "<table>\n" + sb.toString().trim() + "</table>\n";
    }

    /**
     * Eventos de pinça.
     */
    private class PinchListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        private float fontSize;
        private int oldFontSize;

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            fontSize = getFontSize();
            oldFontSize = (int) fontSize;
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            CodeView.this.fontSize = fontSize;
            super.onScaleEnd(detector);
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            fontSize = getFontSize() * detector.getScaleFactor();
            if (fontSize >= 8) {
                changeFontSize((int) fontSize);
                if (onHighlightListener != null && oldFontSize != (int) fontSize) {
                    onHighlightListener.onFontSizeChanged((int) fontSize);
                }
                oldFontSize = (int) fontSize;
            } else {
                fontSize = 8;
            }
            return false;
        }
    }
}
