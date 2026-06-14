import java.time.LocalDate;
import java.util.ArrayList;


// Class for Purchase Order WH detail standard
class WHPO {
    int demandId;
    String sku;
    String vendorCode;
    int orderQty;
    double unitCost;
    double amount;
    String poNumber;
    String countryCode;
    LocalDate shipDate;
    double chargeShip;

// Constructor and Method field for purchase order detail included with ful-fill data by user
public WHPO(
      int demandId,
    String sku,
    String vendorCode,
    int orderQty,
    double unitCost,
    String countryCode,
    LocalDate shipDate)
    
    {
        // Validation each field of PO
     if(sku.length()>4){
        throw new IllegalArgumentException("SKU "+ sku+ " is not found");}
        
      if(vendorCode.length()>4){
        throw new IllegalArgumentException("Vendor code " + vendorCode + "is not found");}
        
      if(countryCode.length()>4){
        throw new IllegalArgumentException("Country code " + countryCode + "is not exist");}  

        this.demandId = demandId;
        this.sku = sku;
        this.vendorCode = vendorCode;
        this.orderQty = orderQty;
        this.unitCost =unitCost;
        this.countryCode =countryCode;
        this.shipDate =shipDate;
        
    }

    // Calculate amount each PO
    public double amount(int orderQty, double unitCost){
        return unitCost*orderQty;}


     // Calculate charge ship if after 23/5/2026 then charge 10%   

    public double chargeShip(LocalDate shipDate, double amount, double chargeFee){
        
        if(this.shipDate.isAfter(LocalDate.of(2026, 06, 23))){
            return amount*chargeFee;}
            return 0.0;

    } }   
// Class for input new data into the standard class
class DemandPO{
    int demandId;
    String sku;
    int orderQty;
    String vendorCode;
    String countrycode;
    double unitCost;
    LocalDate shipDate;

// Constructor and method of class demandpo
public DemandPO(
    int demandId,
    String sku,
    int orderQty,
    String vendorCode,
    String countrycode,
    double unitCost,
LocalDate shipDate){
        this.demandId = demandId;
        this.sku =sku;
        this.orderQty =orderQty;
        this.vendorCode = vendorCode;
        this.countrycode = countrycode;
        this.unitCost=unitCost;
        this.shipDate =shipDate;
    }    
}


public class Baitap8 {
    public static void main(String[] args) {
        double chargeFee= 0.1;

/// ARRAY List Demand PO
ArrayList<DemandPO> InputDemand = new ArrayList<>();
InputDemand.add(new DemandPO(1, "02MN", 10, "NTX", "USA", 12.45,LocalDate.of(2025, 05, 01)));
InputDemand.add(new DemandPO(2, "02MA2", 10, "NTX", "USA", 14.45,LocalDate.of(2025, 05, 01)));
InputDemand.add(new DemandPO(3, "02MN", 10, "NTX", "USA", 16.45,LocalDate.of(2025, 05, 01)));


/// ARRAY List PO standard

ArrayList<WHPO> PODetail = new ArrayList<>();
PODetail.add(new WHPO(4, "MNHU", "NTX", 10, 12.45, "CAN", LocalDate.of(2026, 02, 27)));
PODetail.add(new WHPO(5, "MNHX", "LTR", 10, 13.45, "USA", LocalDate.of(2026, 02, 24)));
PODetail.add(new WHPO(6, "MNHB", "HTC", 10, 12.09, "MEX", LocalDate.of(2026, 07, 30)));
PODetail.add(new WHPO(7, "MNHA", "TPF", 10, 1.4, "VNM", LocalDate.of(2026, 07,17)));

// xỬ LÝ GENERATE PO
int runningNo =1;
double TotalAmount =0;
for (DemandPO dpo : InputDemand) {
    try {
        WHPO newPO = new WHPO(dpo.demandId,
 dpo.sku,
 dpo.vendorCode,
 dpo.orderQty,
 dpo.unitCost,
 dpo.countrycode,
 dpo.shipDate);

/// generate PO number
LocalDate today = LocalDate.now();
String year2Digit = String.format("%02d", today.getYear()% 100);
String poNumber = dpo.countrycode + "WH" + dpo.vendorCode + year2Digit + String.format("%03d",runningNo);
    newPO.poNumber= poNumber;
    PODetail.add(newPO);
        System.out.printf("Success to add PO: %s | PO Number: %s\n",dpo.sku,newPO.poNumber);
        runningNo++;
} catch (IllegalArgumentException e) {
        System.out.printf("Failed to add database: %s\n", dpo.sku);
        
    }
}
for(WHPO wpo: PODetail){
    double poAmount= wpo.amount(wpo.orderQty, wpo.unitCost);
    double poChargefee = wpo.chargeShip(wpo.shipDate, poAmount,chargeFee);
    double poAmountSubtotal = poAmount +poChargefee;

    TotalAmount+=poAmountSubtotal;
    System.out.printf("Total amount PO: %s  | Charge ship: %.2f\n", poAmountSubtotal, poChargefee);
    System.out.println("Total PO amount" + TotalAmount);
}
    }}