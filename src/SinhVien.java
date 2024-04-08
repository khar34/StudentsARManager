import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.Set;

public class SinhVien {
	
	protected String id;
	protected String name;
	protected String birthday;
	protected int namhoc;
	protected float diemdauvao;
	DanhSachKetQua ketqua = new DanhSachKetQua();

	private static Set<String> idSet = new HashSet<>(); // Set để lưu trữ các mã số sinh viên đã nhập


	// Constructor mặc định
	public SinhVien() {
		id = name = birthday = null;
		namhoc = 0;
		diemdauvao = 0.0f;
	}
	
	// Constructor
	public SinhVien(String id, String name, String birthday, int namhoc, float diemdauvao, DanhSachKetQua ketqua) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.namhoc = namhoc;
		this.diemdauvao = diemdauvao;
		this.ketqua = ketqua;
	}
	
	// Copy Constructor
	public SinhVien(SinhVien obj) {
		this.id = obj.id;
		this.name = obj.name;
		this.birthday = obj.birthday;
		this.namhoc = obj.namhoc;
		this.diemdauvao = obj.diemdauvao;
		this.ketqua = obj.ketqua;
	}
	
	// Getter and Setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getNamhoc() {
		return namhoc;
	}
	public void setNamhoc(int namhoc) {
		this.namhoc = namhoc;
	}
	public float getDiemdauvao() {
		return diemdauvao;
	}
	public void setDiemdauvao(float diemdauvao) {
		this.diemdauvao = diemdauvao;
	}

	// Methods
	public void input() {
		Scanner sc = new Scanner(System.in);
		boolean inputSuccess = false;
		boolean idEntered = false;

		while (!inputSuccess) {
			try {
				String idInput = "";
				while (idInput.isEmpty() || idInput.trim().isEmpty() || idSet.contains(idInput)) {
					System.out.print("Nhập mã số sinh viên: ");
					idInput = sc.nextLine().trim();
					if (idInput.isEmpty()) {
						System.out.println("Mã số sinh viên không được để trống. Vui lòng nhập lại.");
						continue;
					}
					if (idSet.contains(idInput)) {
						System.out.println("Mã số sinh viên đã tồn tại trong danh sách. Vui lòng nhập lại.");
					}
				}
				setId(idInput);
				idSet.add(idInput); // Thêm mã số sinh viên vào Set sau khi đã kiểm tra
				// Các bước nhập thông tin khác của sinh viên ở đây
				inputSuccess = true; // Đánh dấu việc nhập thông tin thành công

				String nameInput = "";
				while (nameInput.isEmpty() || nameInput.trim().isEmpty()) {
					System.out.print("Nhập họ và tên sinh viên: ");
					nameInput = sc.nextLine();
					if (nameInput.isEmpty()) {
						throw new IllegalArgumentException("Họ và tên sinh viên không được để trống.");
					}
				}
				setName(nameInput);

				String birthdayInput;
				Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
				Matcher matcher = null;
				boolean isValidDate = false;
				boolean isOver18 = false;

				do {
					System.out.print("Nhập ngày tháng năm sinh (DD/MM/YYYY): ");
					birthdayInput = sc.nextLine();
					if (birthdayInput.isEmpty() || birthdayInput.trim().isEmpty()) {
						System.out.println("Ngày tháng năm sinh không được để trống.");
						continue;
					}
					matcher = pattern.matcher(birthdayInput);
					if (!matcher.matches()) {
						System.out.println("Định dạng ngày tháng năm không hợp lệ. Vui lòng nhập lại.");
						continue;
					}

					// Kiểm tra ngày, tháng và năm hợp lệ
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate birthdayDate = LocalDate.parse(birthdayInput, formatter);
					LocalDate today = LocalDate.now();
					long age = ChronoUnit.YEARS.between(birthdayDate, today);
					if (age < 18) {
						System.out.println("Sinh viên phải đủ 18 tuổi trở lên. Vui lòng nhập lại.");
						continue;
					}

					isValidDate = true; // Ngày tháng năm sinh hợp lệ
					isOver18 = true; // Tuổi đủ 18 tuổi trở lên

				} while (!isValidDate || !isOver18); // Lặp lại nếu ngày tháng năm sinh không hợp lệ hoặc tuổi không đủ

				setBirthday(birthdayInput);

				int namhocInput;
				boolean namhocSuccess = false;

				do {
					System.out.print("Nhập vào năm học: ");
					String namhocStr = sc.nextLine();

					if (namhocStr.isEmpty() || namhocStr.trim().isEmpty()) {
						System.out.println("Năm học không được để trống.");
						continue;
					}

					try {
						namhocInput = Integer.parseInt(namhocStr);
						int minNamhoc = Integer.parseInt(birthdayInput.substring(6)) + 18; // Tính năm học tối thiểu
						if (namhocInput < minNamhoc) {
							System.out.println("Năm học không hợp lệ. Năm học phải từ " + minNamhoc + " trở đi.");
							continue;
						}

						setNamhoc(namhocInput);
						namhocSuccess = true;
					} catch (NumberFormatException e) {
						System.out.println("Năm học phải là một số nguyên dương.");
					}
				} while (!namhocSuccess);

				float diemdauvaoInput = 0;
				boolean isDiemdauvaoValid = false;

				while (!isDiemdauvaoValid) {
					System.out.print("Nhập điểm đầu vào: ");
					String diemdauvaoStr = sc.nextLine();
					if (diemdauvaoStr.isEmpty() || diemdauvaoStr.trim().isEmpty()) {
						System.out.println("Điểm đầu vào không được để trống.");
						continue; // Quay lại đầu vòng lặp để yêu cầu nhập lại
					}
					try {
						diemdauvaoInput = Float.parseFloat(diemdauvaoStr);
						if (diemdauvaoInput < 0 || diemdauvaoInput > 30) {
							throw new IllegalArgumentException();
						}
						isDiemdauvaoValid = true; // Đánh dấu điểm đầu vào hợp lệ, kết thúc vòng lặp
					} catch (NumberFormatException e) {
						System.out.println("Điểm đầu vào phải là một số thực từ 0 đến 30.");
					} catch (IllegalArgumentException e) {
						System.out.println("Điểm đầu vào không hợp lệ. Vui lòng nhập lại.");
					}
				}
				setDiemdauvao(diemdauvaoInput);

				System.out.println("Nhập danh sách kết quả học tập: ");
				inputSuccess = true;

				KetQuaHocTap result;
				byte chon;
				do {
					System.out.println("1. Thêm kết quả 1 học kì.");
					System.out.println("0. Kết thúc.");
					System.out.print("Chọn: ");
					chon = sc.nextByte();
					sc.nextLine();

					if (chon == 1) {
						result = new KetQuaHocTap();
						result.input();
						ketqua.input(result);
					} else if (chon != 0) {
						System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
					}
				} while (chon != 0);

			} catch (Exception e) {
				System.out.println("Đã xảy ra lỗi khi nhập dữ liệu: " + e.getMessage());
				System.out.println("Vui lòng nhập lại.");
			}
		}
	}


	public void output() {


			int maxLengthId = 10;  // Độ dài tối đa của mã số
			int maxLengthName = 30;  // Độ dài tối đa của họ và tên
			int maxLengthBirthday = 12;  // Độ dài tối đa của ngày sinh
			int maxLengthNamhoc = 8;  // Độ dài tối đa của năm học
			int maxLengthDiemdauvao = 6;  // Độ dài tối đa của ĐĐV
			System.out.printf("\n| %-" + maxLengthId + "s | %-" + maxLengthName + "s | %-" + maxLengthBirthday + "s | %" + maxLengthNamhoc + "d | %" + maxLengthDiemdauvao + ".2f |", id, name, birthday, namhoc, diemdauvao);

	}


	public static void tieude() {

		System.out.printf("\n| %-10s | %-30s | %-12s | %8s | %6s |", "Mã số", "Họ và tên", "Ngày sinh", "Năm học", "ĐĐV");
	}


	public void outputListKetQua() {
		KetQuaHocTap.tieude();
		ketqua.outputListKetQua();
	}




}
