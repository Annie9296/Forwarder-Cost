import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

class PaymentRequest {
    String paymentId;
    String paymentPo;
    double paymentAmount;
    LocalDate interestPaymentStart;
    LocalDate interestPaymentEnd;
    LocalDate paymentOverDate;
    LocalDate paymentDeadline;

 public PaymentRequest(
    String paymentId,
    String paymentPo,
    double paymentAmount,
    LocalDate interestPaymentStart,
    LocalDate interestPaymentEnd,
    LocalDate paymentOverDate,
    LocalDate paymentDeadline
 )   {
    this.paymentId = paymentId;
    this.paymentPo = paymentPo;
    this.paymentAmount = paymentAmount;
    this.paymentDeadline = paymentDeadline;
    this.interestPaymentEnd=interestPaymentEnd;
    this.interestPaymentStart= interestPaymentStart;
    this.paymentOverDate = paymentOverDate;
 }
 public double CalculatePaymentInterest (double paymentAmount, int freeInterestDay, int paidInterestDay, double paymentInterest)
 { LocalDate today= LocalDate.now();

    // CASE 1 là Today nằm trong hạn thanh toán
    if(today.isBefore(interestPaymentEnd) || today.isBefore(interestPaymentStart)){
    System.out.println("Not Reach Payment term days to Pay vendor");
 return 0.0;}
 
 // CASE 2: TODAY chưa overdue
if(paymentDeadline.isAfter(today)){
    System.out.println("Not Reach Deadline to Pay vendor");
return 0.0;}

// CASE 3: Today overdue và phát sinh lãi 
long overdueDays= ChronoUnit.DAYS.between(paymentDeadline, today);
double paymentFee =0;
if(overdueDays<= freeInterestDay) {
    paymentFee = paymentAmount * paymentInterest;
}
else if(overdueDays >freeInterestDay && overdueDays <= paidInterestDay){
    paymentFee = paymentAmount * paymentInterest * (paidInterestDay - freeInterestDay);
} 
else {
    paymentFee = paymentAmount * paymentInterest * (overdueDays - freeInterestDay) * 0.5;}

System.out.println(paymentId + "| Overdue Days: " + overdueDays + "| Payment Fee: " + paymentFee);
return paymentFee;
}}

public class Baitap5 {
    public static void main(String[] args) {
        int freeInterestDay =3;
        int paidInterestDay = 30;
        double paymentInterest = 0.15;
        

        ArrayList<PaymentRequest> CalculatePaymentAmount = new ArrayList<>();

        CalculatePaymentAmount.add(new PaymentRequest("SUP123","PO123",1200.00,LocalDate.of(2025,05,01), LocalDate.of(2025,05,31), LocalDate.of(2025,05,20 ),LocalDate.of(2025,05,20 )));
        CalculatePaymentAmount.add(new PaymentRequest("SUP124","PO124",1310.20,LocalDate.of(2025,06,01), LocalDate.of(2025,06,30), LocalDate.of(2025,06,20 ),LocalDate.of(2025,06,20 )));
        CalculatePaymentAmount.add(new PaymentRequest("SUP125","PO125",1570.08,LocalDate.of(2025,07,01), LocalDate.of(2025,07,30), LocalDate.of(2025,07,20 ),LocalDate.of(2025,07,20 )));
        CalculatePaymentAmount.add(new PaymentRequest("SUP126","PO126",2100.76,LocalDate.of(2025,8,01), LocalDate.of(2025,8,30), LocalDate.of(2025,8,20 ),LocalDate.of(2025,8,20 )));
        CalculatePaymentAmount.add(new PaymentRequest("SUP127","PO127",3100.23,LocalDate.of(2025,9,01), LocalDate.of(2025,9,30), LocalDate.of(2025,9,20 ),LocalDate.of(2025,9,20 )));

        double PaymentDept = 0;

        for (PaymentRequest pr: CalculatePaymentAmount){
             double paymentInterestFee = pr.CalculatePaymentInterest(PaymentDept, freeInterestDay, paidInterestDay, paymentInterest);
            double totalPayment = paymentInterestFee + pr.paymentAmount;
            PaymentDept += totalPayment;
            System.out.println("Total Payment need to transfer is " + pr.paymentId + "with po " + pr.paymentPo + totalPayment);
        }
        System.out.println("Tong chi no can thanh toan la " + PaymentDept);
    }
}
