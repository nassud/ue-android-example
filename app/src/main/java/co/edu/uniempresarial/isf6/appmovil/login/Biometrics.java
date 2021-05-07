package co.edu.uniempresarial.isf6.appmovil.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class Biometrics extends AppCompatActivity {


    //Biometric

    private TextView tvStatus;
    private Button btnAuth;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biometrics);

        tvStatus = findViewById(R.id.tvStatus);
        btnAuth = findViewById(R.id.btnAuth);

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(Biometrics.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull @NotNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                tvStatus.setText("Error de autenticacion: " + errString);
                Toast.makeText(Biometrics.this, "Error de autenticacion: " + errString
                        ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull @NotNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                tvStatus.setText("Autenticacion exitosa...");
                Toast.makeText(Biometrics.this, "Autenticacion exitosa..."
                        ,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Biometrics.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                tvStatus.setText("Autenticacion Fallida...");
                Toast.makeText(Biometrics.this, "Autenticacion Fallida..."
                        ,Toast.LENGTH_SHORT).show();
            }
        });


        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticacion biometrica")
                .setSubtitle("Logeo mediante huella dactilar")
                .setNegativeButtonText("User App Password")
                .build();


        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });

    }
}