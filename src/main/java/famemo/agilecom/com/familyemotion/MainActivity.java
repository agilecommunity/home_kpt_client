package famemo.agilecom.com.familyemotion;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mEmotionList;
    EmotionAdapter mEmotionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<EmotionEntry> emotions = new ArrayList<EmotionEntry>();
        emotions.add(new EmotionEntry("今日は優しかった", 0));
        emotions.add(new EmotionEntry("家事手伝ってくれた", 1));
        emotions.add(new EmotionEntry("ゲームばっかりしてた", 2));
        emotions.add(new EmotionEntry("テレビばっかり見てた", 3));
        emotions.add(new EmotionEntry("ご飯食べたらお腹壊した。", 4));
        emotions.add(new EmotionEntry("test6", 5));
        emotions.add(new EmotionEntry("test7", 6));
        emotions.add(new EmotionEntry("test8", 7));
        emotions.add(new EmotionEntry("test9", 8));
        emotions.add(new EmotionEntry("test10", 9));
        emotions.add(new EmotionEntry("test11", 10));
        emotions.add(new EmotionEntry("test12", 11));
        mEmotionAdapter = new EmotionAdapter(getApplicationContext(), R.layout.emotion_list, R.id.text, emotions);

        mEmotionList = (ListView) findViewById(R.id.listView);
        mEmotionList.setAdapter(mEmotionAdapter);
        mEmotionList.setBackgroundColor(Color.BLACK);
        LinearLayout input = (LinearLayout) findViewById(R.id.input);
        input.bringToFront();
        input.setBackgroundColor(Color.WHITE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
