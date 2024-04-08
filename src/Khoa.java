import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Khoa {

    private String tenKhoa;

    public List<SinhVien> getSinhVienList() {
        return sinhVienList;
    }

    private List<SinhVien> sinhVienList;

    public Khoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
        this.sinhVienList = new ArrayList<>();
    }

    public String getTenKhoa() {
        return tenKhoa;
    }


    public void themSinhVien(SinhVien sinhVien) {
        sinhVienList.add(sinhVien);
    }

    public int sinhVienChinhQuyTrongKhoa() {
        int count = 0;
        for (SinhVien sinhVien : sinhVienList) {
            if (sinhVien instanceof SinhVienChinhQuy) {
                count++;
            }
        }
        return count;
    }

    public SinhVien sinhVienDiemDauVaoCaoNhat() {
        SinhVien sinhVienMax = null;
        float diemMax = Float.MIN_VALUE;
        for (SinhVien sinhVien : sinhVienList) {
            if (sinhVien.getDiemdauvao() > diemMax) {
                diemMax = sinhVien.getDiemdauvao();
                sinhVienMax = sinhVien;
            }
        }
        return sinhVienMax;
    }

    public List<SinhVienTaiChuc> sinhVienTaiChucTheoNoiDaoTao(String noiDaoTao) {
        List<SinhVienTaiChuc> sinhVienTaiChucList = new ArrayList<>();
        for (SinhVien sinhVien : sinhVienList) {
            if (sinhVien instanceof SinhVienTaiChuc) {
                SinhVienTaiChuc svTaiChuc = (SinhVienTaiChuc) sinhVien;
                if (svTaiChuc.getNoiLKDT().equalsIgnoreCase(noiDaoTao)) {
                    sinhVienTaiChucList.add(svTaiChuc);
                }
            }
        }
        return sinhVienTaiChucList;
    }

    public List<SinhVien> sinhVienDiemTrungBinhCaoNhatHocKyGanNhat(float diem) {
        List<SinhVien> sinhVienListDiemCao = new ArrayList<>();
        Map<SinhVien, Float> mapDiem = new HashMap<>();
        for (SinhVien sinhVien : sinhVienList) {
            DanhSachKetQua ketQua = sinhVien.ketqua;
            if (!ketQua.list.isEmpty()) {
                KetQuaHocTap hocKyCuoi = ketQua.list.get(ketQua.list.size() - 1);
                if (hocKyCuoi.getDiemTrungBinh() >= diem) {
                    sinhVienListDiemCao.add(sinhVien);
                    mapDiem.put(sinhVien, hocKyCuoi.getDiemTrungBinh());
                }
            }
        }
        return sinhVienListDiemCao;
    }

    public SinhVien sinhVienDiemTrungBinhCaoNhat() {
        SinhVien sinhVienMax = null;
        float diemMax = Float.MIN_VALUE;
        for (SinhVien sinhVien : sinhVienList) {
            float diemTB = sinhVien.ketqua.tinhDiemTB();
            if (diemTB > diemMax) {
                diemMax = diemTB;
                sinhVienMax = sinhVien;
            }
        }
        return sinhVienMax;
    }

    public void sapXepSinhVienTheoNamVaLoai() {
        sinhVienList.sort((sv1, sv2) -> {
            if (sv1 instanceof SinhVienChinhQuy && sv2 instanceof SinhVienTaiChuc) {
                return -1;
            } else if (sv1 instanceof SinhVienTaiChuc && sv2 instanceof SinhVienChinhQuy) {
                return 1;
            } else {
                return Integer.compare(sv2.getNamhoc(), sv1.getNamhoc());
            }
        });
    }

    public Map<Integer, Integer> thongKeSoLuongSinhVienTheoNam() {
        Map<Integer, Integer> thongKe = new HashMap<>();
        for (SinhVien sinhVien : sinhVienList) {
            int nam = sinhVien.getNamhoc();
            thongKe.put(nam, thongKe.getOrDefault(nam, 0) + 1);
        }
        return thongKe;
    }

    public void xoaSinhVien(SinhVien sv) {
        sinhVienList.remove(sv);
    }

    public void suaSinhVien(SinhVien sinhVienCanCapNhat) {
        // Tìm vị trí của sinh viên cần cập nhật trong danh sách
        int index = sinhVienList.indexOf(sinhVienCanCapNhat);

        // Nếu sinh viên tồn tại trong danh sách
        if (index != -1) {
            // Xóa sinh viên cũ
            sinhVienList.remove(index);

            // Thêm sinh viên mới vào vị trí của sinh viên cũ
            sinhVienList.add(index, sinhVienCanCapNhat);

            System.out.println("Thông tin sinh viên đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy sinh viên cần cập nhật trong danh sách.");
        }
    }

}
