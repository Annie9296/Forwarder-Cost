public class  Baitap1{


    public static void tinhTienPhongTro (
        int[] soNguoi, 
        int[] soPhong, 
        int[] giaPhong){
    int tongDoanhThu =0;
    
        for (int i =0; i< giaPhong.length; i++){
            int tongTatCa = giaPhong[i] * soNguoi[i];
            System.out.println("Tong tien cua moi phong" + soPhong[i] + " danh cho" + soNguoi[i] + " nguoi la:" + tongTatCa);
            tongDoanhThu = tongDoanhThu + tongTatCa;
        }
        System.out.println("Tong tien can thu tat ca la " + tongDoanhThu);
    }
    public static void main(String[] args) {
    int[] soPhong={101,102,103,104};
    int[] giaPhong = {100,200,300,400};
    int[] soNguoi = {1,2,3,4};
    tinhTienPhongTro(soNguoi, soPhong, giaPhong);

}
}