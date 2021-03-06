package uk.co.alex_errington.cleanarchitecturelastfm.listing;

import java.util.List;

import io.reactivex.Observable;
import uk.co.alex_errington.cleanarchitecturelastfm.Artist;
import uk.co.alex_errington.cleanarchitecturelastfm.ArtistsWrapper;
import uk.co.alex_errington.cleanarchitecturelastfm.network.LastFmWebService;

class ArtistsListingInteractorImpl implements ArtistsListingInteractor {

    private LastFmWebService lastFmWebService;

    public ArtistsListingInteractorImpl(LastFmWebService lastFmWebService) {
        this.lastFmWebService = lastFmWebService;
    }

    @Override
    public boolean isPaginationSupported() {
        return false;
    }

    @Override
    public Observable<List<Artist>> fetchArtists(int page) {
        return lastFmWebService.chartedArtists(page).map(ArtistsWrapper::getArtistList);
    }
}
