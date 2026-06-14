

public class Main{
public static void main(String[] args) {
    int[] giaPhong={100,200,300,400,500,600};
    System.out.println("Gia Phong cao nhat" + giaPhong[5]);
    int[] soNguoiMoiPhong = {1,2,3,4,5,6};
    for (int i=0; i<soNguoiMoiPhong.length;i++){
        System.out.println("So nguoi" + soNguoiMoiPhong[i] + "o phong"+ giaPhong[i] + "can tra tien "+ (soNguoiMoiPhong[i]*giaPhong[i])+ "tram nghin");
    }
}
}
