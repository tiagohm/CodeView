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
    private static final String ARDUINO = "#include <SPI.h>  // needed for Arduino versions later than 0018\n" +
            "#include <Ethernet.h>\n" +
            "#include <EthernetUdp.h>  // UDP library from: bjoern@cs.stanford.edu 12/30/2008\n" +
            "\n" +
            "\n" +
            "// Enter a MAC address and IP address for your controller below.\n" +
            "// The IP address will be dependent on your local network:\n" +
            "byte mac[] = {\n" +
            "  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED\n" +
            "};\n" +
            "IPAddress ip(192, 168, 1, 177);\n" +
            "\n" +
            "unsigned int localPort = 8888;      // local port to listen on\n" +
            "\n" +
            "// buffers for receiving and sending data\n" +
            "char packetBuffer[UDP_TX_PACKET_MAX_SIZE];  //buffer to hold incoming packet,\n" +
            "char  ReplyBuffer[] = \"acknowledged\";       // a string to send back\n" +
            "\n" +
            "// An EthernetUDP instance to let us send and receive packets over UDP\n" +
            "EthernetUDP Udp;\n" +
            "\n" +
            "void setup() {\n" +
            "  // start the Ethernet and UDP:\n" +
            "  Ethernet.begin(mac, ip);\n" +
            "  Udp.begin(localPort);\n" +
            "\n" +
            "  Serial.begin(9600);\n" +
            "}\n" +
            "\n" +
            "void loop() {\n" +
            "  // if there's data available, read a packet\n" +
            "  int packetSize = Udp.parsePacket();\n" +
            "  if (packetSize) {\n" +
            "    Serial.print(\"Received packet of size \");\n" +
            "    Serial.println(packetSize);\n" +
            "    Serial.print(\"From \");\n" +
            "    IPAddress remote = Udp.remoteIP();\n" +
            "    for (int i = 0; i < 4; i++) {\n" +
            "      Serial.print(remote[i], DEC);\n" +
            "      if (i < 3) {\n" +
            "        Serial.print(\".\");\n" +
            "      }\n" +
            "    }\n" +
            "    Serial.print(\", port \");\n" +
            "    Serial.println(Udp.remotePort());\n" +
            "\n" +
            "    // read the packet into packetBufffer\n" +
            "    Udp.read(packetBuffer, UDP_TX_PACKET_MAX_SIZE);\n" +
            "    Serial.println(\"Contents:\");\n" +
            "    Serial.println(packetBuffer);\n" +
            "\n" +
            "    // send a reply to the IP address and port that sent us the packet we received\n" +
            "    Udp.beginPacket(Udp.remoteIP(), Udp.remotePort());\n" +
            "    Udp.write(ReplyBuffer);\n" +
            "    Udp.endPacket();\n" +
            "  }\n" +
            "  delay(10);\n" +
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
        cv.setCode(C_CODE)
                .setLanguage(HightlightJs.Languages.CPP)
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
                            .setLanguage(Prism.Languages.C);
                    THEMES = Prism.Themes.values();
                }
                else
                {
                    highlighter = 0;
                    cv.setSyntaxHighlighter(sh = new HightlightJs())
                            .setLanguage(HightlightJs.Languages.CPP);
                    THEMES = HightlightJs.Themes.values();
                }

                themePos = 0;
                cv.setTheme(THEMES[themePos]).apply();
                break;
        }

        return true;
    }
}
