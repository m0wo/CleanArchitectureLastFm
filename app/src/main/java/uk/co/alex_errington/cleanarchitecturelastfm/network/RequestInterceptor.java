package uk.co.alex_errington.cleanarchitecturelastfm.network;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import uk.co.alex_errington.cleanarchitecturelastfm.BuildConfig;

public class RequestInterceptor implements Interceptor {

    @Inject
    public RequestInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.LASTFM_API_KEY)
                .build();

        Request request = original.newBuilder().url(url).build();
        return chain.proceed(request);
    }

}
