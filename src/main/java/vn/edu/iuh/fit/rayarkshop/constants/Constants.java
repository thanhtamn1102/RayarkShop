package vn.edu.iuh.fit.rayarkshop.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    @Value("${const.expected_profit}")
    public static double EXPECTED_PROFIT;

}
