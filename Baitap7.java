import java.time.LocalDate;
import java.util.ArrayList;

class PurchaseOrderWH {  //Là business object chuẩn data ban đầu, có thể hiểu là db table hiện có
    int poId;
    String poNumber;
    double poAmount;
    LocalDate poShipDate;
    public PurchaseOrderWH (
        int poId,
        String poNumber,
        double poAmount,
        LocalDate poShipDate
    
    ){  // xử lý validation các yêu cầu nhập data vào bảng sẽ phải đúng rule, nghĩa là data sai thì không được tạo object
    if(poNumber.length()<4){
        throw new IllegalArgumentException("Po Number must be contain 4 letters, please check again your input" + poNumber);
    }
    if (poAmount <= 0){
        throw new IllegalArgumentException("PO Amount can not be null" + poNumber);
    }
    LocalDate today = LocalDate.now();
    if(poShipDate!=null && poShipDate.isBefore(today)){
        throw new IllegalArgumentException("PO shipdate can not pick the date in the past" + poNumber);
    }
    this.poId =poId;
    this.poAmount =poAmount;
    this.poNumber =poNumber;
    this.poShipDate =poShipDate;
}
}
class PreviousData{ // Data object muốn được add vào class PurchaseOrderWH
    int pdId;
    String pdNumber;
    double pdAmount;
    LocalDate pdShipDate;
    public PreviousData(
        int pdId,
        String pdNumber,
        double pdAmount,
        LocalDate pdShipDate)
    {
    this.pdId =pdId;
    this.pdAmount= pdAmount;
    this.pdNumber =pdNumber;
    this.pdShipDate =pdShipDate;
}}


public class Baitap7 {
    public static void main(String[] args) {
        ArrayList<PreviousData> POList = new ArrayList<>();
        POList.add(new PreviousData(12, "PO1", 1200.0, LocalDate.of(2026, 05, 01)));
        POList.add(new PreviousData(13, "PO13", 500.0, LocalDate.of(2027, 05, 01)));
        POList.add(new PreviousData(145, "PO12", 1200.0, LocalDate.of(2028, 04, 01)));
        POList.add(new PreviousData(176, "PO176", 1200.0, LocalDate.of(2025, 05, 01)));

        ArrayList<PurchaseOrderWH> POOriginal = new ArrayList<>(); // Khai báo object cho class ban đầu
        for (PreviousData data: POList){ // bằng cách lấy data Previous nhập vào class gốc theo từng loop
            try{  // khai báo cách add data vào class gốc
                PurchaseOrderWH newPo= new PurchaseOrderWH(data.pdId, data.pdNumber, data.pdAmount, data.pdShipDate);
                POOriginal.add(newPo);
                System.out.printf("Successfully adding data into PO: %s\n",data.pdNumber);
                
            } catch (IllegalArgumentException e){
                System.out.printf(" Failed to add data into PO: %s\n", data.pdNumber + "| Reason " + e.getMessage());
            }
        }
        System.out.println("Total line adding data " + POOriginal.size() + " match requirement");
    }
}
