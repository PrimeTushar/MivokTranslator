package com.wichitra.mivoktab;

public class itemModel {


    private String Mivok="";
    private String eng="";
    private int resid=NO_IMAGE_PROVIDED;
    private int songid;

    private static final int NO_IMAGE_PROVIDED=-1;

    public itemModel(String eng,String Mivok,int songid)
    {
        this.Mivok=Mivok;
        this.eng=eng;
        this.songid=songid;

    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public itemModel(String eng, String Mivok ,int resid, int songid)
    {
        this.Mivok=Mivok;
        this.eng=eng;
        this.resid=resid;
        this.songid=songid;
    }
    public String getMivok() {
        return Mivok;
    }

    public void setMivok(String mivok) {
        Mivok = mivok;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }
    boolean hasImage()
    {
        return resid!=NO_IMAGE_PROVIDED;
    }
}
