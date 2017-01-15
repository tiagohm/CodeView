package br.tiagohm.codeview;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class CodeView extends WebView
{
    private SyntaxHighlighter mSyntaxHighlighter = new Prism();
    private String mCode = "";
    private String mEscapedCode = "";
    private Language mLanguage;

    public CodeView(Context context)
    {
        this(context, null);
    }

    public CodeView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CodeView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        getSettings().setJavaScriptEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setLoadWithOverviewMode(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else
        {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public CodeView setSyntaxHighlighter(SyntaxHighlighter sh)
    {
        mSyntaxHighlighter = sh;
        return this;
    }

    public SyntaxHighlighter getSyntaxHighlighter()
    {
        return mSyntaxHighlighter;
    }

    public CodeView setCode(String code)
    {
        if(code == null) code = "";
        mCode = code;
        mEscapedCode = Html.escapeHtml(code);
        return this;
    }

    public String getCode()
    {
        return mCode;
    }

    public Language getLanguage()
    {
        return mLanguage;
    }

    public CodeView setLanguage(Language language)
    {
        mLanguage = language;
        return this;
    }

    public CodeView setTheme(Theme theme)
    {
        if(mSyntaxHighlighter != null && theme != null)
        {
            mSyntaxHighlighter.setTheme(theme);
        }

        return this;
    }

    public void apply()
    {
        if(mSyntaxHighlighter != null)
        {
            loadDataWithBaseURL("",
                    mSyntaxHighlighter.getHtmlCode(mEscapedCode, getLanguage()),
                    "text/html",
                    "UTF-8",
                    "");
        }
    }
}
