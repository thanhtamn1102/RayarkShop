package vn.edu.iuh.fit.rayarkshop.models;

public enum ProductStatus {

    DANG_BAN,
    DA_AN,
    NGUNG_KINH_DOANH;

    @Override
    public String toString() {
        switch (this) {
            case DANG_BAN:
                return "Đang bán";
            case NGUNG_KINH_DOANH:
                return "Ngừng kinh doanh";
            case DA_AN:
                return "Đã ẩn";
            default:
                return super.toString();
        }
    }
}
