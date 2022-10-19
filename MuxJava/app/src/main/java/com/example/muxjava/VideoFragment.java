package com.example.muxjava;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.hls.offline.HlsDownloader;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class VideoFragment extends Fragment {
    private final MainActivity mainActivity = new MainActivity();
    private final SimpleExoPlayer player = mainActivity.getPlayer();
    private MediaItem uri;
    private MediaSource mediaSource;
    private DownloadService downloadConstructorHelper;
    private HlsDownloader downloader;

    int position = -2;

    public MediaItem getUri(){
        if (uri == null){
            uri = MediaItem.fromUri(mainActivity.videoDatas.get(position).getStreamUrl());
        }
        return uri;
    }


    List<StreamKey> cacheStreamKeys = Collections.unmodifiableList(
            Arrays.asList(
                    new StreamKey(0,1),
                    new StreamKey(1, 1),
                    new StreamKey(2, 1),
                    new StreamKey(3, 1),
                    new StreamKey(4, 1)
            ));

    public MediaSource getMediaSource(){
        CacheDataSource.Factory dataSourceFactory = mainActivity.getCacheDataSourceFactory();
        if (mediaSource == null){
            mediaSource =
                    new HlsMediaSource
                            .Factory(dataSourceFactory)
                            .setAllowChunklessPreparation(true)
                            .createMediaSource(uri);
        }
        return mediaSource;
    }





  String KEY_POSITION = "KEY_POSITION";
    Long PRE_CACHE_SIZE = 5 * 1024 * 1024L;
    String TAG = "VideoFragment";

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            if (getArguments().getInt(KEY_POSITION, -1) > 0) {
                position = getArguments().getInt(KEY_POSITION);
            }else {
                position = -2;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       //mainActivity.pagerLastItem.setValue();
        return inflater.inflate(R.layout.fragment_video, container, false);

    }

    public DownloadService getDownloadConstructorHelper(){
        if (downloadConstructorHelper == null) {
        downloadConstructorHelper = new DownloaderConstructorHelper(
                mainActivity.getCache(),
                mainActivity.getUpstreamDataSourceFactory(),
                mainActivity.getCacheDataSourceFactory(),
                null,
                null);
        }
        return downloadConstructorHelper;
    }
    public HlsDownloader getDownloader(){
        if (downloader == null){
            downloader = new  HlsDownloader(uri, cacheStreamKeys, downloadConstructorHelper);
        }
        return downloader;
    }

    private void  cancelPreCache() {
        downloader.cancel();
    }
}
