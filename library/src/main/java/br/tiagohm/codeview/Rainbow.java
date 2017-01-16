package br.tiagohm.codeview;

public class Rainbow extends SyntaxHighlighter
{
    private static final String HTML_SCRIPT =
            "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<link href=\"%s\" rel=\"stylesheet\"/>\n" +
                    "<style>body {%s}</style>\n" +
                    "<style>code {%s}</style>\n" +
                    "<style>pre {%s}</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<pre><code class=\"%s\">%s</code></pre>" +
                    "\t<script src=\"%s\"></script>\n" +
                    "%s" +
                    "</body>\n" +
                    "</html>";
    private static final String JS_PATH = "file:///android_asset/rainbow/rainbow.js";
    private static final String LN_JS_PATH = "file:///android_asset/rainbow/rainbow-line-numbers.js";
    private static final String LN_CSS_PATH = "file:///android_asset/rainbow/styles/line-numbers.css";

    private static String OTHERS_SCRIPTS = "";
    private static String BODY_CSS = "";
    private static String PRE_CSS = "";
    private static String CODE_CSS = "";
    private static String PRE_CLASS = "";
    private static String CODE_CLASS = "";
    private static String CODE_TEXT = "";

    public Rainbow()
    {
        setTheme(Themes.DEFAULT);
    }

    @Override
    public Language[] getSupportedLanguages()
    {
        return Languages.values();
    }

    @Override
    public Theme[] getSupportedThemes()
    {
        return Themes.values();
    }

    @Override
    public String getName()
    {
        return "Rainbow";
    }

    @Override
    public String getHtmlCode(String code, Language lang, int textSize)
    {
        BODY_CSS = "margin: 0px !important; display: inline-block;";
        CODE_CSS = "font-size: " + textSize + "px !important; line-height: 1.2 !important;";
        PRE_CSS = " margin-bottom: -5px !important; font-size: " + textSize + "px !important; line-height: 1.2 !important;";
        CODE_CLASS = "language-" + lang.getLanguageName();
        CODE_TEXT = code;

        if(isShowLineNumber())
        {
            OTHERS_SCRIPTS = "<script src=\"" + LN_JS_PATH + "\"></script>";
            OTHERS_SCRIPTS += "<link href=\"" + LN_CSS_PATH + "\" rel=\"stylesheet\" />\n";
        }
        else
        {
            OTHERS_SCRIPTS = "";
        }

        return String.format(HTML_SCRIPT,
                getTheme().getPath(),
                BODY_CSS,
                CODE_CSS,
                PRE_CSS,
                CODE_CLASS, CODE_TEXT,
                JS_PATH,
                OTHERS_SCRIPTS);
    }

    public enum Themes implements Theme
    {
        DEFAULT("rainbow"),
        ALL_HALLOWS_EVE("all-hallows-eve"),
        BLACKBOARD("blackboard"),
        DREAM_WEAVER("dreamweaver"),
        ESPRESSO_LIBRE("espresso-libre"),
        GITHUB("github"),
        KIMBIE_DARK("kimbie-dark"),
        KIMBIE_LIGHT("kimbie-light"),
        MONOKAI("monokai"),
        OBSIDIAN("obsidian"),
        PARAISO_DARK("paraiso-dark"),
        PARAISO_LIGHT("paraiso-light"),
        PASTIE("pastie"),
        RAINBOW("rainbow"),
        SOLARIZED_DARK("solarized-dark"),
        SOLARIZED_LIGHT("solarized-light"),
        SUNBURST("sunburst"),
        TOMORROW_NIGHT("tomorrow-night"),
        TRICOLORE("tricolore"),
        TWILIGHT("twilight"),
        ZENBURNESQUE("zenburnesque");

        private final String name;

        Themes(String name)
        {
            this.name = name;
        }

        @Override
        public String getPath()
        {
            return "file:///android_asset/rainbow/styles/" + name + ".css";
        }
    }

    public enum Languages implements Language
    {
        CSS("css"),
        JAVASCRIPT("javascript"),
        C("c"),
        C_SHARP("csharp"),
        COFFEESCRIPT("coffeescript"),
        D("d"),
        GO("go"),
        HASKELL("haskell"),
        JAVA("java"),
        JSON("json"),
        LUA("lua"),
        PHP("php"),
        PYTHON("python"),
        R("r"),
        RUBY("ruby"),
        SCHEME("scheme"),
        SHELL("shell"),
        SMALLTALK("smalltalk"),
        SQL("sql"),
        GENERIC("generic");

        private final String name;

        Languages(String name)
        {
            this.name = name;
        }

        @Override
        public String getLanguageName()
        {
            return name;
        }
    }
}
