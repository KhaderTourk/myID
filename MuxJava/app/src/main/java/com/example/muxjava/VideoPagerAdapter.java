package com.example.muxjava;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoPagerAdapter extends FragmentStateAdapter {
    AppCompatActivity activity;
    List<VideoData> showDetails;

    public VideoPagerAdapter(AppCompatActivity activity){
        super(activity);
        this.showDetails = new ArrayList<>();
        this.activity = activity;
    }

    public List<VideoData> getShowDetails() {
        return showDetails;
    }

    public void setShowDetails(List<VideoData> showDetails) {
        this.showDetails = showDetails;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("KEY_POSITION" , position);
        VideoFragment videoFragment =  new VideoFragment();
        videoFragment.setArguments(bundle);
        return videoFragment;
    }

    @Override
    public int getItemCount() {
        return showDetails.size();
    }
}
