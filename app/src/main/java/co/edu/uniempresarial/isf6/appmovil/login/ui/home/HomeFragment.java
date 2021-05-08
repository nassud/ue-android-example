package co.edu.uniempresarial.isf6.appmovil.login.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.jwt.JWT;
import com.auth0.android.result.Credentials;

import org.jetbrains.annotations.NotNull;

import co.edu.uniempresarial.isf6.appmovil.login.MainActivity;
import co.edu.uniempresarial.isf6.appmovil.login.R;
import co.edu.uniempresarial.isf6.appmovil.login.dao.DaoSession;
import co.edu.uniempresarial.isf6.appmovil.login.dao.UserDao;
import co.edu.uniempresarial.isf6.appmovil.login.entities.User;
import co.edu.uniempresarial.isf6.appmovil.login.providers.Auth;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button button = (Button) root.findViewById(R.id.buttonLogin);
        button.setOnClickListener(v -> Auth.login(root.getContext(), getAuthCallback(root.getContext())));

        return root;
    }

    private Callback getAuthCallback(Context context) {
        DaoSession daoSession = ((MainActivity)getActivity()).getDaoSession();
        UserDao userDao = daoSession.getUserDao();

        Callback callback = new Callback<Credentials, AuthenticationException>() {
            @Override
            public void onSuccess(Credentials credentials) {
                String idToken = credentials.getIdToken();

                JWT jwt = new JWT(idToken);

                User user = new User();
                user.setUsername(jwt.getClaim("nickname").asString());
                user.setName(jwt.getClaim("name").asString());
                user.setEmail(jwt.getClaim("email").asString());
                user.setPicture(jwt.getClaim("picture").asString());
                user.setSubject(jwt.getSubject());
                user.setToken(idToken);
                user.setLastLogin(jwt.getIssuedAt());

                userDao.save(user);

                System.out.println("Token: " + user.getToken());
                Toast.makeText(context,"Se ha autenticado: " + user.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull AuthenticationException e) {
                Toast.makeText(context,"Ocurrió un error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        return callback;
    }
}