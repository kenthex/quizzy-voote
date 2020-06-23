package com.example.quizzyvoote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzyvoote.classes.Storage;
import com.example.quizzyvoote.classes.api_NetworkService;
import com.example.quizzyvoote.classes.api_Tokens;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btn_signin;
    Button btn_signup;
    TextView textView;
    SharedPreferences data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        Storage.init(MainActivity.this);
        String token = Storage.getProperty("TOKEN");

        if(token != null) {
            //Toast.makeText(MainActivity.this, "TOKEN: " + token, Toast.LENGTH_SHORT).show();
            api_NetworkService.getInstance()
                    .getJSONApi()
                    .getToken(token)
                    .enqueue(new Callback<api_Tokens>() {
                        @Override
                        public void onResponse(@NonNull Call<api_Tokens> call, @NonNull Response<api_Tokens> response) {
                            api_Tokens post = response.body();

                            long createdAt = post.getCreatedAt();
                            long expiredAt = post.getExpiredAt();

                            if(expiredAt > createdAt) {
                                /// OK
                            } else {

                            }

                            Storage.addProperty("TOKEN", post.getToken());

                            api_NetworkService.getInstance()
                                    .getJSONApi()
                                    .delToken(post.getToken())
                                    .enqueue(new Callback<api_Tokens>() {
                                        @Override
                                        public void onResponse(@NonNull Call<api_Tokens> call, @NonNull Response<api_Tokens> response) {
                                            api_Tokens post = response.body();
                                        }
                                        @Override
                                        public void onFailure(@NonNull Call<api_Tokens> call, @NonNull Throwable t) {
                                            Log.d("RES", "ERROR");
                                            t.printStackTrace();
                                        }
                                    });


//                          Intent intent = new Intent(SigninActivity.this, MainActionActivity.class);
//                          startActivity(intent);
                            Toast.makeText(MainActivity.this, "TOKEN: " + Storage.getProperty("TOKEN"), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(@NonNull Call<api_Tokens> call, @NonNull Throwable t) {
                            Log.d("RES", "ERROR");
                            t.printStackTrace();
                        }
                    });


        } else { Toast.makeText(MainActivity.this, "NO TOKEN", Toast.LENGTH_SHORT).show(); }

    }
}
