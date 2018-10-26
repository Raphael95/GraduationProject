package com.example.rapha.transpotsystem;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import httpResolve.ResolveGson;
import httpReuest.RequestOkHttp;

public class VerifyFailureActivity extends AppCompatActivity {

    private RequestOkHttp okHttp=new RequestOkHttp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_failure);

        Button  verify_again=(Button)findViewById(R.id.verify_again);
        verify_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteDriver(ResolveGson.jiashiyuan.getDianhua()).execute();
            }
        });
    }

    class DeleteDriver extends AsyncTask<Void,Void,Void>{
        private String phone;

        public DeleteDriver(String phone){
            this.phone=phone;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            okHttp.deleteDriver(phone);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            finish();
            Intent intent=new Intent(VerifyFailureActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    }
}
