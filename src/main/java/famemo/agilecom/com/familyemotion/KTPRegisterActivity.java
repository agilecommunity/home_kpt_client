package famemo.agilecom.com.familyemotion;

import android.content.Intent;
import android.content.res.Resources;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class KTPRegisterActivity extends AppCompatActivity {

    TextView tv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ktpregister);
        iv = (ImageView) findViewById(R.id.imageView);
        tv = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        Bundle bdl = intent.getExtras();
        tv.setText(bdl.getString("content"));
        Resources res = getResources();
        iv.setImageDrawable(res.getDrawable(EmotionEntry.getResourceId(bdl.getInt("emotion"))));
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

    }

    public void onClick(View clickedButton) {
        String buttonText = ((Button) clickedButton).getText().toString();
        String kind = buttonText.toLowerCase();
        if (kind.equals("keep") || kind.equals("problem")) {
            postMemo(kind);
        }
        finish();
    }

    public void postMemo(String kind) {

        String ret = "";

        // URL
        URI url = null;
        try {
            url = new URI("http://ajakohomekptdemo.cloudapp.net:3000/memos");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            ret = e.toString();
        }

        JSONObject root = new JSONObject();
        JSONObject memo = new JSONObject();

        // POSTパラメータ付きでPOSTリクエストを構築
        HttpPost request = new HttpPost(url);

        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");
        try {
            memo.put("content", tv.getText().toString());
            memo.put("kind", kind);
            root.put("memo", memo);
            StringEntity params = new StringEntity(root.toString());
            // 送信パラメータのエンコードを指定
            request.setEntity(params);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // POSTリクエストを実行
        HttpClient httpclient = new DefaultHttpClient();

        try {
            Log.d("posttest", "POST開始");
            ret = httpclient.execute(request, new ResponseHandler<String>() {

                @Override
                public String handleResponse(HttpResponse response) throws IOException {
                    Log.d(
                            "posttest",
                            "レスポンスコード：" + response.getStatusLine().getStatusCode()
                    );

                    // 正常に受信できた場合は200
                    switch (response.getStatusLine().getStatusCode()) {
                        case HttpStatus.SC_OK:
                            Log.d("posttest", "レスポンス取得に成功");

                            // レスポンスデータをエンコード済みの文字列として取得する
                            return EntityUtils.toString(response.getEntity(), "UTF-8");

                        case HttpStatus.SC_NOT_FOUND:
                            Log.d("posttest", "データが存在しない");
                            return null;

                        default:
                            Log.d("posttest", "通信エラー");
                            return null;
                    }

                }

            });

        } catch (IOException e) {
            Log.d("posttest", "通信に失敗：" + e.toString());
        } finally {
            // shutdownすると通信できなくなる
            httpclient.getConnectionManager().shutdown();
        }

        // 受信結果をUIに表示
        tv.setText(ret);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ktpregister, menu);
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
