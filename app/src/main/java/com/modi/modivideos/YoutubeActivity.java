package com.modi.modivideos;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private YouTubePlayerView youTubePlayerView;
    public String vid_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        initValues();
        initUI();
        initUIActions();
    }

    public void initValues() {
        if(getIntent().hasExtra(Constants.IntentExtras.VID_ID)){
            vid_id = getIntent().getStringExtra(Constants.IntentExtras.VID_ID);
        }else {
            vid_id = "";
        }
    }

    public void initUI() {
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_player_view);
    }

    public void initUIActions() {
        youTubePlayerView.initialize(Config.DEVELOPER_KEY , this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(null == youTubePlayer) return;

        // Start buffering
        if (!b) {
            //youTubePlayer.cueVideo(vid_id);
            youTubePlayer.loadVideo(vid_id);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
