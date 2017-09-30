package com.modi.modivideos.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.modi.modivideos.Config;
import com.modi.modivideos.Constants;
import com.modi.modivideos.R;
import com.modi.modivideos.YoutubeActivity;
import com.modi.modivideos.adapters.VideoAdapter;
import com.modi.modivideos.callback.BaseInterface;
import com.modi.modivideos.callback.RecyclerViewOnClickInterface;
import com.modi.modivideos.data.LatestVideo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LatestVideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LatestVideoFragment extends Fragment implements RecyclerViewOnClickInterface {

    private Context context;

    private BaseInterface activityListener;
    private RecyclerViewOnClickInterface mListener;
    private DatabaseReference mDatabase;

    private RecyclerView rv_videos;

    private List<LatestVideo> video_list;
    private List<String> video_id_list;
    private VideoAdapter video_adapter;
    private LinearLayoutManager layout_manager;

    public LatestVideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LatestVideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LatestVideoFragment newInstance() {
        LatestVideoFragment fragment = new LatestVideoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_latest_video, container, false);
        initValues();
        initUI(v);
        initUIActions();
        loadData();
        return v;
    }

    public void initValues() {
        context = getActivity();
        mListener = this;
        video_id_list = new ArrayList<>();
        video_list = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void initUI(View v) {
        rv_videos = (RecyclerView) v.findViewById(R.id.rv_videos);
    }

    public void initUIActions() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rv_videos.setHasFixedSize(true);

        // use a linear layout manager
        layout_manager = new LinearLayoutManager(context);
        rv_videos.setLayoutManager(layout_manager);

        // specify an adapter (see also next example)
        video_adapter = new VideoAdapter(video_list , context , mListener);
        rv_videos.setAdapter(video_adapter);
    }

    public void loadData() {
        mDatabase = mDatabase.child("latest");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                video_id_list.clear();
                Iterable<DataSnapshot> videos_snap = dataSnapshot.getChildren();
                for (DataSnapshot video_snap : videos_snap) {
                    video_id_list.add(video_snap.getValue().toString());
                }
                fetchDataFromYoutube();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void fetchDataFromYoutube() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://www.googleapis.com/youtube/v3/videos?";
        url += "part=snippet&";
        url += "id=" + android.text.TextUtils.join(",", video_id_list) + "&";
        url += "key=" + Config.DEVELOPER_KEY;
        Log.e("Request", url);
        // Request a JSON response from the provided URL
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray items = response.getJSONArray("items");
                        video_list.clear();
                        for(int i=0;i<items.length();i++) {
                            String id = items.getJSONObject(i).getString("id");
                            JSONObject snippet = items.getJSONObject(i).getJSONObject("snippet");
                            String title = snippet.getString("title");
                            String pic = snippet.getJSONObject("thumbnails").getJSONObject("default")
                                    .getString("url");
                            LatestVideo video = new LatestVideo(id , pic , title);
                            video_list.add(video);
                        }
                        video_adapter.notifyDataSetChanged();
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO Auto-generated method stub

                }
            });

        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseInterface) {
            activityListener = (BaseInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BaseInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private class FetchYoutubeVideosTask extends AsyncTask<String, Integer, JSONObject> {

        protected JSONObject doInBackground(String... params) {
            return new JSONObject();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
        }
    }

    @Override
    public void onRVItemClick(int position) {
        LatestVideo video = video_list.get(position);
        String id = video.getVid_id();
        Intent youtubeActivity = new Intent(context , YoutubeActivity.class);
        youtubeActivity.putExtra(Constants.IntentExtras.VID_ID , id);
        startActivity(youtubeActivity);
    }

}
