package vn.edu.iuh.fit.rayarkshop.models.mappers;


import vn.edu.iuh.fit.rayarkshop.models.SalesOrder;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrderDetail;
import vn.edu.iuh.fit.rayarkshop.models.ShoppingCartItem;

public class SalesOrderDetailMapper {

    public static SalesOrderDetail toSalesOrderDetail(SalesOrder salesOrder, ShoppingCartItem shoppingCartItem) {
        SalesOrderDetail salesOrderDetail = new SalesOrderDetail(
                salesOrder,
                shoppingCartItem.getProduct(),
                shoppingCartItem.getProductVariation(),
                shoppingCartItem.getQuantity(),
                shoppingCartItem.getProduct().getPrice()
        );
        return salesOrderDetail;
    }

}
