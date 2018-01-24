package com.foodback.foodback.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.config.FeaturedEndpoints;
import com.foodback.foodback.config.UserEndpoints;
import com.foodback.foodback.logic.Comment;
import com.foodback.foodback.logic.Featured;
import com.foodback.foodback.logic.Meal;
import com.foodback.foodback.logic.User;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

/**
 * Created by Foodback on 2017/2018.
 */

public class EstablishmentCommentAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Comment> comments;

    public EstablishmentCommentAdapter(Context context, ArrayList<Comment> comments) {
        super(context, R.layout.layout_estabpage_comments, comments);

        this.context = context;
        this.comments = comments;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        if(null == convertView) {
            convertView = inflater.inflate(R.layout.layout_estabpage_menu, parent, false);
        }

        TextView comment_user = convertView.findViewById(R.id.comment_user);
        TextView comment_text = convertView.findViewById(R.id.comment_text);
        TextView comment_rating = convertView.findViewById(R.id.comment_rating);

        final ImageView btnReport = convertView.findViewById(R.id.btnReport);

        String user_name = getUserById(position);

        comment_user.setText(user_name);

        comment_text.setText(comments.get(position).getComment());

        comment_rating.setText(String.format(Locale.UK, "%1d", comments.get(position).getRating()));

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return convertView;
    }

    private String getUserById(int position) {

        final User[] tmp = new User[1];

        try {
            UserEndpoints services = retrofit.create(UserEndpoints.class);
            Call<User> call = services.getUserById(comments.get(position).getCommenter_id());

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        tmp[0] = response.body();
                    } else {
                        isBad(context, response);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    isFailure(context, t);
                }
            });
        } catch (Exception e) {
            isException(context, e);
        }

        return tmp[0].getName();
    }
}
