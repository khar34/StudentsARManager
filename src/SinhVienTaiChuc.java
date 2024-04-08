
import java.util.Scanner;

public class SinhVienTaiChuc extends SinhVien{
	private String noiLKDT;
	
	public SinhVienTaiChuc() {
		super();
		noiLKDT = null;
	}
	
	public SinhVienTaiChuc(String id, String name, String birthday, int namhoc, float diemdauvao, DanhSachKetQua ketqua , String noiLKDT) {
		super(id, name, birthday, namhoc, diemdauvao, ketqua);
		this.noiLKDT = noiLKDT;
	}
	
	public SinhVienTaiChuc(SinhVienTaiChuc obj) {
		super(obj.id, obj.name, obj.birthday, obj.namhoc, obj.diemdauvao, obj.ketqua);
		this.noiLKDT = obj.noiLKDT;
	}

	public String getNoiLKDT() {
		return noiLKDT;
	}

	public void setNoiLKDT(String noiLKDT) {
		this.noiLKDT = noiLKDT;
	}
	@Override
	public void input() {
		super.input();
		Scanner sc = new Scanner(System.in);
		String noiLKDTInput = "";
		do {
			System.out.print("Nhập nơi liên kết đào tạo: ");
			noiLKDTInput = sc.nextLine();
			if (noiLKDTInput.isEmpty() || noiLKDTInput.trim().isEmpty()) {
				System.out.println("Nơi liên kết đào tạo không được để trống. Vui lòng nhập lại.");
			}
		} while (noiLKDTInput.isEmpty());
		setNoiLKDT(noiLKDTInput);
	}
	@Override
	public void output() {
		tieude();
		super.output();
		System.out.printf(" %-30s |", noiLKDT);
	}
	
	public static void tieude() {
		SinhVien.tieude();
		System.out.printf(" %-30s |", "Nơi liên kết đào tạo");
	}
	@Override
	public void outputListKetQua() {
		super.outputListKetQua();
	}
}
