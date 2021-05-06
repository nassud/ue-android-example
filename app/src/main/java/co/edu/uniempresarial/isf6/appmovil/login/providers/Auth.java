package co.edu.uniempresarial.isf6.appmovil.login.providers;

import android.content.Context;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;

import org.jetbrains.annotations.NotNull;

import co.edu.uniempresarial.isf6.appmovil.login.MainActivity;
import co.edu.uniempresarial.isf6.appmovil.login.dao.DaoSession;

public class Auth {

    public static void login(Context context) {
        Auth0 auth0 = new Auth0(context);

        Callback callback = new Callback<Credentials, AuthenticationException>() {
            @Override
            public void onSuccess(Credentials credentials) {
                String idToken = credentials.getIdToken();
                String accessToken = credentials.getAccessToken();
                System.out.println("Id token: " + idToken);
                System.out.println("Access token: " + accessToken);
                Toast.makeText(context,"Autenticado con token: " + idToken,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull AuthenticationException e) {
                Toast.makeText(context,"Ocurrió un error: " + e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        };

        WebAuthProvider.login(auth0)
                .withScheme("uelogin")
                .start(context, callback);
    }

}
