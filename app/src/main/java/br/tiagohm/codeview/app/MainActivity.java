package br.tiagohm.codeview.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.HightlightJs;
import br.tiagohm.codeview.Prism;
import br.tiagohm.codeview.SyntaxHighlighter;
import br.tiagohm.codeview.Theme;

public class MainActivity extends AppCompatActivity
{

    private static final String JAVA_CODE = "import java.util.*;\n" +
            "import java.awt.*;\n" +
            "import java.awt.event.*;\n" +
            "\n" +
            "import javax.swing.*;\n" +
            "import javax.accessibility.*;\n" +
            "\n" +
            "public class BigExample extends JFrame {\n" +
            "\n" +
            "  public BigExample() {\n" +
            "    super(\"Big Accessibility Example\");\n" +
            "    setSize(700,500);\n" +
            "    setDefaultCloseOperation(EXIT_ON_CLOSE);\n" +
            "\n" +
            "    JMenuBar jmb = new JMenuBar();\n" +
            "    JMenu fileMenu = new JMenu(\"File\");\n" +
            "    JMenuItem openItem = new JMenuItem(\"Open\");\n" +
            "    JMenuItem saveItem = new JMenuItem(\"Save\");\n" +
            "    JMenuItem exitItem = new JMenuItem(\"Exit\");\n" +
            "    exitItem.addActionListener(new ActionListener() {\n" +
            "      public void actionPerformed(ActionEvent ae) {\n" +
            "        System.exit(0);\n" +
            "      }\n" +
            "    });\n" +
            "\n" +
            "    fileMenu.add(openItem);\n" +
            "    fileMenu.add(saveItem);\n" +
            "    fileMenu.add(new JSeparator());\n" +
            "    fileMenu.add(exitItem);\n" +
            "    jmb.add(fileMenu);\n" +
            "    setJMenuBar(jmb);\n" +
            "\n" +
            "    JTextArea jta = new JTextArea(\"[Notes]\\n\");\n" +
            "    JScrollPane sp1 = new JScrollPane(jta);\n" +
            "    sp1.setMinimumSize(new Dimension(200,200));\n" +
            "    sp1.setPreferredSize(new Dimension(200,200));\n" +
            "\n" +
            "    String[] titles = { \"Name\", \"Start Date\", \"Job Title\" };\n" +
            "    String[][] data = {\n" +
            "      {\"Jordan\", \"2001\", \"Director\"},\n" +
            "      {\"Naveen\", \"1999\", \"Programmer\"},\n" +
            "      {\"Jia\", \"2000\", \"Analyst\"},\n" +
            "      {\"Brooks\", \"1998\", \"Evangelist\"}\n" +
            "    };\n" +
            "    JTable table = new JTable(data, titles);\n" +
            "    table.getAccessibleContext().setAccessibleDescription(\"Employee Statistics\");\n" +
            "    JPanel rightPane = new JPanel(new BorderLayout());\n" +
            "    rightPane.add(new JScrollPane(table), BorderLayout.CENTER);\n" +
            "    rightPane.add(new JLabel(new ImageIcon(\"logo.gif\")), BorderLayout.SOUTH);\n" +
            "\n" +
            "    JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp1, rightPane);\n" +
            "\n" +
            "    getContentPane().add(jsp, BorderLayout.CENTER);\n" +
            "\n" +
            "    JPanel bPane = new JPanel();\n" +
            "    JButton okButton = new JButton(\"Ok\");\n" +
            "    JButton applyButton = new JButton(\"Apply\");\n" +
            "    JButton clearButton = new JButton(\"Clear\");\n" +
            "    bPane.add(okButton);\n" +
            "    bPane.add(applyButton);\n" +
            "    bPane.add(clearButton);\n" +
            "\n" +
            "    getContentPane().add(bPane, BorderLayout.SOUTH);\n" +
            "\n" +
            "    setVisible(true);\n" +
            "  }\n" +
            "\n" +
            "  public static void main(String args[]) {\n" +
            "    new BigExample();\n" +
            "    new AssistiveExample();\n" +
            "  }\n" +
            "}";

    private Theme[] THEMES;
    private int themePos = 0;
    private int highlighter = 0;
    private SyntaxHighlighter sh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CodeView cv = (CodeView)findViewById(R.id.code_view);

        THEMES = HightlightJs.Themes.values();
        cv.setSyntaxHighlighter(sh = new HightlightJs());
        cv.setCode(JAVA_CODE)
                .setLanguage(HightlightJs.Languages.JAVA)
                .setTextSize(12)
                .setShowLineNumber(true)
                .apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        final int id = item.getItemId();
        final CodeView cv = (CodeView)findViewById(R.id.code_view);

        switch(id)
        {
            case R.id.change_theme_action:
                cv.setTheme(THEMES[themePos]).apply();
                Toast.makeText(MainActivity.this, ((Enum)THEMES[themePos]).name(), Toast.LENGTH_SHORT).show();
                themePos = ++themePos % THEMES.length;
                break;
            case R.id.change_highlighter_action:
                if(highlighter == 0)
                {
                    highlighter = 1;
                    cv.setSyntaxHighlighter(sh = new Prism())
                            .setShowLineNumber(true)
                            .setLanguage(Prism.Languages.JAVA);
                    THEMES = Prism.Themes.values();
                }
                else
                {
                    highlighter = 0;
                    cv.setSyntaxHighlighter(sh = new HightlightJs())
                            .setShowLineNumber(true)
                            .setLanguage(HightlightJs.Languages.JAVA);
                    THEMES = HightlightJs.Themes.values();
                }

                themePos = 0;
                cv.setTheme(THEMES[themePos]).apply();
                break;
            case R.id.show_line_number_action:
                cv.toggleShowLineNumber().apply();
                break;
        }

        return true;
    }
}
