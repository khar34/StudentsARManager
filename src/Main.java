

//import java.util.Locale;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        //Locale locale = new Locale("vi", "VN");
        //Locale.setDefault(locale);
        DanhSachKhoa danhSachKhoa = new DanhSachKhoa();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=========================================================Menu=========================================================");
            System.out.println("1. Thêm sinh viên.");
            System.out.println("2. Hiển thị danh sách sinh viên.");
            System.out.println("3. Cập nhật thông tin sinh viên trong danh sách.");
            System.out.println("4. Xóa sinh viên ra khỏi danh sách.");
            System.out.println("5. Hiển thị số lượng sinh viên chính quy trong từng khoa.");
            System.out.println("6. Hiển thị sinh viên có điểm thi đầu vào cao nhất của mỗi khoa.");
            System.out.println("7. Hiển thị sinh viên tại chức tại một nơi đào tạo.");
            System.out.println("8. Hiển thị danh sách sinh viên có điểm trung bình học kì gần nhất từ 8.0 trở lên trong mỗi khoa.");
            System.out.println("9. Hiển thị sinh viên có điểm trung bình học kì cao nhất trong mỗi khoa.");
            System.out.println("10. Sắp xếp danh sách sinh viên trong mỗi khoa.");
            System.out.println("11. Thống kê số lượng sinh viên theo năm vào học trong mỗi khoa.");
            System.out.println("0. Thoát.");
            System.out.println("Nhập lựa chọn của bạn: ");
            System.out.println("\n======================================================================================================================");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        clearScreen();
                        themSinhVien(danhSachKhoa);
                        break;
                    case 2:
                        clearScreen();
                        inDanhSachSinhVien(danhSachKhoa);
                        break;
                    case 3:
                        clearScreen();
                        capNhatThongTinSinhVien(danhSachKhoa);
                        break;
                    case 4:
                        clearScreen();
                        xoaSinhVien(danhSachKhoa);
                        break;
                    case 5:
                        clearScreen();
                        hienThiSoLuongSVChinhQuyTheoKhoa(danhSachKhoa);
                        break;
                    case 6:
                        clearScreen();
                        hienThiSVCoDiemDauVaoCaoNhat(danhSachKhoa);
                        break;
                    case 7:
                        clearScreen();
                        hienThiSVTaiChucTaiNoiDaoTao(danhSachKhoa);
                        break;
                    case 8:
                        clearScreen();
                        hienThiSVCoDTBHon8TrongKyGanNhat(danhSachKhoa);
                        break;
                    case 9:
                        clearScreen();
                        hienThiSVCoDTBCaoNhat(danhSachKhoa);
                        break;
                    case 10:
                        clearScreen();
                        sapXepSVTrongMoiKhoa(danhSachKhoa);
                        break;
                    case 11:
                        clearScreen();
                        thongKeSVTheoNamVaoHoc(danhSachKhoa);
                        break;
                    case 0:
                        System.out.println("Đã thoát chương trình.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập một số nguyên.");
                scanner.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }

    private static void themSinhVien(DanhSachKhoa danhSachKhoa) {
        Scanner sc = new Scanner(System.in);

        String tenKhoa;
        Khoa khoa = null;
        do {
            System.out.print("Nhập tên khoa: ");
            tenKhoa = sc.nextLine();
            if (tenKhoa.isEmpty() || tenKhoa.trim().isEmpty()) {
                System.out.println("Tên khoa không được để trống. Vui lòng nhập lại.");
            } else {
                khoa = danhSachKhoa.timKhoaTheoTen(tenKhoa);
                if (khoa == null) {
                    khoa = new Khoa(tenKhoa);
                    danhSachKhoa.themKhoa(khoa);
                }
            }
        } while (tenKhoa.isEmpty() || tenKhoa.trim().isEmpty());

        int loaiSV;
        SinhVien sinhVien;

        do {
            System.out.println("Chọn loại sinh viên:");
            System.out.println("1. Sinh viên chính quy");
            System.out.println("2. Sinh viên tại chức");
            System.out.print("Chọn: ");
            loaiSV = sc.nextInt();
            sc.nextLine();

            if (loaiSV != 1 && loaiSV != 2) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (loaiSV != 1 && loaiSV != 2);

        if (loaiSV == 1) {
            sinhVien = new SinhVienChinhQuy();
        } else {
            sinhVien = new SinhVienTaiChuc();
        }

        sinhVien.input();
        khoa.themSinhVien(sinhVien);
    }

    private static void inDanhSachSinhVien(DanhSachKhoa danhSachKhoa) {
        System.out.println("Danh sách tất cả sinh viên:");
        for (Khoa khoa : danhSachKhoa.getDanhSachKhoa()) {
            for (SinhVien sv : khoa.getSinhVienList()) {
                sv.output();
            }
        }
    }

    private static void xoaSinhVien(DanhSachKhoa danhSachKhoa) {
        Scanner scanner = new Scanner(System.in);
        String maSV;
        boolean daXoa = false;

        // Yêu cầu người dùng nhập mã sinh viên và kiểm tra điều kiện
        do {
            System.out.print("Nhập mã sinh viên cần xóa: ");
            maSV = scanner.nextLine().trim(); // Loại bỏ các khoảng trắng ở đầu và cuối chuỗi
            if (maSV.isEmpty()) {
                System.out.println("Mã sinh viên không được để trống. Vui lòng nhập lại.");
            }
        } while (maSV.isEmpty()); // Lặp lại nếu mã sinh viên trống

        for (Khoa khoa : danhSachKhoa.getDanhSachKhoa()) {
            for (SinhVien sv : khoa.getSinhVienList()) {
                if (sv.getId().equals(maSV)) {
                    khoa.xoaSinhVien(sv);
                    System.out.println("Đã xóa sinh viên có mã " + maSV);
                    daXoa = true;
                    break;
                }
            }
            if (daXoa) {
                break;
            }
        }
        if (!daXoa) {
            System.out.println("Không tìm thấy sinh viên có mã " + maSV);
        }
    }


    private static void capNhatThongTinSinhVien(DanhSachKhoa danhSachKhoa) {
        Scanner scanner = new Scanner(System.in);
        String maSV;
        SinhVien sinhVienCanCapNhat = null;
        Khoa khoaSinhVien = null;

        // Yêu cầu người dùng nhập mã sinh viên và kiểm tra điều kiện
        do {
            System.out.print("Nhập mã sinh viên cần cập nhật: ");
            maSV = scanner.nextLine().trim(); // Loại bỏ các khoảng trắng ở đầu và cuối chuỗi
            if (maSV.isEmpty()) {
                System.out.println("Mã sinh viên không được để trống. Vui lòng nhập lại.");
            }
        } while (maSV.isEmpty()); // Lặp lại nếu mã sinh viên trống

        // Tìm sinh viên cần cập nhật trong danh sách sinh viên của các khoa
        for (Khoa khoa : danhSachKhoa.getDanhSachKhoa()) {
            for (SinhVien sv : khoa.getSinhVienList()) {
                if (sv.getId().equals(maSV)) {
                    sinhVienCanCapNhat = sv;
                    khoaSinhVien = khoa;
                    break;
                }
            }
            if (sinhVienCanCapNhat != null) {
                break;
            }
        }

        // Kiểm tra nếu không tìm thấy sinh viên cần cập nhật
        if (sinhVienCanCapNhat == null) {
            System.out.println("Không tìm thấy sinh viên có mã " + maSV);
            return;
        }

        // Hiển thị thông báo và yêu cầu người dùng nhập thông tin mới cho sinh viên
        System.out.println("Nhập thông tin mới cho sinh viên:");
        sinhVienCanCapNhat.input(); // Cập nhật thông tin sinh viên
        khoaSinhVien.suaSinhVien(sinhVienCanCapNhat); // Cập nhật thông tin trong danh sách sinh viên của khoa
        System.out.println("Đã cập nhật thông tin cho sinh viên có mã " + maSV);
    }



    private static void hienThiSoLuongSVChinhQuyTheoKhoa(DanhSachKhoa danhSachKhoa) {
        for(Khoa khoa: danhSachKhoa.getDanhSachKhoa()) {
            System.out.println("\nKhoa: " + khoa.getTenKhoa() + " có " + khoa.sinhVienChinhQuyTrongKhoa()+ " sinh viên chính quy.");
        }
    }

    private static void hienThiSVCoDiemDauVaoCaoNhat(DanhSachKhoa danhSachKhoa) {
        for(Khoa khoa : danhSachKhoa.getDanhSachKhoa()) {
            SinhVien sv = khoa.sinhVienDiemDauVaoCaoNhat();
            if (sv != null) {
                System.out.println("\nSinh viên có điểm đầu vào cao nhất của khoa " + khoa.getTenKhoa()+":");
                sv.output();
            }
            else {
                System.out.println("Không có sinh viên trong khoa " + khoa.getTenKhoa());
            }
        }
    }
    private static void hienThiSVTaiChucTaiNoiDaoTao(DanhSachKhoa danhSachKhoa) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập nơi đào tạo: ");
        String noiDaoTao = scanner.nextLine();
        for (Khoa khoa : danhSachKhoa.getDanhSachKhoa()) {
            System.out.println("\nSinh viên tại chức tại " + noiDaoTao + " của khoa " + khoa.getTenKhoa() + ":");
            for (SinhVienTaiChuc sv : khoa.sinhVienTaiChucTheoNoiDaoTao(noiDaoTao)) {
                sv.output();
            }
        }
    }

    private static void hienThiSVCoDTBHon8TrongKyGanNhat(DanhSachKhoa danhSachKhoa) {
        for (Khoa khoa : danhSachKhoa.getDanhSachKhoa()) {
            System.out.println("\nSinh viên có điểm trung bình học kỳ gần nhất từ 8.0 trở lên của khoa " + khoa.getTenKhoa() + ":");
            for (SinhVien sv : khoa.sinhVienDiemTrungBinhCaoNhatHocKyGanNhat(8.0f)) {
                sv.output();
            }
        }
    }


    private static void hienThiSVCoDTBCaoNhat(DanhSachKhoa danhSachKhoa) {
        for (Khoa khoa : danhSachKhoa.getDanhSachKhoa()) {
            SinhVien sv = khoa.sinhVienDiemTrungBinhCaoNhat();
            if (sv != null) {
                System.out.println("\nSinh viên có điểm trung bình học kỳ cao nhất của khoa " + khoa.getTenKhoa() + ":");
                sv.output();
            } else {
                System.out.println("Không có sinh viên trong khoa " + khoa.getTenKhoa());
            }
        }
    }

    private static void sapXepSVTrongMoiKhoa(DanhSachKhoa danhSachKhoa) {
        for (Khoa khoa : danhSachKhoa.getDanhSachKhoa()) {
            khoa.sapXepSinhVienTheoNamVaLoai();
            System.out.println("\nDanh sách sinh viên của khoa " + khoa.getTenKhoa() + " sau khi sắp xếp:");
            for (SinhVien sv : khoa.getSinhVienList()) {
                sv.output();
            }
        }
    }

    private static void thongKeSVTheoNamVaoHoc(DanhSachKhoa danhSachKhoa) {
        for (Khoa khoa : danhSachKhoa.getDanhSachKhoa()) {
            System.out.println("\nThống kê số lượng sinh viên theo năm vào học của khoa " + khoa.getTenKhoa() + ":");
            for (int nam : khoa.thongKeSoLuongSinhVienTheoNam().keySet()) {
                System.out.println(nam + ": " + khoa.thongKeSoLuongSinhVienTheoNam().get(nam));
            }
        }
    }
    //Dọn màn hình
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

