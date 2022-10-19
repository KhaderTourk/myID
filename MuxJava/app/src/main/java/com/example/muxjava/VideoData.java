package com.example.muxjava;

import com.google.android.exoplayer2.MediaItem;

public class VideoData {
    private int id;
    private String streamUrl;

    public VideoData(int id, String streamUrl) {
        this.id = id;
        this.streamUrl = streamUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }
}