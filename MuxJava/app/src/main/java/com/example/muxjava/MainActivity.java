package com.example.muxjava;

import static com.google.android.exoplayer2.Player.REPEAT_MODE_OFF;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.cache.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Long CACHE_SIZE = 50 * 1024 * 1024L;
    public static String TAG = "MainActivity";
    private Cache cache;
    private DefaultDataSourceFactory upstreamDataSourceFactory;
    private CacheDataSource.Factory cacheDataSourceFactory;
    private SimpleExoPlayer player;

    public Cache getCache(){
        File exoCacheDir = new File("${cacheDir.absolutePath}/exo");
        LeastRecentlyUsedCacheEvictor evictor = new LeastRecentlyUsedCacheEvictor(CACHE_SIZE);
        if (cache == null) {
            cache = new SimpleCache(exoCacheDir, evictor, new ExoDatabaseProvider(this));
        }
        return cache;
    }

    public DefaultDataSourceFactory getUpstreamDataSourceFactory(){
        if (upstreamDataSourceFactory == null) {
            upstreamDataSourceFactory = new DefaultDataSourceFactory(this, "Android");
        }
        return upstreamDataSourceFactory;
    }
   public CacheDataSource.Factory getCacheDataSourceFactory(){
       CacheDataSink.Factory dataSink = new CacheDataSink.Factory().setCache(cache).setFragmentSize(CacheDataSink.DEFAULT_FRAGMENT_SIZE);
       if (cacheDataSourceFactory == null){
             cacheDataSourceFactory = new CacheDataSource
                    .Factory()
                    .setCache(cache)
                    .setUpstreamDataSourceFactory(upstreamDataSourceFactory)
                    .setCacheReadDataSourceFactory(new FileDataSource.Factory())
                    .setCacheWriteDataSinkFactory(dataSink)
                    .setFlags(CacheDataSource.FLAG_BLOCK_ON_CACHE | CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
                    .setEventListener(new CacheDataSource.EventListener() {
                        @Override
                        public void onCacheIgnored(int reason) {Log.e(TAG, "onCacheIgnored");}
                        @Override
                        public void onCachedBytesRead(long cacheSizeBytes, long cachedBytesRead) {Log.e(TAG, "onCachedBytesRead , cacheSizeBytes: " + cacheSizeBytes + "   cachedBytesRead: " + cachedBytesRead);}
                    });
        }
        return cacheDataSourceFactory;
   }

   public SimpleExoPlayer getPlayer(){
        if (player == null){
            player = new SimpleExoPlayer.Builder(this)
                    .build();
        }
        return player;
   }


    MutableLiveData<Integer> pagerLastItem = new MutableLiveData<>(0);

    int i = -1;
    List<VideoData> videoDatas = Collections.unmodifiableList(
            Arrays.asList(
                    new VideoData(i++, "https://stream.mux.com/90002Rl36NP4a4U00EagLJOw02LqXfxL2ZQMtOMnTu5BQCo.m3u8"),
                    new VideoData(i++, "https://stream.mux.com/nvGI9AIQ7SlpSB01ZCQN25q9WmGAHA35aqtnYKwcJaIg.m3u8"),
                    new VideoData(i++, "https://stream.mux.com/pA01Qxuph01ASrXDWjCW50059a54q02cbA73zG1JWlDO3Qs.m3u8"),
                    new VideoData(i++, "https://stream.mux.com/SqUxmcUgROL53TzLOwsHUcLVOmDbpGS00ZzlsoupCVok.m3u8")
            ));

private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player.setRepeatMode(REPEAT_MODE_OFF);
        player.setPlayWhenReady(true);
        viewPager = findViewById(R.id.viewPager);

        VideoPagerAdapter videoPagerAdapter = new VideoPagerAdapter(this);
       videoPagerAdapter.showDetails = this.videoDatas;
        viewPager.setAdapter(videoPagerAdapter);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setOffscreenPageLimit(1);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager2.SCROLL_STATE_IDLE){
                    if (viewPager.getCurrentItem() != pagerLastItem.getValue()){
                        pagerLastItem.setValue(viewPager.getCurrentItem());
                    }
                    player.setPlayWhenReady(true);
                }else {
                    player.setPlayWhenReady(false);
                }
            }
        });



    }

    @Override
    protected void onDestroy() {
        player.release();
        super.onDestroy();
    }

}