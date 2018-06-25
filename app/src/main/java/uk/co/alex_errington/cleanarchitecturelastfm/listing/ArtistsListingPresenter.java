package uk.co.alex_errington.cleanarchitecturelastfm.listing;

public interface ArtistsListingPresenter {
    void firstPage();

    void nextPage();

    void setView(ArtistsListingView view);

    void destroy();
}
