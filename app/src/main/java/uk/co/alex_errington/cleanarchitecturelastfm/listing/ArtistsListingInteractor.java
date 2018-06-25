package uk.co.alex_errington.cleanarchitecturelastfm.listing;

import java.util.List;

import io.reactivex.Observable;
import uk.co.alex_errington.cleanarchitecturelastfm.Artist;

public interface ArtistsListingInteractor {
    boolean isPaginationSupported();
    Observable<List<Artist>> fetchArtists(int page);
}
