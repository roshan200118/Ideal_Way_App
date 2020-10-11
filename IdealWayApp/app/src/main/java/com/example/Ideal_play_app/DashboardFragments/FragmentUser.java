package com.example.Ideal_play_app.DashboardFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Ideal_play_app.Badges;
import com.example.Ideal_play_app.UsrBadges;
import com.example.Ideal_play_app.DatabaseClasses.SessionData;
import com.example.Ideal_play_app.R;
import com.example.Ideal_play_app.RecyclerViewAdapters.BadgeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentUser extends Fragment {
    View view;

    private RecyclerView badgeRecyclerView;
    private List<Badges> mBadges;


    public FragmentUser() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getUserBadges();
        view = inflater.inflate(R.layout.user_fragment, container, false);
        badgeRecyclerView = (RecyclerView) view.findViewById(R.id.badges_recyclerView);
        BadgeRecyclerViewAdapter recyclerViewAdapter = new BadgeRecyclerViewAdapter(getContext(), mBadges);
        badgeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        badgeRecyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    private void getUserBadges() {
        List<UsrBadges> userBadges = SessionData.mUsrBadgesDatabase.mUsrBadgesDao().getAllBadgesByUser(SessionData.currentUser.getUserName());
        mBadges = new ArrayList<Badges>();
        for (int i = 0; i < userBadges.size(); i++) {
            mBadges.add(SessionData.mBadgeDatabase.mBadgeDao().fetchBadgeByID(userBadges.get(i).getBadgeID()));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
