package vn.edu.iuh.fit.rayarkshop.utils;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vn.edu.iuh.fit.rayarkshop.models.District;
import vn.edu.iuh.fit.rayarkshop.models.Province;
import vn.edu.iuh.fit.rayarkshop.models.Ward;

import java.util.List;
import java.util.Map;

@Service
public interface VnProvincesApiService {

    @POST("graphql")
    Call<Map<String, Object>> executeGraphQLRequest(@Body VnProvincesGraphQLRequest request);

    @GET("api/provinces/getAll")
    Call<List<Province>> getAllProvinces();

    @GET("api/provinces/getById")
    Call<Province> getProvinceById(@Query("id") String provinceId);

    @GET("api/districts/getById")
    Call<District> getDistrictById(@Query("id") String districtId);

    @GET("api/districts/getByProvince")
    Call<List<District>> getDistrictByProvince(@Query("provinceId") String provinceId);

    @GET("api/wards/getById")
    Call<Ward> getWardById(@Query("id") String wardId);

    @GET("api/wards/getByDistrict")
    Call<List<Ward>> getWardsByDistrict(@Query("districtId") String districtId);

}
