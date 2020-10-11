package com.example.Ideal_play_app.DatabaseClasses;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Ideal_play_app.Users;
import com.example.Ideal_play_app.R;

public class SessionData {
    public static Users currentUser = new Users("Kevin", "password", 1);
    public static UserDatabase mUserDatabase;
    public static BadgeDatabase mBadgeDatabase;
    public static UsrBadgesDatabase mUsrBadgesDatabase;
    public static String selectedRole = "DEFAULT";

    public static void refreshSessionStats() {

    }

    public static void displayBadge(Context context, int badgeId) {
        ImageView badgeImage;
        TextView badgeTitle;
        TextView badgeDescription;
        Button popupBack;

        final Dialog mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.popup_badge_earned);

        badgeImage = (ImageView) mDialog.findViewById(R.id.badge_image);
        badgeTitle = (TextView) mDialog.findViewById(R.id.badge_title);
        badgeDescription = (TextView) mDialog.findViewById(R.id.badge_description);
        popupBack = (Button) mDialog.findViewById(R.id.popup_back);

        popupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        badgeImage.setImageResource(ImageBank.badges[mBadgeDatabase.mBadgeDao().fetchBadgeByID(badgeId).getIcon()]);
        badgeTitle.setText(mBadgeDatabase.mBadgeDao().fetchBadgeByID(badgeId).getName());
        badgeDescription.setText(mBadgeDatabase.mBadgeDao().fetchBadgeByID(badgeId).getDescription());

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mDialog.show();
    }
    
    public static void createDB(Context context){

         mUserDatabase = Room.databaseBuilder(context.getApplicationContext(),
                UserDatabase.class, "user_db").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

         mBadgeDatabase = Room.databaseBuilder(context.getApplicationContext(),
                BadgeDatabase.class, "badge_db").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
         mUsrBadgesDatabase = Room.databaseBuilder(context.getApplicationContext(),
                UsrBadgesDatabase.class, "usrbadge_db").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    };


}

