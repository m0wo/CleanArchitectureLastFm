package uk.co.alex_errington.cleanarchitecturelastfm.listing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import uk.co.alex_errington.cleanarchitecturelastfm.Artist;
import uk.co.alex_errington.cleanarchitecturelastfm.R;

public class ArtistsListingActivity extends AppCompatActivity implements ArtistsListingFragment.Callback{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.music_guide);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public void onArtistsLoaded(Artist artist) {

    }

    @Override
    public void onArtistClicked(Artist artist) {

    }

    private void loadArtistFragment(Artist artist) {

    }
}
