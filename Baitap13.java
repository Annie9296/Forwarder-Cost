
abstract class CostOfGoodsSold{
    String providerName;
    enum ProviderType {
        SUPPLIER, BROKER, WAREHOUSE
    };
    String shipmentId;
    double shipmentAmount;
    enum SupplierFeeType {
        GoodsCost, PalletFee, Sorting
    };
    enum BrokerFeeType {
        Loading, Discharge, Unloading
    };
    enum WarehouseFeeType {
        Storage, Handling, Sorting
    };
    
}

public class Baitap13 {
    
}
