package uk.co.alex_errington.cleanarchitecturelastfm.listing.impl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.alex_errington.cleanarchitecturelastfm.Artist;
import uk.co.alex_errington.cleanarchitecturelastfm.listing.ArtistsListingInteractor;
import uk.co.alex_errington.cleanarchitecturelastfm.listing.ArtistsListingPresenter;
import uk.co.alex_errington.cleanarchitecturelastfm.listing.ArtistsListingView;
import uk.co.alex_errington.cleanarchitecturelastfm.util.RxUtils;

public class ArtistsListingPresenterImpl implements ArtistsListingPresenter {
    private ArtistsListingView view;
    private ArtistsListingInteractor artistsInteractor;
    private Disposable fetchSubscription;
    private int currentPage = 1;
    private List<Artist> loadedArtists = new ArrayList<>();

    ArtistsListingPresenterImpl(ArtistsListingInteractor interactor) {
        artistsInteractor = interactor;
    }

    @Override
    public void setView(ArtistsListingView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(fetchSubscription);
    }

    private void displayArtists() {
        showLoading();
        fetchSubscription = artistsInteractor.fetchArtists(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onArtistFetchSuccess, this::onAristFetchFailed);
    }

    @Override
    public void firstPage() {

    }

    @Override
    public void nextPage() {

    }

    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    private void onArtistFetchSuccess(List<Artist> artists) {
        if (artistsInteractor.isPaginationSupported()) {
            loadedArtists.addAll(artists);
        } else {
            loadedArtists = new ArrayList<>(artists);
        }
        if(isViewAttached()) {
            view.showArtists(loadedArtists);
        }
    }

    private void onAristFetchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
    }

    private boolean isViewAttached() {
        return view != null;
    }



}
