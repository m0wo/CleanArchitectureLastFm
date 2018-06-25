package uk.co.alex_errington.cleanarchitecturelastfm;

import com.squareup.moshi.Json;

import java.util.List;

public class ArtistsWrapper {

    @Json(name="artists")
    private ArtistWrapper artists;

    public List<Artist> getArtistList() {
        return artists.getArtistList();
    }

    public void setArtistList(List<Artist> artists) {
        this.artists.setArtistList(artists);
    }
}

class ArtistWrapper {

    @Json(name="artist")
    private List<Artist> artists;

    public List<Artist> getArtistList() {
        return artists;
    }

    public void setArtistList(List<Artist> artists) {
        this.artists = artists;
    }
}