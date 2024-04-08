
import java.util.Scanner;

public class KetQuaHocTap {
	
	    private String tenHocKy;
	    private float diemTrungBinh;

	    public KetQuaHocTap(){
	    	tenHocKy = null;
	    	diemTrungBinh = 0.0f;
	    }
	    
	    public KetQuaHocTap(String tenHocKy, float diemTrungBinh) {
	        this.tenHocKy = tenHocKy;
	        this.diemTrungBinh = diemTrungBinh;
	    }
	    
	    public KetQuaHocTap(KetQuaHocTap obj) {
	        this.tenHocKy = obj.tenHocKy;
	        this.diemTrungBinh = obj.diemTrungBinh;
	    }

	    public String getTenHocKy() {
	        return tenHocKy;
	    }

	    public void setTenHocKy(String tenHocKy) {
	        this.tenHocKy = tenHocKy;
	    }

	    public float getDiemTrungBinh() {
	        return diemTrungBinh;
	    }

	    public void setDiemTrungBinh(float diemTrungBinh) {
	        this.diemTrungBinh = diemTrungBinh;
	    }
	    
	    public void setDiemTrungBinh() {
	        this.diemTrungBinh = 0;
	    }

	public void input() {
		Scanner sc = new Scanner(System.in);
		boolean inputSuccess = false;

		while (!inputSuccess) {
			try {
				System.out.print("Nhập tên học kì: ");
				setTenHocKy(sc.nextLine());

				System.out.print("Nhập điểm trung bình (thang điểm 10): ");
				float diemTrungBinhInput = Float.parseFloat(sc.nextLine());
				if (diemTrungBinhInput < 0 || diemTrungBinhInput > 10) {
					throw new IllegalArgumentException("Điểm trung bình không hợp lệ. Vui lòng nhập lại.");
				}
				setDiemTrungBinh(diemTrungBinhInput);

				inputSuccess = true; // Đánh dấu là dữ liệu đã nhập thành công
			} catch (NumberFormatException e) {
				System.out.println("Điểm trung bình phải là một số thực từ 0 đến 10.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	    
	    public void output() {
	    	System.out.printf("\n| %-30s | %2.2f |", tenHocKy, diemTrungBinh);
	    }
	    
	    public static void tieude() {
	    	System.out.printf("\n| %-30s | %-4s |","Ten hoc ki", "DTB");
	    }
}
