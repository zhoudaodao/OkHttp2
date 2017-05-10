package bawei.com.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_get,bt_get1;
    private Button bt_post,bt_post1;
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_get=(Button)findViewById(R.id.tget);
        bt_get1=(Button)findViewById(R.id.yget);
        bt_post=(Button)findViewById(R.id.tpost);
        bt_post1=(Button)findViewById(R.id.ypost);
        bt_get1.setOnClickListener(this);
        bt_get.setOnClickListener(this);
        bt_post.setOnClickListener(this);
        bt_post1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tget:
                getRequest();
                break;

            case R.id.tpost:
                postRequest();
                break;
            case R.id.yget:
                get1Request();
                break;

            case R.id.ypost:
                post1Request();
                break;


        }
    }
//post异步请求
    private void post1Request() {
        RequestBody body = new FormBody
                .Builder()
                .add("processID", "getNavigateNews")
                .build();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://admin.wap.china.com/user/NavigateTypeAction.do")
                .post(body)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
//get异步请求
    private void get1Request() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news")
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    //get同步请求
    private void getRequest() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news")
                        .build();


                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    final String string = response.body().string();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
    //post同步请求
    private void postRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody body = new FormBody
                        .Builder()
                        .add("processID", "getNavigateNews")
                        .build();
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://admin.wap.china.com/user/NavigateTypeAction.do")
                        .post(body)
                        .build();


                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    final String string = response.body().string();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
