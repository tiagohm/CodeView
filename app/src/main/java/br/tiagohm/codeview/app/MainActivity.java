package br.tiagohm.codeview.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Prism;
import br.tiagohm.codeview.Theme;

public class MainActivity extends AppCompatActivity
{

    private static final String C_CODE = "#include <stdio.h>\n" +
            "#include <stdlib.h>  /* For exit() function */\n" +
            "int main()\n" +
            "{\n" +
            "   char sentence[1000];\n" +
            "   FILE *fptr;\n" +
            "\n" +
            "   fptr = fopen(\"program.txt\", \"w\");\n" +
            "   if(fptr == NULL)\n" +
            "   {\n" +
            "      printf(\"Error!\");\n" +
            "      exit(1);\n" +
            "   }\n" +
            "   \n" +
            "   printf(\"Enter a sentence:\\n\");\n" +
            "   gets(sentence);\n" +
            "\n" +
            "   fprintf(fptr,\"%s\", sentence);\n" +
            "   fclose(fptr);\n" +
            "\n" +
            "   return 0;\n" +
            "}";
    private static final Theme[] THEMES = Prism.Themes.values();
    private int themePos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CodeView cv = (CodeView)findViewById(R.id.code_view);
        cv.setCode(C_CODE)
                .setLanguage(Prism.Languages.C)
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

        switch(id)
        {
            case R.id.change_theme_action:
                final CodeView cv = (CodeView)findViewById(R.id.code_view);
                cv.setTheme(THEMES[themePos]).apply();
                Toast.makeText(MainActivity.this, ((Prism.Themes)THEMES[themePos]).name(), Toast.LENGTH_SHORT).show();
                themePos = ++themePos % THEMES.length;
                break;
        }

        return true;
    }
}
