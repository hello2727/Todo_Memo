package org.jh.todomemo;

public class PictureConstructure {
    int picture;
    String ptitle;
    String pcontents;

    public PictureConstructure(int picture, String ptitle, String pcontents) {
        this.picture = picture;
        this.ptitle = ptitle;
        this.pcontents = pcontents;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPcontents() {
        return pcontents;
    }

    public void setPcontents(String pcontents) {
        this.pcontents = pcontents;
    }
}
