package com.shy.lunbotu.inter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private View get;
    private View post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initEvent();
    }

    private void initEvent() {
        get.setOnClickListener(this);
        post.setOnClickListener(this);
    }

    private void initView() {
        get = findViewById(R.id.okhttpGet);
        post = findViewById(R.id.okhttpPost);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okhttpGet:
                initGet();
                break;
            case R.id.okhttpPost:
                initPost();
                break;
            default:


        }

    }

    private void initGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request build = new Request.Builder().url("http://192.168.120.220:8082/get").build();
                try {
                    Response response = okHttpClient.newCall(build).execute();
                    final String string = response.body().string();
                    Log.d("//////", string);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(OkHttpActivity.this, string, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initPost() {
        final String url = "http://192.168.120.220:8082/post";
        final String[] arr = new String[]{"1", "2", "3", "4", "5"};
        final String str = "123";
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, str);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("////", "失败");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(OkHttpActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("////", "成功");
                        final String context = response.body().string();
                        Log.d("////", context);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(OkHttpActivity.this, context, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }).start();
    }
}
