package br.tiagohm.codeview;

public abstract class SyntaxHighlighter
{
    private Theme mTheme;
    private boolean mShowLineNumber = false;

    public abstract String getHtmlCode(String code, Language lang, int textSize);

    public abstract Theme[] getSupportedThemes();

    public abstract Language[] getSupportedLanguages();

    public abstract String getName();

    public Theme getTheme()
    {
        return mTheme;
    }

    public void setTheme(Theme theme)
    {
        mTheme = theme;
    }

    public boolean isShowLineNumber()
    {
        return mShowLineNumber;
    }

    public void setShowLineNumber(boolean value)
    {
        mShowLineNumber = value;
    }
}
