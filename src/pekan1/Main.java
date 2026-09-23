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
					System.out.println("Masukkkan PIN: ");
					String pin = input.nextLine();
					
					Rekening rekeningBaru = new Rekening(no, nama, saldo, pin);
					daftarRekening.add(rekeningBaru);
					akunAktif = rekeningBaru;
					break;
					
				case 2:
					if(akunAktif == null) {
						System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
					}else {
						System.out.println("Masukkan nominal setor: ");
						double setor = bacaDouble(input, "Masukkan nominal: ");
						akunAktif.setorTunai(setor);
					}
					break;
					
				case 3:
					if(akunAktif.isBlocked()) {
						System.out.println("Akses ditolak: akun anda sedang terblokir!");
						break;
					}
					if(akunAktif == null) {
						System.out.println("Eror: Mohon maaf, Anda belum memiliki nomor rekening!");
					}else {
						System.out.println("Masukkan PIN: ");
						String pinInput = input.nextLine();
						if(akunAktif.otentikasi(pinInput)) {
							System.out.println("Masukkan nominal tarik: ");
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