package uk.co.alex_errington.cleanarchitecturelastfm.listing;

import java.util.List;

import uk.co.alex_errington.cleanarchitecturelastfm.Artist;

public interface ArtistsListingView {
    void showArtists(List<Artist> artists);
    void loadingStarted();
    void loadingFailed(String errorMessage);
    void onArtistClicked(Artist artist);
}
