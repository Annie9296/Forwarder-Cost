
import java.util.ArrayList; // Bước 1: Bắt buộc phải import thư viện này ở đầu file
import java.util.Collections;
    
public class BaiTap2 {

    public static void tinhDoanhThuKhachSanTrongNgay(
        ArrayList<String> danhSachPhong,
        ArrayList<Double> giaTienPhong,
        ArrayList<Integer> soNguoiLon) 
        {
            double doanhThuMoiLoaiPhongBaMuoiNgay =0;
        
        for(int i = 0; i < danhSachPhong.size(); i++){
            double doanhThuMoiLoaiPhongMoiNgay = giaTienPhong.get(i) * soNguoiLon.get(i);
            System.out.println("Doanh thu loai phong" + danhSachPhong.get(i) + "se thu cho" + soNguoiLon.get(i) + "nguoi la" + doanhThuMoiLoaiPhongMoiNgay);
            doanhThuMoiLoaiPhongBaMuoiNgay = doanhThuMoiLoaiPhongBaMuoiNgay + (doanhThuMoiLoaiPhongMoiNgay *30);
        }
        System.out.println("Doanh Thu 30 Ngay cua cac loai phong" + doanhThuMoiLoaiPhongBaMuoiNgay);
        }
    public static void main(String[] args) {
        ArrayList<String> danhSachPhong = new ArrayList<>();
        danhSachPhong.add("SUPER VIP");
        danhSachPhong.add("SUITE");
        danhSachPhong.add("PREMIUM");
        danhSachPhong.add("STANDARD");
        danhSachPhong.add("CHEAP");
        ArrayList<Double> giaTienPhong = new ArrayList<>();
        giaTienPhong.add(14.9);
        giaTienPhong.add(12.9);
        giaTienPhong.add(10.9);
        giaTienPhong.add(8.9);
        giaTienPhong.add(5.9);
        ArrayList<Integer> soNguoiLon = new ArrayList<>();
        soNguoiLon.add(1);
        soNguoiLon.add(2);
        soNguoiLon.add(3);
        soNguoiLon.add(4);
        soNguoiLon.add(5);
        tinhDoanhThuKhachSanTrongNgay(danhSachPhong, giaTienPhong, soNguoiLon);
        double giaCanTim= 5.9;
        int phongSo= giaTienPhong.indexOf(giaCanTim);
        if (phongSo!=-1){
            System.out.println("Tim thay muc gia can thue" + giaCanTim + "tai phong" + phongSo + "chinh la loai" + danhSachPhong.get(phongSo));
        } else {
            System.out.println("Khong co mua gia can tim");
                    Collections.sort(giaTienPhong, Collections.reverseOrder());
        System.out.println("Gia tien phong cao nhat" + giaTienPhong);
        }
    }
    
    }
        