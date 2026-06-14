import java.time.LocalDate;
import java.util.ArrayList;


class PurchaseOrderDI{ //Khai bao Class
    int poId;
    String poVendorCode;
    String poVendorCountry;
    double poOrderAmount;
    double poRemainingAmount;
    double poDeposit;
    String poDestinationCountry;
    double poTaxRate;
    LocalDate poShipDate;
    int poPaymentTermDays;
    LocalDate poPaymentDueDate;


public  PurchaseOrderDI( //Khai bao method & field trong class
    int poId,
    String poVendorCode,
    String poVendorCountry,
    double poOrderAmount,
    String poDestinationCountry,
    LocalDate poShipDate,
    int poPaymentTermDays)
    {  // khai bao contrustor để tạo object, form nhập dữ liệu cho bảng

    this.poId = poId;
    this.poVendorCode = poVendorCode;
    this.poVendorCountry = poVendorCountry;
    this.poOrderAmount = poOrderAmount;
    this.poDestinationCountry = poDestinationCountry;
    this.poShipDate = poShipDate;
    this.poPaymentTermDays = poPaymentTermDays;

}
// Khai bao METHOD, BUSSINESS LOGIC
/// BUOC 1: TINH DEPOSIT THEO MOI VENDOR

public double poVendorDeposit (double vendorDepositVietNam, String poVendorCountry, double poOrderAmount){
    if(this.poVendorCountry.equals("CHN")){return 0.0;}
    else return poOrderAmount*vendorDepositVietNam;}

/// BUOC 2: TINH TAX FEE THEO LOAI RATE THEO MOI VENDOR

    public double poTaxFee (double poOrderAmount, double taxRateChina, double taxRateVietNam, String poDestinationCountry){
        if(this.poDestinationCountry.equals("VNM")){return poOrderAmount*(1 + taxRateChina);}
        else return poOrderAmount*(1 + taxRateVietNam);}
    

    /// BUOC 3: TINH PAYMENT DUE DATE THEO MOI PO
    public LocalDate poPaymentDueDate (LocalDate poShipDate, int poPaymentTermDays){
        if (this.poShipDate == null) {return null;}
        else return poShipDate.plusDays(poPaymentTermDays);
    }

    //// BUOC 4 TINH REMAINING AMOUNT THEO MOI PO
    public double poRemainingAmount(double poOrderAmount, double poVendorDeposit){
        if(this.poDeposit == 0) {return poOrderAmount;}
        else return poOrderAmount-poVendorDeposit(poDeposit, poVendorCountry, poOrderAmount);
    }
}

/// HAM MAIN DE TINH TOAN
public class Baitap6 {
    public static void main(String[] args) {
        double vendorDepositVietNam = 0.5;
        double taxRateChina = 0.15;
        double taxRateVietNam = 0.05;

        //// BUOC 1 KHAI BAO OBJECT
        
        ArrayList<PurchaseOrderDI> PurchaseOrderDetail = new ArrayList<>();
       

        PurchaseOrderDetail.add(new PurchaseOrderDI(123,"NTX","CHN",1200.89,"USA",LocalDate.of(2025, 05, 01),30));
        PurchaseOrderDetail.add(new PurchaseOrderDI(124,"KST","VNM",2200.89,"USA",LocalDate.of(2025, 06, 01),30));
        PurchaseOrderDetail.add(new PurchaseOrderDI(125,"LTR","CHN",3200.89,"USA",LocalDate.of(2025, 07, 01),180));
        PurchaseOrderDetail.add(new PurchaseOrderDI(126,"NTX","CHN",5200.89,"USA",LocalDate.of(2025, 03, 01),60));
        PurchaseOrderDetail.add(new PurchaseOrderDI(127,"HTC","VNM",7200.89,"USA",LocalDate.of(2025, 02, 01),120));
        PurchaseOrderDetail.add(new PurchaseOrderDI(000,"HTC","VNM",7200.89,"USA",LocalDate.of(2025, 02, 01),120));

        // BUOC 2 LOOP
        double PurchaseCost =0;

        for (PurchaseOrderDI po : PurchaseOrderDetail) {
            
            double poVendorDepositAmount = po.poVendorDeposit(vendorDepositVietNam, po.poVendorCountry, po.poOrderAmount);
            double poTaxFeeAmount = po.poTaxFee( po.poOrderAmount,  taxRateChina,  taxRateVietNam,  po.poDestinationCountry);
            LocalDate poPaymentDueDateFinal = po.poPaymentDueDate( po.poShipDate, po.poPaymentTermDays);
            double poRemainingFinal = po.poRemainingAmount( po.poOrderAmount, po.poVendorDeposit(vendorDepositVietNam, po.poVendorCountry, poTaxFeeAmount));
            double PurchaseCostSubtotal = poVendorDepositAmount + poTaxFeeAmount + poRemainingFinal;

            PurchaseCost += PurchaseCostSubtotal;
            System.out.printf("PO: %s | Deposit: %.2f\n | Remaining: %.2f/n | Payment Due Date: %s\n", po.poId,poVendorDepositAmount, poRemainingFinal, poPaymentDueDateFinal);

            System.out.println("Total Purchase Cost is " + PurchaseCost);
           
        }
    }
}
