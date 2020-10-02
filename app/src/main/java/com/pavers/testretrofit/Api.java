package com.pavers.testretrofit;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("getUpdatedProduct") // used for updating the product location and available qty when displaying the product
    Observable<Response<TokenResponse>> getUpdatedProduct(
            @Query("posCode") String posCode,
            @Query("colourExpected") boolean getColourExpected
    );

}
