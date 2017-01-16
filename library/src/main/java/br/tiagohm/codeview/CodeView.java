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
    private SyntaxHighlighter mSyntaxHighlighter;
    private String mCode = "";
    private String mEscapedCode = "";
    private Language mLanguage;
    private int mTextSize = 14;

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

    public SyntaxHighlighter getSyntaxHighlighter()
    {
        return mSyntaxHighlighter;
    }

    public CodeView setSyntaxHighlighter(SyntaxHighlighter sh)
    {
        mSyntaxHighlighter = sh;
        return this;
    }

    public String getCode()
    {
        return mCode;
    }

    public CodeView setCode(String code)
    {
        if(code == null) code = "";
        mCode = code;
        mEscapedCode = Html.escapeHtml(code);
        return this;
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

    public int getTextSize()
    {
        return mTextSize;
    }

    public CodeView setTextSize(int size)
    {
        mTextSize = size;
        return this;
    }

    public CodeView setShowLineNumber(boolean value)
    {
        if(mSyntaxHighlighter != null)
        {
            mSyntaxHighlighter.setShowLineNumber(value);
        }

        return this;
    }

    public CodeView toggleShowLineNumber()
    {
        if(mSyntaxHighlighter != null)
        {
            mSyntaxHighlighter.setShowLineNumber(!mSyntaxHighlighter.isShowLineNumber());
        }

        return this;
    }

    public void apply()
    {
        loadDataWithBaseURL("",
                mSyntaxHighlighter != null ?
                        mSyntaxHighlighter.getHtmlCode(mEscapedCode, getLanguage(), getTextSize()) :
                        mEscapedCode,
                "text/html",
                "UTF-8",
                "");
    }
}
