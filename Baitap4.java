import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

class thuNhapBookingPhong{   // Khai báo template dự định đưa dữ liệu vô
    int soPhongCheckIn;
    LocalTime gioInThucTe;
    LocalTime gioOutThucTe;
    int soNgayCheckIn;
    double giaPhong;

public thuNhapBookingPhong(int soPhongCheckIn, LocalTime gioInThucTe, LocalTime gioOutThucTe, int soNgayCheckIn, double giaPhong){ // CONTRUSTOR dùng để tạo object
    this.soPhongCheckIn = soPhongCheckIn;
    this.gioInThucTe = gioInThucTe;
    this.gioOutThucTe = gioOutThucTe;
    this.soNgayCheckIn = soNgayCheckIn;
    this.giaPhong = giaPhong;}


 public double tinhPhuPhiIn(double phiCheckInQuaGio, LocalTime gioInQuyDinh){  // METHOD Business Logic Function để tính toán điều kiện
    if(this.gioInThucTe .isBefore(gioInQuyDinh)) {
        long soGioChenhLech = ChronoUnit.HOURS.between(this.gioInThucTe, gioInQuyDinh);
        return soGioChenhLech*phiCheckInQuaGio;
    }
    return 0.0;
 }

 public double tinhPhuPhiOut(double phiCheckOutQuaGio,LocalTime gioOutQuyDinh ){ //METHOD Business Logic Function để tính toán điều kiện
    if(this.gioOutThucTe .isAfter(gioOutQuyDinh)) {
        long soGioChenhLech= ChronoUnit.HOURS.between(gioOutQuyDinh, this.gioOutThucTe);
        return soGioChenhLech*phiCheckOutQuaGio;
    }
    return 0.0;
 }}

public class Baitap4 {  //class chính chạy chương trình
    public static void main(String[] args) {  //Hàm chạy tính toán
        LocalTime gioInQuyDinh = LocalTime.of(12, 0);
        LocalTime gioOutQuyDinh = LocalTime.of(12, 0);
        
        double phiCheckInQuaGio = 9.7; //Giá phạt mỗi giờ check in trễ
        double phiCheckOutQuaGio = 26.5; // Giá phạt mỗi giờ check out trễ

        ArrayList<thuNhapBookingPhong> danhSachPhongTinhTien = new ArrayList<>();  // OBJECT khai báo dữ liệu thật, cấu trúc array.add(new class(data)

        danhSachPhongTinhTien.add(new thuNhapBookingPhong(101,LocalTime.of(12, 0),LocalTime.of(12, 0),2,80.0));
        danhSachPhongTinhTien.add(new thuNhapBookingPhong(102, LocalTime.of(11, 0) , LocalTime.of(14, 0), 3, 160.0));
        danhSachPhongTinhTien.add(new thuNhapBookingPhong(103, LocalTime.of(13, 0) , LocalTime.of(17, 0), 3, 270.0));
        danhSachPhongTinhTien.add(new thuNhapBookingPhong(104, LocalTime.of(12, 0) , LocalTime.of(12, 0), 3, 320.0));
        
        double tongDoanhThuPhong = 0; // Khai báo tạm 1 biến tính tổng

        ArrayList<Double> tatCaChiPhiOut = new ArrayList<>();
        System.out.println("=== BAO CAO CHI TIET");

        for (thuNhapBookingPhong tnbp : danhSachPhongTinhTien){  //tính trên từng loop điều kiện object 
            double phiIn = tnbp.tinhPhuPhiIn(phiCheckInQuaGio, gioInQuyDinh);
            double phiOut = tnbp.tinhPhuPhiOut(phiCheckOutQuaGio, gioOutQuyDinh);
            double tongTienPhong = (phiIn + phiOut) + (tnbp.giaPhong * tnbp.soNgayCheckIn);

            tongDoanhThuPhong += tongTienPhong ; // += tức là cộng dồn lên, A +B, ra giá trị C + D,...
            tatCaChiPhiOut.add(phiOut);
            
            System.out.printf("Phong %d | Phi In: %.2f$ | Phi Out: %.2f$| Tong: %.2f$ \n" , tnbp.soPhongCheckIn,phiIn,phiOut,tongTienPhong); //printf là hiển thị Formatting giá trị, ví dụ nếu là %.2f là làm tròn 2 chữ số thập phân
        }
        System.out.println("\n=== Bao cao cuoi ngay ===");
        System.out.printf("Tong chi phi luu tru: %.2f$ \n", tongDoanhThuPhong);
        System.out.printf("Phu phi Check Out cao nhat: %.2f$ \n",Collections.max(tatCaChiPhiOut));

    }
}
