package uk.co.alex_errington.cleanarchitecturelastfm.listing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import uk.co.alex_errington.cleanarchitecturelastfm.Artist;
import uk.co.alex_errington.cleanarchitecturelastfm.R;

public class ArtistsListingAdapter extends RecyclerView.Adapter<ArtistsListingAdapter.ViewHolder>{

    private List<Artist> artists;
    private Context context;
    private ArtistsListingView view;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public Artist artist;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            ArtistsListingAdapter.this.view.onArtistClicked(artist);
        }
    }

    public ArtistsListingAdapter(List<Artist> artists, ArtistsListingView artistsView) {
        this.artists = artists;
        this.view = artistsView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.artist_grid_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.artist = artists.get(position);
        //glide image setup goes here
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }
}
