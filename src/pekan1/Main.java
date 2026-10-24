package pekan1;
import java.util.ArrayList;
import java.util.Scanner;



public class Main{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Akun");
			System.out.println("6. Cetak Mutasi");
			System.out.println("7. Simulasi Akhir Bulan (khusus tabungan)");
			System.out.println("8. Transfer ke rekening lain");
			System.out.println("0. Keluar");
			System.out.print("Pilih menu: ");
			
			
			int pilihan = bacaInt(input, "Pilih menu: ");

			
				switch (pilihan) {
				case 1:
					System.out.print("Masukkkan No Rekening: ");
					String no = input.nextLine();
					System.out.print("Masukkan Nama Pemilik: ");
					String nama = input.nextLine();
					System.out.print("Masukkan Saldo Awal: ");
					double saldo = bacaDouble(input, "");
					
					if(saldo < 50000) {
						System.out.println("Gagal: Saldo awal minimal Rp.50000!");
						break;
					};
					
					System.out.println("Masukkkan PIN: ");	
					String pin = input.nextLine();
					System.out.println("Pilih Produk : \n 1. Tabungan Umum \n 2. Giro Bisnis");
					System.out.println("Pilihan : ");
					int produk = input.nextInt();
					input.nextLine();
					
					Rekening rekeningbaru = null;
					
					if(produk == 1) {
						System.out.println("Suku Bunga (%) :");
						double sukuBunga = input.nextDouble();
						input.nextLine();
						rekeningbaru = new RekeningTabungan(no, nama, saldo, pin, sukuBunga);
					}else if(produk == 2) {
						System.out.println("Batas Overdraft :");
						double batas = input.nextDouble();
						input.nextLine();
						rekeningbaru = new RekeningGiro(no, nama, saldo, pin, batas);
					}else {
						System.out.println("Pilihan produk tidak valid!");
						break;
					}
					
					daftarRekening.add(rekeningbaru);
					System.out.println("Rekening berhasil dibuat!");
					
					
					daftarRekening.add(rekeningbaru);
					akunAktif = rekeningbaru;
					break;
					
				case 2:
					if(akunAktif == null) {
						System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
					}else {
						double setor = bacaDouble(input, "Masukkan nominal: ");
						akunAktif.setorTunai(setor);
					}
					break;
					
				case 3:
					if(akunAktif == null) {
						System.out.println("Eror: Mohon maaf, Anda belum memiliki nomor rekening!");
					}
					
					if(akunAktif.isBlocked()) {
						System.out.println("Akses ditolak: akun anda sedang terblokir!");
						break;
					}
					else {
						System.out.println("Masukkan PIN: ");
						String pinInput = input.nextLine();
						if(akunAktif.otentikasi(pinInput)) {
							double tarik = bacaDouble(input, "Masukkan nominal: ");
							akunAktif.tarikTunai(tarik);
						}else {
							System.out.println("Akses ditolak: PIN yang anda masukkan salah!");
						}
					}
					break;
					
				case 4:
					if(akunAktif == null) {
						System.out.println("Error: Anda belum membuka rekening!");
					}else {
						akunAktif.cekInformasi();
					}
					break;
				
				case 5:
					if(daftarRekening.isEmpty()) {
						System.out.println("Error: Belum ada rekening yang terdaftar!");
					}
					else {
						System.out.println("Masukkan No Rekening yang ingin diaktifkan: ");
						String cariNomor = input.nextLine();
						
						Rekening ditemukan = null;
						for(Rekening r : daftarRekening) {
							if(r.getNomorRekening().equals(cariNomor)) {
								ditemukan = r;
								break;
							}
						}
						
						if(ditemukan != null) {
							akunAktif = ditemukan;
							System.out.println("Berhasil beralih ke Rekening atas nama " + akunAktif.getNamaPemilik());
						}
						else {
							System.out.println("Error : Nomor rekening tidak ditemukan!");
						}
					}
					break;
					
				case 6:
					if(akunAktif == null) {
						System.out.println("Error: Anda belum membuka rekening!");
					}else {
						System.out.println("Masukkan PIN: ");
						String pinInput = input.nextLine();
						if(akunAktif.otentikasi(pinInput)) {
							akunAktif.cetakMutasi();
						}else {
							System.out.println("Akses ditolak: PIN yang anda masukkan salah!");
						}
						
						
					}
					break;
					
				case 7:
					if(akunAktif instanceof RekeningTabungan) {
						RekeningTabungan tab = (RekeningTabungan) akunAktif;
						tab.tambahBungaAkhirBulan();
					}else {
						System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan");
					}
					break;
					
				case 8:
					if(akunAktif == null) {
						System.out.println("Error: Anda belum membukan rekening!");
					}
					else {
						System.out.println("Masukkan nomor rekening tujuan : ");
						String rt = input.nextLine();
						Rekening ditemukan = null;
						for(Rekening r : daftarRekening) {
							if(r.getNomorRekening().equals(rt)) {
								ditemukan = r;
								break;
							}
						}
						
						if(ditemukan != null) {
							System.out.println("Masukkan nominal transfer : ");
							double nominal = input.nextDouble();
							input.nextLine();
							System.out.println("masukkan PIN : ");
							String pinInput = input.nextLine();
							if(akunAktif.otentikasi(pinInput) == true) {
								akunAktif.Transfer(ditemukan, nominal, pinInput);
								}
							}else {
							System.out.println("Error: Rekening tidak ditemukan!");
						}
					}
					break;
					
				case 0:
					isRunning = false;
					System.out.println("Sistem ditutup. Terima kasih!");
					break;
					
				default:
					System.out.println("Pilihan tidak valid!");
				}

		}
		input.close();
	}
	
	public static int bacaInt (Scanner input, String pesan) {
		while(true) {
			System.out.println(pesan);
			try {
				int nilai = Integer.parseInt(input.nextLine().trim());
				return nilai;
			}
			catch (NumberFormatException e) {
				System.out.println("Error : Pilihan harus berupa angka!");
			}
		}
	}
	
	public static double bacaDouble (Scanner input, String pesan) {
		while(true) {
			System.out.println(pesan);
			try {
				double nilai = Double.parseDouble(input.nextLine().trim());
				return nilai;
			}
			catch (NumberFormatException e) {
				System.out.println("Error : Nominal harus angka!");
			}
		}
	}
}