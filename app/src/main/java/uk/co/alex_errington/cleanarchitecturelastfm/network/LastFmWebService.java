package uk.co.alex_errington.cleanarchitecturelastfm.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.co.alex_errington.cleanarchitecturelastfm.ArtistsWrapper;

public interface LastFmWebService {
    @GET("http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&format=json")
    Observable<ArtistsWrapper> chartedArtists(@Query("page") int page);
}
