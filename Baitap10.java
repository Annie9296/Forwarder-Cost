import java.time.LocalDate;
import java.util.ArrayList;

// Class Inventory place to show summary following the each sku
class Inventory{
    String sku;
    String warehouseCode;
    int physicalQty;
    int holdQty;
    int salableQty;
    String inboundCode;
    double storageFee;

// METHOD Inventory of class inventory to show which data field and bussiness calculation
public Inventory(
    String sku,
    String warehouseCode,
    int physicalQty,
    int holdQty,
    String inboundCode,
    double storageFee){
    
    /// Validation if any action to add SKU
    if(sku.length()>4){
        throw new IllegalArgumentException("SKU " + sku + " is not found");
    }
    if(holdQty>physicalQty){
        throw new IllegalArgumentException("Hold Qty must lower than or equal by physical quantity");
    }
    this.sku= sku;
    this.warehouseCode =warehouseCode;
    this.physicalQty = physicalQty;
    this.holdQty = holdQty;
    this.inboundCode=inboundCode;
    this.storageFee =storageFee;}

    //// Get sellabel quantity each SKU
    public int getsalableQty(){
        return physicalQty - holdQty;};
    public double calculateLiquidationFee(double inventoryValue){
        return 0.0;};
}
// Class Inbound code used in add qty into inventory class
class InboundCode{
    String sku;
    String warehouseCode;
    int importQty;
    String inboundCode;
    String inboundStatus;
    LocalDate inboundDate;

 // METHOD of class inbound code to add data into Inventory
 public InboundCode(
    String sku,
    String warehouseCode,
    int importQty,
    String inboundStatus,
    LocalDate inboundDate){
        this.sku = sku;
        this.warehouseCode =warehouseCode;
        this.importQty = importQty;
        this.inboundStatus = inboundStatus;
        this.inboundDate = inboundDate;    
    }
}
class ExpireSKU extends Inventory{
    boolean expireSKU;
    

    public ExpireSKU(String sku,
    String warehouseCode,
    int physicalQty,
    int holdQty,
    String inboundCode,
    double storageFee,
    boolean expireSKU){
        super(sku,warehouseCode,physicalQty,holdQty,inboundCode,storageFee);
        this.expireSKU= expireSKU;
        
    }
    @Override 
    public double calculateLiquidationFee(double inventoryValue){
    if(!expireSKU){
        return 0.0;} 
        return inventoryValue * 0.15;
    }}





public class Baitap10 {
    
    public static void main(String[] args) {
        // ARRAY LIST SKU THAM CHIEU
        ArrayList<String> skuList = new ArrayList<>();
        skuList.add("02MN");
        skuList.add("JUPS");
        skuList.add("VDVY");
        skuList.add("JX7N");

       // ARRAY LIST THAM CHIEU GIA SKU
        ArrayList<Double> unitPrice = new ArrayList<>();
        unitPrice.add(12.7);
        unitPrice.add(11.99);
        unitPrice.add(14.06);
        unitPrice.add(19.54);

       // ARRAY LIST INBOUND CODE
       ArrayList<InboundCode> inboundTransaction = new ArrayList<>();
       inboundTransaction.add(new InboundCode("02MN",  "BRE", 10, "IN PROGRESS", LocalDate.of(2026, 5, 1)));
       inboundTransaction.add(new InboundCode("JUPS",  "BRE", 12, "COMPLETED", LocalDate.of(2026, 6, 1)));
       inboundTransaction.add(new InboundCode("VDVY",  "BRE", 100, "COMPLETED", LocalDate.of(2026, 4, 1)));
       inboundTransaction.add(new InboundCode("JX7N",  "BRE", 10, "IN PROGRESS", LocalDate.of(2026, 5, 1)));
    
       // ARRAY LIST INBOUND SKU
       ArrayList<Inventory> inventorySku = new ArrayList<>();
       inventorySku.add(new Inventory("02MN","BRE",10,8,"",0));
       inventorySku.add(new Inventory("JUPS", "BRE",10,3,"",0));
       inventorySku.add(new Inventory("VDVY", "BRE",10,1,"",0));
       inventorySku.add(new Inventory("JX7N", "BRE",10,2,"",0));
       inventorySku.add(new ExpireSKU("JX7N", "BRE",10,2,"",0,true));
       inventorySku.add(new ExpireSKU("JUPS", "BRE",10,2,"",0,false));

       // GENERATE INBOUND CODE
       int runningNo=1;
       
       
       for (InboundCode ic : inboundTransaction) {
        try {
            String inboundCode= "";
            if (ic.inboundStatus.equals("COMPLETED")){
                String year2Digit = String.format("%02d", ic.inboundDate.getYear()%100);
                inboundCode = "IB"+ic.warehouseCode+year2Digit+runningNo;
                runningNo++;
            }
            Inventory iv = new Inventory(ic.sku, ic.warehouseCode, ic.importQty,0,inboundCode,0);
            inventorySku.add(iv);
            System.out.printf("Sucess to add SKU: %s | Inbound code: %s%n" , ic.sku,inboundCode);

        }
            catch(IllegalArgumentException e){
                System.out.printf("Failed to add SKU: %s | Reaspm %s%n",ic.sku, e.getMessage());
            }
    }
    System.out.println("Inventory Summary");
    double totalInventoryValue =0;
    for (Inventory iv : inventorySku) {
        int salableQty = iv.getsalableQty();
        double storageFee;
        if(salableQty<=5){storageFee=0;}
        else if(salableQty<=15){
            storageFee= salableQty*5;
        } else{storageFee= 15*5 + (salableQty-15)*1.2;}
       double unitPriceValue= 0;
       for (int i = 0; i < skuList.size(); i++) {
        if(iv.sku.equals(skuList.get(i))){
            unitPriceValue = unitPrice.get(i);
            break;
        }
       }
       double inventoryValue = salableQty * unitPriceValue;
       totalInventoryValue +=inventoryValue;
       double liquidationSubtotal = iv.calculateLiquidationFee(inventoryValue);
       System.out.printf("SKU: %s | Salable Qty: %d | Storage Fee: %.2f  | Inventory Value: %2f\n | Liquidation Fee: %.2f\n", iv.sku,salableQty,storageFee,inventoryValue, liquidationSubtotal);
    }
    
    System.out.printf("%n Total Inventory Value: %2f\n",totalInventoryValue);
    


}}
