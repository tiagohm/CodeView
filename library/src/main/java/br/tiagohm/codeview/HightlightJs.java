package br.tiagohm.codeview;

public class HightlightJs extends SyntaxHighlighter
{
    private static final String HTML_SCRIPT =
            "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<link rel=\"stylesheet\" href=\"%s\" />\n" +
                    "<style>body {%s}</style>\n" +
                    "<style>code {%s}</style>\n" +
                    "<style>pre {%s}</style>\n" +
                    "<script src=\"%s\"></script>\n" +
                    "<script>hljs.initHighlightingOnLoad();</script>" +
                    "%s" +
                    "</head>\n" +
                    "<body>\n" +
                    "<pre><code class=\"%s\">%s</code></pre>\n" +
                    "</body>\n" +
                    "</html>";
    private static final String LANG_PATH = "file:///android_asset/highlightjs/languages/%s.js";
    private static final String JS_PATH = "file:///android_asset/highlightjs/highlight.js";
    private static final String HLN_PATH = "file:///android_asset/highlightjs/highlightjs-line-number.js";
    private static String OTHERS_SCRIPTS = "";
    private static String BODY_CSS = "";
    private static String PRE_CSS = "";
    private static String CODE_CSS = "";
    private static String PRE_CLASS = "";
    private static String CODE_CLASS = "";
    private static String CODE_TEXT = "";

    public HightlightJs()
    {
        setTheme(Themes.DEFAULT);
    }

    @Override
    public Theme[] getSupportedThemes()
    {
        return Themes.values();
    }

    @Override
    public Language[] getSupportedLanguages()
    {
        return Languages.values();
    }

    @Override
    public String getName()
    {
        return "HightlightJs";
    }

    @Override
    public String getHtmlCode(String code, Language lang, int textSize)
    {
        BODY_CSS = "margin: 0px !important;";
        CODE_CSS = "font-size: " + textSize + "px !important; line-height: 1.2 !important;";
        PRE_CSS = "margin: 0px !important; font-size: " + textSize + "px !important; line-height: 1.2 !important;";
        CODE_CLASS = lang.getLanguageName();
        CODE_TEXT = code;

        if(isShowLineNumber())
        {
            OTHERS_SCRIPTS = "<style>.hljs-line-numbers {\n" +
                    "    text-align: right;\n" +
                    "    border-right: 1px solid #ccc;\n" +
                    "    color: #999;\n" +
                    "    -webkit-touch-callout: none;\n" +
                    "    -webkit-user-select: none;\n" +
                    "}</style>\n";
            OTHERS_SCRIPTS += "<script src=\"" + HLN_PATH + "\"></script>\n";
            OTHERS_SCRIPTS += "<script>hljs.initLineNumbersOnLoad();</script>\n";
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
                JS_PATH,
                OTHERS_SCRIPTS,
                CODE_CLASS, CODE_TEXT);
    }

    public enum Themes implements Theme
    {
        AGATE("agate"),
        ANDROIDSTUDIO("androidstudio"),
        ARDUINO_LIGHT("arduino-light"),
        ARTA("arta"),
        ASCETIC("ascetic"),
        ATELIER_CAVE_DARK("atelier-cave-dark"),
        ATELIER_CAVE_LIGHT("atelier-cave-light"),
        ATELIER_DUNE_DARK("atelier-dune-dark"),
        ATELIER_DUNE_LIGHT("atelier-dune-light"),
        ATELIER_ESTUARY_DARK("atelier-estuary-dark"),
        ATELIER_ESTUARY_LIGHT("atelier-estuary-light"),
        ATELIER_FOREST_DARK("atelier-forest-dark"),
        ATELIER_FOREST_LIGHT("atelier-forest-light"),
        ATELIER_HEATH_DARK("atelier-heath-dark"),
        ATELIER_HEATH_LIGHT("atelier-heath-light"),
        ATELIER_LAKESIDE_DARK("atelier-lakeside-dark"),
        ATELIER_LAKESIDE_LIGHT("atelier-lakeside-light"),
        ATELIER_PLATEAU_DARK("atelier-plateau-dark"),
        ATELIER_PLATEAU_LIGHT("atelier-plateau-light"),
        ATELIER_SAVANNA_DARK("atelier-savanna-dark"),
        ATELIER_SAVANNA_LIGHT("atelier-savanna-light"),
        ATELIER_SEASIDE_DARK("atelier-seaside-dark"),
        ATELIER_SEASIDE_LIGHT("atelier-seaside-light"),
        ATELIER_SULPHURPOOL_DARK("atelier-sulphurpool-dark"),
        ATELIER_SULPHURPOOL_LIGHT("atelier-sulphurpool-light"),
        ATOM_ONE_DARK("atom-one-dark"),
        ATOM_ONE_LIGHT("atom-one-light"),
        BROWN_PAPER("brown-paper"),
        CODEPEN_EMBED("codepen-embed"),
        COLOR_BREWER("color-brewer"),
        DARCULA("darcula"),
        DARK("dark"),
        DARKULA("darkula"),
        DEFAULT("default"),
        DOCCO("docco"),
        DRACULA("dracula"),
        FAR("far"),
        FOUNDATION("foundation"),
        GITHUB("github"),
        GITHUB_GIST("github-gist"),
        GOOGLECODE("googlecode"),
        GRAYSCALE("grayscale"),
        GRUVBOX_DARK("gruvbox-dark"),
        GRUVBOX_LIGHT("gruvbox-light"),
        HOPSCOTCH("hopscotch"),
        HYBRID("hybrid"),
        IDEA("idea"),
        IR_BLACK("ir-black"),
        KIMBIE_DARK("kimbie.dark"),
        KIMBIE_LIGHT("kimbie.light"),
        MAGULA("magula"),
        MONO_BLUE("mono-blue"),
        MONOKAI("monokai"),
        MONOKAI_SUBLIME("monokai-sublime"),
        OBSIDIAN("obsidian"),
        OCEAN("ocean"),
        PARAISO_DARK("paraiso-dark"),
        PARAISO_LIGHT("paraiso-light"),
        POJOAQUE("pojoaque"),
        PUREBASIC("purebasic"),
        QTCREATOR_DARK("qtcreator_dark"),
        QTCREATOR_LIGHT("qtcreator_light"),
        RAILSCASTS("railscasts"),
        RAINBOW("rainbow"),
        SCHOOL_BOOK("school-book"),
        SOLARIZED_DARK("solarized-dark"),
        SOLARIZED_LIGHT("solarized-light"),
        SUNBURST("sunburst"),
        TOMORROW("tomorrow"),
        TOMORROW_NIGHT("tomorrow-night"),
        TOMORROW_NIGHT_BLUE("tomorrow-night-blue"),
        TOMORROW_NIGHT_BRIGHT("tomorrow-night-bright"),
        TOMORROW_NIGHT_EIGHTIES("tomorrow-night-eighties"),
        VS("vs"),
        XCODE("xcode"),
        XT256("xt256"),
        ZENBURN("zenburn");

        private final String name;

        Themes(String name)
        {
            this.name = name;
        }

        @Override
        public String getPath()
        {
            return "file:///android_asset/highlightjs/styles/" + name + ".css";
        }
    }

    public enum Languages implements Language
    {
        AUTO(""),
        _1C("1c"),
        ABNF("abnf"),
        ACCESS_LOG("accesslog"),
        ACTIONSCRIPT("actionscript"),
        ADA("ada"),
        APACHE("apache"),
        APPLESCRIPT("applescript"),
        ARDUINO("arduino"),
        ARM_ASSEMBLY("armasm"),
        ASCII_DOC("asciidoc"),
        ASPECTJ("aspectj"),
        AUTOHOTKEY("autohotkey"),
        AUTOIT("autoit"),
        AVR_ASSEMBLER("avrasm"),
        AWK("awk"),
        AXAPTA("axapta"),
        BASH("bash"),
        BASIC("basic"),
        BNF("bnf"),
        BRAINFUCK("brainfuck"),
        C_AL("cal"),
        CAP_N_PROTO("capnproto"),
        CEYLON("ceylon"),
        CLEAN("clean"),
        CLOJURE("clojure"),
        CLOJURE_REPL("clojure_repl"),
        CMAKE("cmake"),
        COFFEESCRIPT("coffeescript"),
        COQ("coq"),
        CACHE_OBJECT_SCRIPT("cos"),
        CPP("cpp"),
        CRMSH("crmsh"),
        CRYSTAL("crystal"),
        C_SHARP("cs"),
        CSP("csp"),
        CSS("css"),
        D("d"),
        DART("dart"),
        DELPHI("delphi"),
        DIFF("diff"),
        DJANGO("django"),
        DNS("dns"),
        DOCKERFILE("dockerfile"),
        DOS("dos"),
        DSCONFIG("dsconfig"),
        DEVICE_TREE("dts"),
        DUST("dust"),
        EBNF("ebnf"),
        ELIXIR("elixir"),
        ELM("elm"),
        ERB("erb"),
        ERLANG("erlang"),
        ERLANG_REPL("erlang_repl"),
        EXCEL("excel"),
        FIX("fix"),
        FLIX("flix"),
        FORTRAN("fortran"),
        F_SHARP("fsharp"),
        GAMS("gams"),
        GAUSS("gauss"),
        GCODE("gcode"),
        GHERKIN("gherkin"),
        GLSL("glsl"),
        GO("go"),
        GOLO("golo"),
        GRADLE("gradle"),
        GROOVY("groovy"),
        HAML("haml"),
        HANDLEBARS("handlebars"),
        HASKELL("haskell"),
        HAXE("haxe"),
        HSP("hsp"),
        HTMLBARS("htmlbars"),
        HTTP("http"),
        HY("hy"),
        INFORM_7("inform7"),
        INI("ini"),
        IRPF90("irpf90"),
        JAVA("java"),
        JAVASCRIPT("javascript"),
        JSON("json"),
        JULIA("julia"),
        KOTLIN("kotlin"),
        LASSO("lasso"),
        LDIF("ldif"),
        LEAF("leaf"),
        LESS("less"),
        LISP("lisp"),
        LIVECODESERVER("livecodeserver"),
        LIVESCRIPT("livescript"),
        LLVM("llvm"),
        LSL("lsl"),
        LUA("lua"),
        MAKEFILE("makefile"),
        MARKDOWN("markdown"),
        MATHEMATICA("mathematica"),
        MATLAB("matlab"),
        MAXIMA("maxima"),
        MEL("mel"),
        MERCURY("mercury"),
        MIPS_ASSEMBLY("mipsasm"),
        MIZAR("mizar"),
        MOJOLICIOUS("mojolicious"),
        MONKEY("monkey"),
        MOONSCRIPT("moonscript"),
        NGINX("nginx"),
        NIMROD("nimrod"),
        NIX("nix"),
        NSIS("nsis"),
        OBJECTIVE_C("objectivec"),
        OCAML("ocaml"),
        OPENSCAD("openscad"),
        OXYGENE("oxygene"),
        PARSER3("parser3"),
        PERL("perl"),
        PF("pf"),
        PHP("php"),
        PONY("pony"),
        POWERSHELL("powershell"),
        PROCESSING("processing"),
        PROFILE("profile"),
        PROLOG("prolog"),
        PROTOCOL_BUFFERS("protobuf"),
        PUPPET("puppet"),
        PURE_BASIC("purebasic"),
        PYTHON("python"),
        Q("q"),
        QML("qml"),
        R("r"),
        RIB("rib"),
        ROBOCONF("roboconf"),
        RSL("rsl"),
        RUBY("ruby"),
        ORACLE_RULES_LANGUAGE("ruleslanguage"),
        RUST("rust"),
        SCALA("scala"),
        SCHEME("scheme"),
        SCILAB("scilab"),
        SCSS("scss"),
        SMALI("smali"),
        SMALLTALK("smalltalk"),
        SML("sml"),
        SQF("sqf"),
        SQL("sql"),
        STAN("stan"),
        STATA("stata"),
        STEP21("step21"),
        STYLUS("stylus"),
        SUBUNIT("subunit"),
        SWIFT("swift"),
        TAGGERSCRIPT("taggerscript"),
        TAP("tap"),
        TCL("tcl"),
        TEX("tex"),
        THRIFT("thrift"),
        TP("tp"),
        TWIG("twig"),
        TYPESCRIPT("typescript"),
        VALA("vala"),
        VB_NET("vbnet"),
        VBSCRIPT("vbscript"),
        VBSCRIPT_HTML("vbscript_html"),
        VERILOG("verilog"),
        VHDL("vhdl"),
        VIM("vim"),
        X86_ASSEMBLY("x86asm"),
        XL("xl"),
        HTML("html"),
        XML("xml"),
        XQUERY("xquery"),
        YAML("yaml"),
        ZEPHIR("zephir");

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
