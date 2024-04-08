import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DanhSachKhoa {
    private List<Khoa> danhSachKhoa;

    public DanhSachKhoa() {
        danhSachKhoa = new ArrayList<>();
    }
    public void themKhoa(Khoa khoa) {
        danhSachKhoa.add(khoa);
    }
    public List<Khoa> getDanhSachKhoa() {
        return danhSachKhoa;
    }


    public Khoa timKhoaTheoTen(String tenKhoa) {
        for (Khoa khoa : danhSachKhoa) {
            if (khoa.getTenKhoa().equalsIgnoreCase(tenKhoa)) {
                return khoa;
            }
        }
        return null;
    }

}
