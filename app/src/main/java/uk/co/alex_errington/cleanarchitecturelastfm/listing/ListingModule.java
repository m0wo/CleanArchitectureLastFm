package uk.co.alex_errington.cleanarchitecturelastfm.listing;

import dagger.Module;
import dagger.Provides;
import uk.co.alex_errington.cleanarchitecturelastfm.network.LastFmWebService;

@Module
public class ListingModule {

    @Provides
    ArtistsListingInteractor provideArtistListingInteractor(LastFmWebService lastFmWebService) {
        return new ArtistsListingInteractorImpl(lastFmWebService);
    }

    @Provides
    ArtistsListingPresenter provideArtistListingPresenter(ArtistsListingInteractor interactor) {
        return new ArtistsListingPresenterImpl(interactor);
    }
}
