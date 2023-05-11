package vn.edu.iuh.fit.rayarkshop.models;

public enum OrderStatus {

    CHO_XAC_NHAN,
    DANG_XU_LY,
    DANG_GIAO,
    DA_GIAO,
    DA_HOAN_THANH,
    DA_HUY;

    @Override
    public String toString() {
        switch (this) {
            case CHO_XAC_NHAN:
                return "Chờ xác nhận";
            case DANG_XU_LY:
                return "Đang xử lý";
            case DANG_GIAO:
                return "Đang giao";
            case DA_GIAO:
                return "Đã giao";
            case DA_HOAN_THANH:
                return "Đã hoàn thành";
            case DA_HUY:
                return "Đã hủy";
            default:
                return super.toString();
        }
    }
}
