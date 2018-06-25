package uk.co.alex_errington.cleanarchitecturelastfm;

import com.squareup.moshi.Json;

import java.util.List;

public class ArtistsWrapper {

    @Json(name="artists")
    private List<Artist> artists;

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
