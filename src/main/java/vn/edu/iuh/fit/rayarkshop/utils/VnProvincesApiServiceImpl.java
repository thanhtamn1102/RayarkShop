package vn.edu.iuh.fit.rayarkshop.utils;

import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.edu.iuh.fit.rayarkshop.models.District;
import vn.edu.iuh.fit.rayarkshop.models.Province;
import vn.edu.iuh.fit.rayarkshop.models.Ward;

import java.util.List;
import java.util.Map;

@Component
public class VnProvincesApiServiceImpl implements VnProvincesApiService {

    private VnProvincesApiService vnProvincesApiService;

    public VnProvincesApiServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vn-provinces-api-production.up.railway.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        vnProvincesApiService = retrofit.create(VnProvincesApiService.class);
    }

    @Override
    public Call<Map<String, Object>> executeGraphQLRequest(VnProvincesGraphQLRequest request) {
        return vnProvincesApiService.executeGraphQLRequest(request);
    }

    @Override
    public Call<List<Province>> getAllProvinces() {
        return vnProvincesApiService.getAllProvinces();
    }

    @Override
    public Call<Province> getProvinceById(String provinceId) {
        return vnProvincesApiService.getProvinceById(provinceId);
    }

    @Override
    public Call<District> getDistrictById(String districtId) {
        return vnProvincesApiService.getDistrictById(districtId);
    }

    @Override
    public Call<List<District>> getDistrictByProvince(String provinceId) {
        return vnProvincesApiService.getDistrictByProvince(provinceId);
    }

    @Override
    public Call<Ward> getWardById(String wardId) {
        return vnProvincesApiService.getWardById(wardId);
    }

    @Override
    public Call<List<Ward>> getWardsByDistrict(String districtId) {
        return vnProvincesApiService.getWardsByDistrict(districtId);
    }
}
