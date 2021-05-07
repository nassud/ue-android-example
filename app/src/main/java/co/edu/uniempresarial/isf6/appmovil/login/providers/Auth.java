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

    public static void login(Context context, Callback callback) {
        Auth0 auth0 = new Auth0(context);

        WebAuthProvider.login(auth0)
                .withScheme("uelogin")
                .start(context, callback);
    }

}
