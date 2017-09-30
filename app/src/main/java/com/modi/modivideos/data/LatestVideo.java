package com.modi.modivideos.data;

/**
 * Created by Avilash on 30-09-2017.
 */

public class LatestVideo {

    private String vid_id , vid_pic , vid_dsc;

    public LatestVideo(String vid_id, String vid_pic, String vid_dsc) {
        this.vid_id = vid_id;
        this.vid_pic = vid_pic;
        this.vid_dsc = vid_dsc;
    }

    public String getVid_id() {
        return vid_id;
    }

    public void setVid_id(String vid_id) {
        this.vid_id = vid_id;
    }

    public String getVid_pic() {
        return vid_pic;
    }

    public void setVid_pic(String vid_pic) {
        this.vid_pic = vid_pic;
    }

    public String getVid_dsc() {
        return vid_dsc;
    }

    public void setVid_dsc(String vid_dsc) {
        this.vid_dsc = vid_dsc;
    }
}
