package br.tiagohm.codeview;

public final class Prism extends SyntaxHighlighter
{
    private static final String HTML_SCRIPT =
            "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "\t<link href=\"%s\" rel=\"stylesheet\" />\n" +
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
    private static final String JS_PATH = "file:///android_asset/prism/prism.js";

    private static String OTHERS_SCRIPTS = "";
    private static String BODY_CSS = "";
    private static String PRE_CSS = "";
    private static String CODE_CSS = "";
    private static String PRE_CLASS = "";
    private static String CODE_CLASS = "";
    private static String CODE_TEXT = "";

    public Prism()
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
        return "Prism";
    }

    @Override
    public final String getHtmlCode(String code, Language lang, int textSize)
    {
        BODY_CSS = "margin: 0px !important;";
        CODE_CSS = "font-size: " + textSize + "px !important; line-height: 1.2 !important;";
        PRE_CSS = "margin: 0px !important; font-size: " + textSize + "px !important; line-height: 1.2 !important;";
        CODE_CLASS = (isShowLineNumber() ? "line-numbers" : "") + " language-" + lang.getLanguageName();
        CODE_TEXT = code;

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
        DEFAULT("default"),
        COY("coy"),
        DARK("dark"),
        FUNKY("funky"),
        OKAIDIA("okaidia"),
        SOLARIZED_LIGHT("solarized_light"),
        TWILIGHT("twilight");

        private final String name;

        Themes(String name)
        {
            this.name = name;
        }

        @Override
        public String getPath()
        {
            return "file:///android_asset/prism/styles/" + name + ".css";
        }
    }

    public enum Languages implements Language
    {
        MARKUP("markup"),
        CSS("css"),
        C_LIKE("clike"),
        JAVASCRIPT("javascript"),
        ABAP("abap"),
        ACTIONSCRIPT("actionscript"),
        ADA("ada"),
        APACHE("apacheconf"),
        APL("apl"),
        APPLESCRIPT("applescript"),
        ASCIIDOC("asciidoc"),
        ASP_NET_C_SHARP("aspnet"),
        AUTOIT("autoit"),
        AUTOHOTKEY("autohotkey"),
        BASH("bash"),
        BASIC("basic"),
        BATCH("batch"),
        BISON("bison"),
        BRAINFUCK("brainfuck"),
        BRO("bro"),
        C("c"),
        C_SHARP("csharp"),
        CPP("cpp"),
        COFFEESCRIPT("coffeescript"),
        CRYSTAL("crystal"),
        CSS_EXTRAS("css-extras"),
        D("d"),
        DART("dart"),
        DIFF("diff"),
        DOCKER("docker"),
        EIFFEL("eiffel"),
        ELIXIR("elixir"),
        ERLANG("erlang"),
        F_SHARP("fsharp"),
        FORTRAN("fortran"),
        GHERKIN("gherkin"),
        GIT("git"),
        GLSL("glsl"),
        GO("go"),
        GRAPHQL("graphql"),
        GROOVY("groovy"),
        HAML("haml"),
        HANDLEBARS("handlebars"),
        HASKELL("haskell"),
        HAXE("haxe"),
        HTTP("http"),
        ICON("icon"),
        INFORM_7("inform7"),
        INI("ini"),
        J("j"),
        JADE("jade"),
        JAVA("java"),
        JOLIE("jolie"),
        JSON("json"),
        JULIA("julia"),
        KEYMAN("keyman"),
        KOTLIN("kotlin"),
        LATEX("latex"),
        LESS("less"),
        LIVESCRIPT("livescript"),
        LOLCODE("lolcode"),
        LUA("lua"),
        MAKEFILE("makefile"),
        MARKDOWN("markdown"),
        MATLAB("matlab"),
        MEL("mel"),
        MIZAR("mizar"),
        MONKEY("monkey"),
        NASM("nasm"),
        NGINX("nginx"),
        NIM("nim"),
        NIX("nix"),
        NSIS("nsis"),
        OBJECTIVE_C("objectivec"),
        OCAML("ocaml"),
        OZ("oz"),
        PARI_GP("parigp"),
        PARSER("parser"),
        PASCAL("pascal"),
        PERL("perl"),
        PHP("php"),
        PHP_EXTRAS("php-extras"),
        POWERSHELL("powershell"),
        PROCESSING("processing"),
        PROLOG("prolog"),
        PROPERTIES("properties"),
        PROTOCOL_BUFFERS("protobuf"),
        PUPPET("puppet"),
        PURE("pure"),
        PYTHON("python"),
        Q("q"),
        QORE("qore"),
        R("r"),
        REACT_JSX("jsx"),
        REASON("reason"),
        REST("rest"),
        RIP("rip"),
        ROBOCONF("roboconf"),
        RUBY("ruby"),
        RUST("rust"),
        SAS("sas"),
        SASS("sass"),
        SCSS("scss"),
        SCALA("scala"),
        SCHEME("scheme"),
        SMALLTALK("smalltalk"),
        SMARTY("smarty"),
        SQL("sql"),
        STYLUS("stylus"),
        SWIFT("swift"),
        TCL("tcl"),
        TEXTILE("textile"),
        TWIG("twig"),
        TYPESCRIPT("typescript"),
        VERILOG("verilog"),
        VHDL("vhdl"),
        VIM("vim"),
        WIKI_MARKUP("wiki"),
        XOJO("xojo"),
        YAML("yaml"),
        XML("xml"),
        HTML("html"),
        SVG("svg"),
        MATHML("mathml");

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
