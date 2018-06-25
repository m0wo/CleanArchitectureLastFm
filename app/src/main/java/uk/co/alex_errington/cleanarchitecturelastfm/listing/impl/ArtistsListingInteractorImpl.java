package uk.co.alex_errington.cleanarchitecturelastfm.listing.impl;

import java.util.List;

import io.reactivex.Observable;
import uk.co.alex_errington.cleanarchitecturelastfm.Artist;
import uk.co.alex_errington.cleanarchitecturelastfm.ArtistsWrapper;
import uk.co.alex_errington.cleanarchitecturelastfm.listing.ArtistsListingInteractor;
import uk.co.alex_errington.cleanarchitecturelastfm.network.LastFmWebService;

public class ArtistsListingInteractorImpl implements ArtistsListingInteractor {

    private LastFmWebService lastFmWebService;

    ArtistsListingInteractorImpl(LastFmWebService lastFmWebService) {
        this.lastFmWebService = lastFmWebService;
    }

    @Override
    public boolean isPaginationSupported() {
        return false;
    }

    @Override
    public Observable<List<Artist>> fetchArtists(int page) {
        return lastFmWebService.chartedArtists(page).map(ArtistsWrapper::getArtists);
    }
}
