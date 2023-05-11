package vn.edu.iuh.fit.rayarkshop.utils;

import com.google.gson.annotations.SerializedName;
import vn.edu.iuh.fit.rayarkshop.models.District;
import vn.edu.iuh.fit.rayarkshop.models.Province;
import vn.edu.iuh.fit.rayarkshop.models.Ward;

public class VnProvincesGraphQLResponse {

    @SerializedName("getProvinceById")
    private Province province;

    @SerializedName("getDistrictById")
    private District district;

    @SerializedName("getWardById")
    private Ward ward;

}
