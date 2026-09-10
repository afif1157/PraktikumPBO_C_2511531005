package pekan1;

import java.util.Scanner;



public class Main{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
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
					
					akunAktif = new Rekening(no, nama, saldo);
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
					if(akunAktif == null) {
						System.out.println("Eror: Mohon maaf, Anda belum memiliki nomor rekening!");
					}else {
						System.out.println("Masukkan nominal tarik: ");
						double tarik = bacaDouble(input, "Masukkan nominal: ");
						akunAktif.tarikTunai(tarik);
					}
					break;
					
				case 4:
					if(akunAktif == null) {
						System.out.println("Error: Anda belum membuka rekening!");
					}else {
						akunAktif.cekInformasi();
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