package br.tiagohm.codeview;

import java.util.Arrays;
import java.util.List;

public class Theme {

    public static final Theme AGATE = new Theme("agate");
    public static final Theme ANDROIDSTUDIO = new Theme("androidstudio");
    public static final Theme ARDUINO_LIGHT = new Theme("arduino-light");
    public static final Theme ARTA = new Theme("arta");
    public static final Theme ASCETIC = new Theme("ascetic");
    public static final Theme ATELIER_CAVE_DARK = new Theme("atelier-cave-dark");
    public static final Theme ATELIER_CAVE_LIGHT = new Theme("atelier-cave-light");
    public static final Theme ATELIER_DUNE_DARK = new Theme("atelier-dune-dark");
    public static final Theme ATELIER_DUNE_LIGHT = new Theme("atelier-dune-light");
    public static final Theme ATELIER_ESTUARY_DARK = new Theme("atelier-estuary-dark");
    public static final Theme ATELIER_ESTUARY_LIGHT = new Theme("atelier-estuary-light");
    public static final Theme ATELIER_FOREST_DARK = new Theme("atelier-forest-dark");
    public static final Theme ATELIER_FOREST_LIGHT = new Theme("atelier-forest-light");
    public static final Theme ATELIER_HEATH_DARK = new Theme("atelier-heath-dark");
    public static final Theme ATELIER_HEATH_LIGHT = new Theme("atelier-heath-light");
    public static final Theme ATELIER_LAKESIDE_DARK = new Theme("atelier-lakeside-dark");
    public static final Theme ATELIER_LAKESIDE_LIGHT = new Theme("atelier-lakeside-light");
    public static final Theme ATELIER_PLATEAU_DARK = new Theme("atelier-plateau-dark");
    public static final Theme ATELIER_PLATEAU_LIGHT = new Theme("atelier-plateau-light");
    public static final Theme ATELIER_SAVANNA_DARK = new Theme("atelier-savanna-dark");
    public static final Theme ATELIER_SAVANNA_LIGHT = new Theme("atelier-savanna-light");
    public static final Theme ATELIER_SEASIDE_DARK = new Theme("atelier-seaside-dark");
    public static final Theme ATELIER_SEASIDE_LIGHT = new Theme("atelier-seaside-light");
    public static final Theme ATELIER_SULPHURPOOL_DARK = new Theme("atelier-sulphurpool-dark");
    public static final Theme ATELIER_SULPHURPOOL_LIGHT = new Theme("atelier-sulphurpool-light");
    public static final Theme ATOM_ONE_DARK = new Theme("atom-one-dark");
    public static final Theme ATOM_ONE_LIGHT = new Theme("atom-one-light");
    public static final Theme BROWN_PAPER = new Theme("brown-paper");
    public static final Theme CODEPEN_EMBED = new Theme("codepen-embed");
    public static final Theme COLOR_BREWER = new Theme("color-brewer");
    public static final Theme DARCULA = new Theme("darcula");
    public static final Theme DARK = new Theme("dark");
    public static final Theme DARKULA = new Theme("darkula");
    public static final Theme DEFAULT = new Theme("default");
    public static final Theme DOCCO = new Theme("docco");
    public static final Theme DRACULA = new Theme("dracula");
    public static final Theme FAR = new Theme("far");
    public static final Theme FOUNDATION = new Theme("foundation");
    public static final Theme GITHUB = new Theme("github");
    public static final Theme GITHUB_GIST = new Theme("github-gist");
    public static final Theme GOOGLECODE = new Theme("googlecode");
    public static final Theme GRAYSCALE = new Theme("grayscale");
    public static final Theme GRUVBOX_DARK = new Theme("gruvbox-dark");
    public static final Theme GRUVBOX_LIGHT = new Theme("gruvbox-light");
    public static final Theme HOPSCOTCH = new Theme("hopscotch");
    public static final Theme HYBRID = new Theme("hybrid");
    public static final Theme IDEA = new Theme("idea");
    public static final Theme IR_BLACK = new Theme("ir-black");
    public static final Theme KIMBIE_DARK = new Theme("kimbie.dark");
    public static final Theme KIMBIE_LIGHT = new Theme("kimbie.light");
    public static final Theme MAGULA = new Theme("magula");
    public static final Theme MONO_BLUE = new Theme("mono-blue");
    public static final Theme MONOKAI = new Theme("monokai");
    public static final Theme MONOKAI_SUBLIME = new Theme("monokai-sublime");
    public static final Theme OBSIDIAN = new Theme("obsidian");
    public static final Theme OCEAN = new Theme("ocean");
    public static final Theme PARAISO_DARK = new Theme("paraiso-dark");
    public static final Theme PARAISO_LIGHT = new Theme("paraiso-light");
    public static final Theme POJOAQUE = new Theme("pojoaque");
    public static final Theme PUREBASIC = new Theme("purebasic");
    public static final Theme QTCREATOR_DARK = new Theme("qtcreator_dark");
    public static final Theme QTCREATOR_LIGHT = new Theme("qtcreator_light");
    public static final Theme RAILSCASTS = new Theme("railscasts");
    public static final Theme RAINBOW = new Theme("rainbow");
    public static final Theme SCHOOL_BOOK = new Theme("school-book");
    public static final Theme SOLARIZED_DARK = new Theme("solarized-dark");
    public static final Theme SOLARIZED_LIGHT = new Theme("solarized-light");
    public static final Theme SUNBURST = new Theme("sunburst");
    public static final Theme TOMORROW = new Theme("tomorrow");
    public static final Theme TOMORROW_NIGHT = new Theme("tomorrow-night");
    public static final Theme TOMORROW_NIGHT_BLUE = new Theme("tomorrow-night-blue");
    public static final Theme TOMORROW_NIGHT_BRIGHT = new Theme("tomorrow-night-bright");
    public static final Theme TOMORROW_NIGHT_EIGHTIES = new Theme("tomorrow-night-eighties");
    public static final Theme VS = new Theme("vs");
    public static final Theme VS2015 = new Theme("vs2015");
    public static final Theme XCODE = new Theme("xcode");
    public static final Theme XT256 = new Theme("xt256");
    public static final Theme ZENBURN = new Theme("zenburn");

    public static final List<Theme> ALL = Arrays.asList(
            AGATE, ANDROIDSTUDIO, ARDUINO_LIGHT, ARTA, ASCETIC, ATELIER_CAVE_DARK, ATELIER_CAVE_LIGHT,
            ATELIER_DUNE_DARK, ATELIER_DUNE_LIGHT, ATELIER_DUNE_DARK, ATELIER_ESTUARY_DARK, ATELIER_ESTUARY_LIGHT,
            ATELIER_FOREST_DARK, ATELIER_FOREST_DARK, ATELIER_FOREST_LIGHT, ATELIER_HEATH_DARK, ATELIER_HEATH_LIGHT, ATELIER_LAKESIDE_DARK,
            ATELIER_LAKESIDE_LIGHT, ATELIER_PLATEAU_DARK, ATELIER_PLATEAU_LIGHT, ATELIER_SAVANNA_DARK, ATELIER_SAVANNA_LIGHT,
            ATELIER_SEASIDE_LIGHT, ATELIER_SEASIDE_DARK, ATELIER_SULPHURPOOL_DARK, ATELIER_SULPHURPOOL_LIGHT,
            ATOM_ONE_DARK, ATOM_ONE_LIGHT, BROWN_PAPER, CODEPEN_EMBED, COLOR_BREWER, DARCULA, DARK, DARKULA,
            DEFAULT, DOCCO, DRACULA, FAR, FOUNDATION, GITHUB, GITHUB_GIST, GOOGLECODE, GRAYSCALE, GRUVBOX_DARK,
            GRUVBOX_LIGHT, HOPSCOTCH, HYBRID, IDEA, IR_BLACK, KIMBIE_DARK, KIMBIE_LIGHT, MAGULA, MONO_BLUE,
            MONOKAI, MONOKAI_SUBLIME, OBSIDIAN, OCEAN, PARAISO_DARK, PARAISO_LIGHT, POJOAQUE, PUREBASIC,
            QTCREATOR_DARK, QTCREATOR_LIGHT, RAILSCASTS, RAINBOW, SCHOOL_BOOK, SOLARIZED_DARK, SOLARIZED_LIGHT, SUNBURST, TOMORROW,
            TOMORROW_NIGHT, TOMORROW_NIGHT_BLUE, TOMORROW_NIGHT_BRIGHT, TOMORROW_NIGHT_EIGHTIES, VS, VS2015,
            XCODE, XT256, ZENBURN);

    private final String name;

    public Theme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return "file:///android_asset/highlightjs/styles/" + getName() + ".css";
    }
}
