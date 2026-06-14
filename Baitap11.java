
import java.util.ArrayList;


abstract  class BabySitRevenue {
    String loaiLop;
    String tenLop;
    int soBeMoiLop;
    double hocPhi;
    int tuoiBe;
    

    public BabySitRevenue(
        String loaiLop,
    String tenLop,
    int soBeMoiLop,
    double hocPhi,
    int tuoiBe){
        this.loaiLop =loaiLop;
        this.tenLop =tenLop;
        this.soBeMoiLop = soBeMoiLop;
        this.hocPhi = hocPhi;
        this.tuoiBe =tuoiBe;
    };
   public double gethocPhiMoiLop(){
        return this.hocPhi*this.soBeMoiLop;
    };
    public abstract double tinhPhiBoiDuongNam ();
    public abstract double tinhPhiCoSoVatChatNam();
    
}
class cacLoaiPhiNhaTruong extends BabySitRevenue{
    public cacLoaiPhiNhaTruong(String loaiLop, String tenLop, int soBeMoiLop, double hocPhi, int tuoiBe){
        super(loaiLop,tenLop,soBeMoiLop,hocPhi,tuoiBe);
    }
    @Override
    public double tinhPhiBoiDuongNam(){
        return (this.gethocPhiMoiLop()*12)*0.15;
    }

    @Override
    public double tinhPhiCoSoVatChatNam(){
        return (this.gethocPhiMoiLop()*12)*0.2;
    }
    

}


public class Baitap11 {
    public static void main(String[] args) {
        ArrayList<BabySitRevenue> revenueBaby = new ArrayList<>();
        revenueBaby.add(new cacLoaiPhiNhaTruong("LOP VO LONG", "LOP MAM", 5, 100, 1));
        revenueBaby.add(new cacLoaiPhiNhaTruong("LOP BAT DAU", "LOP LA", 10, 200, 3));
        revenueBaby.add(new cacLoaiPhiNhaTruong("LOP KET THUC", "LOP HOA", 15, 500, 5));

        System.out.println("---- Bao cao doanh thu -------");
        double actualDuKienDoanhThuMoiNam =0;

        for(BabySitRevenue bs: revenueBaby){
            double phiBoiDuongTrongNam = bs.tinhPhiBoiDuongNam();
            double phiCoSoVatChatTrongNam = bs.tinhPhiCoSoVatChatNam();
            double phiThuHocPhiTrongNam = bs.gethocPhiMoiLop();
            double thucThuPhuHuynhTrongNam = bs.tinhPhiBoiDuongNam() + bs.tinhPhiCoSoVatChatNam() + bs.gethocPhiMoiLop();
            actualDuKienDoanhThuMoiNam += thucThuPhuHuynhTrongNam;

            System.out.println("Tong thuc te thu tat ca lop hoc la: ---- " + actualDuKienDoanhThuMoiNam);
            System.out.printf("Loai Lop: %s  | Ten Lop: %-12s | Tuoi bat dau: %d   | Hoc Phi moi thang: %,10.2f | Hoc Phi Moi Nam: %,10.2f | Phi Boi Duong 1 Nam : %,10.2f  | Phi Co So Vat Chat 1 nam: %,10.2f\n", 
            bs.loaiLop,
            bs.tenLop,
            bs.tuoiBe,
            bs.hocPhi,
            phiThuHocPhiTrongNam,
            phiBoiDuongTrongNam,
            phiCoSoVatChatTrongNam);
        }
    }
    
}
