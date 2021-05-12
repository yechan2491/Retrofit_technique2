package com.cookandroid.retrofit_technique2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
//https://api.github.com/users/yechan2491 주소 정보 레트로핏으로 가져오기
    // 구조는 List<post> 형식으로 하지 않아도됨
    // post들이 여러개 있을때 List를 사용하는 것임
    // post는 이제 우리가 정의할 Json 데이터 구조를 갖춘 서버와 통신하기 위한 구조를 post 라는 자바 클래스 파일로 정의할것임

    private final String BASEURL = "https://api.github.com";
    private TextView textViewResult,test_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.text_view_result);
        test_text=(TextView) findViewById(R.id.test_text);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Post> call = jsonPlaceHolderApi.getPost("yechan2491");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("code: " + response.code());
                    return;
                }
                Post posts = response.body();
//                Toast.makeText(getApplicationContext(),"성공",Toast.LENGTH_LONG).show();
                textViewResult.setText(posts.getLogin());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_LONG).show();
            }
        });


        // 여기서 부터 데이터 전송송
        HashMap<String,Object> input=new HashMap<>();
        input.put("login","yechan2");
        input.put("id",123);
        input.put("node_id","body body 당근당근");
        jsonPlaceHolderApi.postData(input).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    Post data=response.body();
                    Toast.makeText(getApplicationContext(),"성공",Toast.LENGTH_LONG).show();
                    test_text.setText("안녕");
                    Log.d("test!!",data.getLogin());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"실패2",Toast.LENGTH_LONG).show();
                test_text.setText("실패");
            }
        });
    }
}