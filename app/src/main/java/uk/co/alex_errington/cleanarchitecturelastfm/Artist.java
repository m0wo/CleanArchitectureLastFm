package uk.co.alex_errington.cleanarchitecturelastfm;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Artist implements Parcelable {

    @Json(name = "mbid")
    private String mbid;
    @Json(name = "name")
    private String name;
    @Json(name = "playcount")
    private long playCount;
    @Json(name = "listeners")
    private long listeners;
    @Json(name = "url")
    private String url;
    @Json(name = "streamable")
    private String streamable;
    @Json(name = "image")
    private Image[] image;

    public Artist()
    {

    }

    protected Artist(Parcel in)
    {
        mbid = in.readString();
        name = in.readString();
        playCount = in.readLong();
        listeners = in.readLong();
        url = in.readString();
        streamable = in.readString();
        image = (Image[]) in.readArray(Image.class.getClassLoader());
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel parcel) {
            return new Artist(parcel);
        }

        @Override
        public Artist[] newArray(int i) {
            return new Artist[i];
        }
    };

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public long getListeners() {
        return listeners;
    }

    public void setListeners(long listeners) {
        this.listeners = listeners;
    }

    public long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(long playCount) {
        this.playCount = playCount;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Image[] getImage() {
        return image;
    }

    public void setImage(Image[] image) {
        this.image = image;
    }

    public String getLargeImageUrl() {
        for(Image image : this.image) {
            if(image.size.equals("extralarge"))
                return image.text;
        }
        //default if the string doesn't exist
        return image[0].text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mbid);
        parcel.writeLong(listeners);
        parcel.writeLong(playCount);
        parcel.writeString(streamable);
    }
}

class Image implements Parcelable{

    @Json(name="#text")
    public String text;

    @Json(name="size")
    public String size;


    public Image()
    {

    }

    protected Image(Parcel in)
    {
        text = in.readString();
        size = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
        parcel.writeString(size);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
