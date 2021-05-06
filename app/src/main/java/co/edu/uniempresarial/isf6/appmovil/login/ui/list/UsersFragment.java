package co.edu.uniempresarial.isf6.appmovil.login.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.uniempresarial.isf6.appmovil.login.MainActivity;
import co.edu.uniempresarial.isf6.appmovil.login.R;
import co.edu.uniempresarial.isf6.appmovil.login.dao.DaoSession;
import co.edu.uniempresarial.isf6.appmovil.login.dao.UserDao;
import co.edu.uniempresarial.isf6.appmovil.login.entities.User;

public class UsersFragment extends Fragment {

    UserDao userDao;
    List<User> userList;
    private UsersViewModel usersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usersViewModel =
                new ViewModelProvider(this).get(UsersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        DaoSession daoSession = ((MainActivity)getActivity()).getDaoSession();
        userDao = daoSession.getUserDao();

        // TODO Usuario de ejemplo.. Remover al guardar desde el login
        if(userDao.queryBuilder().count() == 0){
            User user = new User();
            user.setUsername("ejemploUser");

            userDao.save(user);
        }

        final RecyclerView rvUsers = root.findViewById(R.id.rvUsers);
        userList = userDao.queryBuilder().list();

        UserAdapter userAdapter = new UserAdapter(userList);
        rvUsers.setAdapter(userAdapter);

        rvUsers.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        return root;
    }
}