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

    private static final String JAVA_CODE = "package com.example.android.bluetoothchat;\n" +
            "\n" +
            "import android.os.Bundle;\n" +
            "import android.support.v4.app.FragmentTransaction;\n" +
            "import android.view.Menu;\n" +
            "import android.view.MenuItem;\n" +
            "import android.widget.ViewAnimator;\n" +
            "\n" +
            "import com.example.android.common.activities.SampleActivityBase;\n" +
            "import com.example.android.common.logger.Log;\n" +
            "import com.example.android.common.logger.LogFragment;\n" +
            "import com.example.android.common.logger.LogWrapper;\n" +
            "import com.example.android.common.logger.MessageOnlyLogFilter;\n" +
            "\n" +
            "/**\n" +
            " * A simple launcher activity containing a summary sample description, sample log and a custom\n" +
            " * {@link android.support.v4.app.Fragment} which can display a view.\n" +
            " * <p>\n" +
            " * For devices with displays with a width of 720dp or greater, the sample log is always visible,\n" +
            " * on other devices it's visibility is controlled by an item on the Action Bar.\n" +
            " */\n" +
            "public class MainActivity extends SampleActivityBase {\n" +
            "\n" +
            "    public static final String TAG = \"MainActivity\";\n" +
            "\n" +
            "    // Whether the Log Fragment is currently shown\n" +
            "    private boolean mLogShown;\n" +
            "\n" +
            "    @Override\n" +
            "    protected void onCreate(Bundle savedInstanceState) {\n" +
            "        super.onCreate(savedInstanceState);\n" +
            "        setContentView(R.layout.activity_main);\n" +
            "\n" +
            "        if (savedInstanceState == null) {\n" +
            "            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();\n" +
            "            BluetoothChatFragment fragment = new BluetoothChatFragment();\n" +
            "            transaction.replace(R.id.sample_content_fragment, fragment);\n" +
            "            transaction.commit();\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public boolean onCreateOptionsMenu(Menu menu) {\n" +
            "        getMenuInflater().inflate(R.menu.main, menu);\n" +
            "        return true;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public boolean onPrepareOptionsMenu(Menu menu) {\n" +
            "        MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);\n" +
            "        logToggle.setVisible(findViewById(R.id.sample_output) instanceof ViewAnimator);\n" +
            "        logToggle.setTitle(mLogShown ? R.string.sample_hide_log : R.string.sample_show_log);\n" +
            "\n" +
            "        return super.onPrepareOptionsMenu(menu);\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public boolean onOptionsItemSelected(MenuItem item) {\n" +
            "        switch(item.getItemId()) {\n" +
            "            case R.id.menu_toggle_log:\n" +
            "                mLogShown = !mLogShown;\n" +
            "                ViewAnimator output = (ViewAnimator) findViewById(R.id.sample_output);\n" +
            "                if (mLogShown) {\n" +
            "                    output.setDisplayedChild(1);\n" +
            "                } else {\n" +
            "                    output.setDisplayedChild(0);\n" +
            "                }\n" +
            "                supportInvalidateOptionsMenu();\n" +
            "                return true;\n" +
            "        }\n" +
            "        return super.onOptionsItemSelected(item);\n" +
            "    }\n" +
            "\n" +
            "    /** Create a chain of targets that will receive log data */\n" +
            "    @Override\n" +
            "    public void initializeLogging() {\n" +
            "        // Wraps Android's native log framework.\n" +
            "        LogWrapper logWrapper = new LogWrapper();\n" +
            "        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.\n" +
            "        Log.setLogNode(logWrapper);\n" +
            "\n" +
            "        // Filter strips out everything except the message text.\n" +
            "        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();\n" +
            "        logWrapper.setNext(msgFilter);\n" +
            "\n" +
            "        // On screen logging via a fragment with a TextView.\n" +
            "        LogFragment logFragment = (LogFragment) getSupportFragmentManager()\n" +
            "                .findFragmentById(R.id.log_fragment);\n" +
            "        msgFilter.setNext(logFragment.getLogView());\n" +
            "\n" +
            "        Log.i(TAG, \"Ready\");\n" +
            "    }\n" +
            "}";

    private SyntaxHighlighter[] SHS = new SyntaxHighlighter[]{
            new HightlightJs(), new Prism()
    };
    private int themePos = 0;
    private int hlPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CodeView cv = (CodeView)findViewById(R.id.code_view);

        cv.setSyntaxHighlighter(new HightlightJs());
        cv.setCode(JAVA_CODE)
                .setLanguage(HightlightJs.Languages.AUTO)
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
                Theme theme = SHS[hlPos].getSupportedThemes()[themePos];
                cv.setTheme(theme).apply();
                Toast.makeText(MainActivity.this, ((Enum)theme).name(), Toast.LENGTH_SHORT).show();
                themePos = ++themePos % SHS[hlPos].getSupportedThemes().length;
                break;
            case R.id.change_highlighter_action:
                hlPos = ++hlPos % SHS.length;
                themePos = 0;
                cv.setSyntaxHighlighter(SHS[hlPos])
                        .setShowLineNumber(true);
                theme = SHS[hlPos].getSupportedThemes()[themePos];
                cv.setTheme(theme).apply();
                Toast.makeText(MainActivity.this, SHS[hlPos].getName(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.show_line_number_action:
                cv.toggleShowLineNumber().apply();
                break;
        }

        return true;
    }
}
