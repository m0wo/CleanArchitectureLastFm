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
    private boolean streamable;

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
        streamable = in.readByte() != 0;
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

    public boolean getStreamable() {
        return streamable;
    }

    public void setStreamable(boolean streamable) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mbid);
        parcel.writeLong(listeners);
        parcel.writeLong(playCount);
        parcel.writeByte((byte) (streamable ? 1 : 0));
    }
}
