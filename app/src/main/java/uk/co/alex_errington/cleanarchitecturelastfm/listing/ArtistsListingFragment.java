package uk.co.alex_errington.cleanarchitecturelastfm.listing;


import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.alex_errington.cleanarchitecturelastfm.Artist;
import uk.co.alex_errington.cleanarchitecturelastfm.BaseApplication;
import uk.co.alex_errington.cleanarchitecturelastfm.Constants;
import uk.co.alex_errington.cleanarchitecturelastfm.R;

public class ArtistsListingFragment extends Fragment implements ArtistsListingView {

    @Inject
    ArtistsListingPresenter artistsPresenter;

    @BindView(R.id.artists_listing)
    RecyclerView artistsListing;

    private RecyclerView.Adapter adapter;
    private List<Artist> artists = new ArrayList<>(20);
    private Callback callback;
    private Unbinder unbinder;

    public ArtistsListingFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplication())
                .createListingComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_artists, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initLayoutReferences();
        artistsListing.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!recyclerView.canScrollVertically(1)) {
                    artistsPresenter.nextPage();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        artistsPresenter.setView(this);
        if(savedInstanceState != null) {
            artists = savedInstanceState.getParcelableArrayList(Constants.ARTIST);
            adapter.notifyDataSetChanged();
            artistsListing.setVisibility(View.VISIBLE);
        } else {
            artistsPresenter.firstPage();
        }
    }

    private void initLayoutReferences() {
        artistsListing.setHasFixedSize(true);

        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 2;
        } else {
            columns = getResources().getInteger(R.integer.no_of_columns);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), columns);

        artistsListing.setLayoutManager(layoutManager);
        adapter = new ArtistsListingAdapter(artists, this);
        artistsListing.setAdapter(adapter);
    }

    @Override
    public void showArtists(List<Artist> artists) {
        this.artists.clear();
        this.artists.addAll(artists);
        artistsListing.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        callback.onArtistsLoaded(artists.get(0));
    }

    @Override
    public void loadingStarted() {
        Snackbar.make(artistsListing, "Loading Artists",
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadingFailed(String errorMessage) {
        Snackbar.make(artistsListing, errorMessage,
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onArtistClicked(Artist artist) {
        callback.onArtistClicked(artist);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        artistsPresenter.destroy();
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication())
                .releaseListingComponent();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.ARTIST,
                (ArrayList<? extends Parcelable>) artists);
    }

    public interface Callback {
        void onArtistsLoaded(Artist artist);
        void onArtistClicked(Artist artist);
    }


}
