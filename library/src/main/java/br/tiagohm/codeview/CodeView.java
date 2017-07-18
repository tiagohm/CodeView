package br.tiagohm.codeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class CodeView extends WebView {

    public interface OnHighlightListener {
        void onStartCodeHighlight();

        void onFinishCodeHighlight();

        void onLanguageDetected(Language language, int relevance);
    }

    private String code = "";
    private String escapeCode;
    private Theme theme;
    private Language language;
    private int fontSize = 16;
    private boolean wrapLine = false;
    private OnHighlightListener onHighlightListener;

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

    private void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CodeView, 0, 0);
        //Define os atributos
        setWrapLine(attributes.getBoolean(R.styleable.CodeView_cv_wrap_line, false));
        setFontSize(attributes.getInt(R.styleable.CodeView_cv_font_size, 14));
        attributes.recycle();

        getSettings().setJavaScriptEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setLoadWithOverviewMode(true);
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
                            onHighlightListener.onFinishCodeHighlight();
                        }
                    }

                    @JavascriptInterface
                    public void onLanguageDetected(String name, int relevance) {
                        if (onHighlightListener != null) {
                            onHighlightListener.onLanguageDetected(Language.getLanguageByName(name), relevance);
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

    public int getFontSize() {
        return fontSize;
    }

    public CodeView setFontSize(int fontSize) {
        this.fontSize = fontSize;
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
        sb.append("font-size:").append(String.format("%dpx;", getFontSize()));
        sb.append("margin: 0px; line-height: 1.2;");
        sb.append("}\n");
        //.hljs
        sb.append(".hljs {");
        sb.append("}");
        sb.append("pre {");
        sb.append("margin: 0px;");
        if (isWrapLine()) {
            sb.append("word-wrap: break-word; white-space: pre-wrap; word-break: break-all;");
        }
        sb.append("}");
        sb.append("</style>");
        //scripts
        sb.append("<script src='file:///android_asset/highlightjs/highlight.js'></script>");
        sb.append("<script>hljs.initHighlightingOnLoad();</script>");
        sb.append("</head>");
        //code
        sb.append("<body>");
        sb.append("<pre><code class='").append(language.getLanguageName()).append("'>")
                .append(escapeCode)
                .append("</code></pre>\n");
        return sb.toString();
    }
}
