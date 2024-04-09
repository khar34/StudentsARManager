
public class SinhVienChinhQuy extends SinhVien {

    public SinhVienChinhQuy() {
        super();
    }

    public SinhVienChinhQuy(String id, String name, String birthday, int namhoc, float diemdauvao, DanhSachKetQua ketqua) {
        super(id, name, birthday, namhoc, diemdauvao, ketqua);
    }

    public SinhVienChinhQuy(SinhVienChinhQuy obj) {
    	super(obj.id, obj.name, obj.birthday, obj.namhoc, obj.diemdauvao, obj.ketqua);
    }

    @Override
    public void input() {
        super.input();
    }

    @Override
    public void output() {
        tieude();
        super.output();
        outputListKetQua();
    }

    public static void tieude() {
        SinhVien.tieude();
    }
    
    @Override
    public void outputListKetQua() {
    	super.outputListKetQua();
    }
}
