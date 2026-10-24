package pekan1;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ArrayList;
 
public class Rekening {

	private String nomorRekening;
	private String namaPemilik;
	protected double saldo;
	private String pin;
	private int percobaanGagal;
	private boolean isBlocked;
	
	NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	protected ArrayList<Transaksi> riwayatTransaksi;
 	
	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		if(pinAwal.length() == 6) {
			this.pin = pinAwal;
		}else {
			System.out.println("Peringatan: PIN harus 6 digit! menggunakan PIN default 123456");
			this.pin = "123456";
		}
		
		this.percobaanGagal = 0;
		this.isBlocked = false;
		
		this.riwayatTransaksi = new ArrayList<>();
		
		if(saldoAwal < 50000) {
			System.out.println("Saldo awal harus > Rp.50.000,00");
		}
		else {
			System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo " + formatRupiah.format(saldo));
		}
		
	}  
	
	public String getNomorRekening() {
		return nomorRekening;
	}
	public String getNamaPemilik() {
		return namaPemilik;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	
	public boolean otentikasi(String inputPin) {
		if(isBlocked) {	
			System.out.println("Akun terblokir: silakan hubungi admin untuk membuka blokir!");
			return false;
		}
		if(this.pin.equals(inputPin)) {
			percobaanGagal = 0;
			return true;
		}else {
			percobaanGagal++;
			int sisaPercobaan = 3 - percobaanGagal;
			
			if(percobaanGagal >= 3) {
				isBlocked = true;
				System.out.println("Akun terblokir karena 3 kali salah pin!");
			}else {
				System.out.println("PIN salah! sisa percobaan "+ sisaPercobaan);
			}
			return false;
		}
	}
	
	public void setorTunai(double nominal) {
		if(nominal > 500000) {
			System.out.println("maksimal nominal setor Rp.500.000");
		}
		else if (nominal >= 10000) {
			saldo += nominal;  	
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor tunai " + formatRupiah.format(nominal) + " berhasil. Saldo saat ini: " + formatRupiah.format(saldo) );
			
		}else {
			System.out.println("Gagal: Nominal setor harus lebih dari Rp.10.000,00!");
		}
	}
	
	public boolean tarikTunai(double nominal) {
		if(nominal < 10000) {
			System.out.println("Gagal: minimal nominal penarikan Rp.10.000,00");
			return false;
		}
		else if(nominal > (saldo-10000)) {
			System.out.println("Gagal:saldo anda " + formatRupiah.format(saldo)+ " minimal sisa saldo Rp.10.000,00");
			return false;
		}
		else if(nominal >= 10000) {   
			saldo -= nominal;
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Tarik tunai " + formatRupiah.format(nominal) + " berhasil. Saldo saat ini: " + formatRupiah.format(saldo) );
			return true;
		}
		else {
			System.out.println("Gagal: Saldo tidak mencukupi!");
			return false;
		}
	}
	
	public void Transfer(Rekening Tujuan, double nominal, String pinInput) {
		if(otentikasi(pinInput) == false) {
			System.out.println("PIN yang anda masukkan salah!");
			return;
		}
		boolean berhasiTarik = this.tarikTunai(nominal);
		if(berhasiTarik) {
			Tujuan.setorTunai(nominal);
			System.out.println("Transfer Rp" + nominal + " berhasil!");
		}else {
			System.out.println("Transfer gagal!");
		}
	} 
	
	public void cekInformasi() { 
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir : " + formatRupiah.format(saldo) );
		System.out.println("-----------------------");   
	}
	
	public void cetakMutasi() {
		if(riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini!");
			return;
		}
		for(Transaksi t : riwayatTransaksi) {
				t.cetakDetail();
		}
	}	
}


