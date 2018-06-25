package uk.co.alex_errington.cleanarchitecturelastfm.listing;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.alex_errington.cleanarchitecturelastfm.Artist;
import uk.co.alex_errington.cleanarchitecturelastfm.R;

public class ArtistsListingAdapter extends RecyclerView.Adapter<ArtistsListingAdapter.ViewHolder>{

    private List<Artist> artists;
    private Context context;
    private ArtistsListingView view;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.artist_background)
        View artistBackground;
        @BindView(R.id.artist_name)
        TextView name;
        @BindView(R.id.artist_image)
        ImageView image;

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
        holder.name.setText(holder.artist.getName());
        //glide image setup goes here


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .asBitmap()
                .load(holder.artist.getLargeImageUrl())
                .apply(options)
                .into(new BitmapImageViewTarget(holder.image) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(bitmap, transition);
                        Palette.from(bitmap).generate(palette -> setBackgroundColor(palette, holder));
                    }
                });
    }

    private void setBackgroundColor(Palette palette, ViewHolder holder) {
        holder.artistBackground.setBackgroundColor(palette.getVibrantColor(context
                .getResources().getColor(R.color.black_translucent_60)));
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }
}
