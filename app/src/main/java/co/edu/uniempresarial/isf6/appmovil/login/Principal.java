package co.edu.uniempresarial.isf6.appmovil.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        taskBegin();
    }
    private void taskBegin(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Principal.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer time = new Timer();
        time.schedule(task, 4000);
    }
}