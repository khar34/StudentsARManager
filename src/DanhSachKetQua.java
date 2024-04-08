
import java.util.ArrayList;

public class DanhSachKetQua {
	ArrayList<KetQuaHocTap> list = new ArrayList<>();

	public void input(KetQuaHocTap ketqua) {
		list.add(ketqua);
	}

	public void outputListKetQua() {
		for (KetQuaHocTap obj : list)
			obj.output();
	}
	public float tinhDiemTB() {
		float tongDiem = 0;
		for (KetQuaHocTap ketQua : list) {
			tongDiem += ketQua.getDiemTrungBinh();
		}
		if (list.size() > 0) {
			return tongDiem / list.size();
		} else {
			return 0;
		}
	}
}




