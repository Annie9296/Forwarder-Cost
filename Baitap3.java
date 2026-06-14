import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public class Baitap3 {

public static void tinhPhuPhi(
    ArrayList<LocalTime> thoiGianCheckOut,
    ArrayList<Integer> soPhongCheckIn,
    ArrayList<LocalTime> thoiGianCheckIn,
    ArrayList<LocalTime> checkOutThucTe,
    double phiCheckOutQuaGio,
    double phiCheckInQuaGio,
    LocalTime thoiGianQuyDinhCheckIn,
    LocalTime thoiGianQuyDinhCheckOut)
 
    {  double tongPhuPhiInOut=0;
        ArrayList<Double> danhSachPhiCheckIn = new ArrayList<>();
        ArrayList<Double> danhSachPhiCheckOut = new ArrayList<>();

    for ( int i =0; i<soPhongCheckIn.size(); i++){
        LocalTime gioInThucTe = thoiGianCheckIn.get(i);
        if ( gioInThucTe.isBefore(thoiGianQuyDinhCheckIn)){
            long soGioChenhLech= ChronoUnit.HOURS.between(gioInThucTe, thoiGianQuyDinhCheckIn);
            double tinhPhiCheckIn = soGioChenhLech * phiCheckInQuaGio;
            System.out.println("Phong " + soPhongCheckIn.get(i) + " check in vao luc " + gioInThucTe + "chenh lech voi quy dinh la  " + soGioChenhLech + " gio" + "nen tinh phu phi la  " + (Math.round(tinhPhiCheckIn)));
            tongPhuPhiInOut = tongPhuPhiInOut + tinhPhiCheckIn;
            danhSachPhiCheckIn.add(tinhPhiCheckIn);
        }
        else{
            System.out.println("So phong " + soPhongCheckIn.get(i) + " da check in dung gio");
            danhSachPhiCheckIn.add(0.0);
        }
    }
    
    for(int i=0; i< soPhongCheckIn.size(); i++){
        LocalTime gioOutThucTe = thoiGianCheckOut.get(i);
        if ( gioOutThucTe.isAfter(thoiGianQuyDinhCheckOut)){
            long soGioChenhLech= ChronoUnit.HOURS.between(thoiGianQuyDinhCheckOut, gioOutThucTe);
            double tinhPhiCheckOut = soGioChenhLech * phiCheckOutQuaGio;
            System.out.println("Phong " + soPhongCheckIn.get(i) + " check out vao luc " + gioOutThucTe + "chenh lech voi quy dinh la  " + soGioChenhLech +" gio"+ "nen tinh phu phi la  " + (Math.round(tinhPhiCheckOut)));
            tongPhuPhiInOut = tongPhuPhiInOut + tinhPhiCheckOut;
            danhSachPhiCheckOut.add(tinhPhiCheckOut);
        }
        else {
            System.out.println("So phong " + soPhongCheckIn.get(i) + " da check out dung gio");
            danhSachPhiCheckOut.add(0.0);
        }
    }
    System.out.println("Tong doanh thu phi phi in Out cua khach san la  " + tongPhuPhiInOut);
double phiInCaoNhat = Collections.max(danhSachPhiCheckIn);
System.out.println("Tong phu phi check in cao nhat la " + phiInCaoNhat);
double phiOutCaoNhat = Collections.max(danhSachPhiCheckOut);
System.out.println("Tong phu phi check out cao nhat la " + phiOutCaoNhat);

    }

    


    public static void main(String[] args) {
        ArrayList<LocalTime> thoiGianCheckOut = new ArrayList<>();
        thoiGianCheckOut.add(LocalTime.of(11,0));
        thoiGianCheckOut.add(LocalTime.of(13,0));
        thoiGianCheckOut.add(LocalTime.of(15,0));
        thoiGianCheckOut.add(LocalTime.of(18,0));
        thoiGianCheckOut.add(LocalTime.of(21,0));

        ArrayList<LocalTime> thoiGianCheckIn = new ArrayList<>();
        thoiGianCheckIn.add(LocalTime.of(11, 0));
        thoiGianCheckIn.add(LocalTime.of(12, 0));
        thoiGianCheckIn.add(LocalTime.of(13, 0));
        thoiGianCheckIn.add(LocalTime.of(15, 0));
        thoiGianCheckIn.add(LocalTime.of(18, 0));
    
        ArrayList<Integer> soPhongCheckIn = new ArrayList<>();
        soPhongCheckIn.add(101);
        soPhongCheckIn.add(102);
        soPhongCheckIn.add(103);
        soPhongCheckIn.add(104);
        soPhongCheckIn.add(105);

        ArrayList<LocalTime> checkOutThucTe = new ArrayList<>();
        checkOutThucTe.add(LocalTime.of(12, 0));
        checkOutThucTe.add(LocalTime.of(13, 0));
        checkOutThucTe.add(LocalTime.of(15, 0));
        checkOutThucTe.add(LocalTime.of(18, 0));
        checkOutThucTe.add(LocalTime.of(21, 0));


        double phiCheckOutQuaGio = 11999.09; //Phí được tính theo từng giờ quá giờ quy định

        double phiCheckInQuaGio = 288.12;

        LocalTime thoiGianQuyDinhCheckIn = LocalTime.of(12, 0); // Thời gian quy định phải check in

        LocalTime thoiGianQuyDinhCheckOut = LocalTime.of(12, 0); // Thời gian quy định phải check out

        tinhPhuPhi(thoiGianCheckOut, soPhongCheckIn, thoiGianCheckIn, checkOutThucTe, phiCheckOutQuaGio, phiCheckInQuaGio, thoiGianQuyDinhCheckIn, thoiGianQuyDinhCheckOut);
    }}