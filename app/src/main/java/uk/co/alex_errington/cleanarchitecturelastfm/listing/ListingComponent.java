package uk.co.alex_errington.cleanarchitecturelastfm.listing;


import dagger.Subcomponent;

@ListingScope
@Subcomponent(modules = {ListingModule.class})
public interface ListingComponent {
    ArtistsListingFragment inject(ArtistsListingFragment fragment);
}
