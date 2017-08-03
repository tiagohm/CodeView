package br.tiagohm.codeview.app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;

public class MainActivity extends AppCompatActivity implements CodeView.OnHighlightListener {

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
            "    public boolean onPrepareOptionsMenu(Menu menu) {\n" +
            "        MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);\n" +
            "        logToggle.setVisible(findViewById(R.id.sample_output) instanceof ViewAnimator);\n" +
            "        logToggle.setTitle(mLogShown ? R.string.sample_hide_log : R.string.sample_show_log);\n" +
            "\n" +
            "        return super.onPrepareOptionsMenu(menu);\n" +
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

    CodeView mCodeView;
    private ProgressDialog mProgressDialog;
    private int themePos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCodeView = (CodeView) findViewById(R.id.code_view);

        mCodeView.setOnHighlightListener(this)
                .setOnHighlightListener(this)
                .setTheme(Theme.AGATE)
                .setCode(JAVA_CODE)
                .setLanguage(Language.AUTO)
                .setWrapLine(true)
                .setFontSize(14)
                .setZoomEnabled(true)
                .setShowLineNumber(true)
                .setStartLineNumber(1)
                .apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_theme_action:
                setHighlightTheme(++themePos);
                return true;
            case R.id.show_line_number_action:
                mCodeView.toggleLineNumber();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStartCodeHighlight() {
        mProgressDialog = ProgressDialog.show(this, null, "Carregando...", true);
    }

    @Override
    public void onFinishCodeHighlight() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Toast.makeText(this, "line count: " + mCodeView.getLineCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLanguageDetected(Language language, int relevance) {
        Toast.makeText(this, "language: " + language + " relevance: " + relevance, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFontSizeChanged(int sizeInPx) {
        Log.d("TAG", "font-size: " + sizeInPx + "px");
    }

    @Override
    public void onLineClicked(int lineNumber, String content) {
        Toast.makeText(this, "line: " + lineNumber + " html: " + content, Toast.LENGTH_SHORT).show();
    }

    private void setHighlightTheme(int pos) {
        mCodeView.setTheme(Theme.ALL.get(pos)).apply();
        Toast.makeText(this, Theme.ALL.get(pos).getName(), Toast.LENGTH_SHORT).show();
    }
}
