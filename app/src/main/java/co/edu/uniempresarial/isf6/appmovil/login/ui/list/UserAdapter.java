package co.edu.uniempresarial.isf6.appmovil.login.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

import co.edu.uniempresarial.isf6.appmovil.login.R;
import co.edu.uniempresarial.isf6.appmovil.login.entities.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_user_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.userName.setText(user.getUsername());
        holder.email.setText(user.getEmail());

        holder.lastLogin.setText(user.getLastLogin().toString());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView email;
        public TextView lastLogin;
        ConstraintLayout constraintLayout;

        public ViewHolder(View view) {
            super(view);
            this.userName = (TextView) view.findViewById(R.id.userName);
            this.email = (TextView) view.findViewById(R.id.email);
            this.lastLogin = (TextView) view.findViewById(R.id.loginDate);
            constraintLayout = (ConstraintLayout) view.findViewById(R.id.item_user_layout);
        }
    }



}
