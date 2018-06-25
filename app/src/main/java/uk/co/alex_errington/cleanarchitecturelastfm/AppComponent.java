package uk.co.alex_errington.cleanarchitecturelastfm;

import javax.inject.Singleton;

import dagger.Component;
import uk.co.alex_errington.cleanarchitecturelastfm.listing.ListingComponent;
import uk.co.alex_errington.cleanarchitecturelastfm.listing.ListingModule;
import uk.co.alex_errington.cleanarchitecturelastfm.network.NetworkModule;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class})
public interface AppComponent
{
    ListingComponent plus(ListingModule listingModule);
}
