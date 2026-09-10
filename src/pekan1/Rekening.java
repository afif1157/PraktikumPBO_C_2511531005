package pekan1;
import java.text.NumberFormat;
import java.util.Locale;
 
public class Rekening {

	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		if(saldoAwal < 50000) {
			System.out.println("Saldo awal harus > Rp.50.000,00");
		}
		else {
			System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo " + formatRupiah.format(saldo));
		}
		
	}  
	
	public void setorTunai(double nominal) {
		
		if (nominal >= 10000) {
			saldo += nominal;  	
			System.out.println("Setor tunai " + formatRupiah.format(nominal) + " berhasil. Saldo saat ini: " + formatRupiah.format(saldo) );
			
		}else {
			System.out.println("Gagal: Nominal setor harus lebih dari Rp.10.000,00!");
		}
	}
	
	public void tarikTunai(double nominal) {
		
		if(nominal < 10000) {
			System.out.println("Gagal: minimal nominal penarikan Rp.10.000,00");
		}
		else if(nominal > (saldo-10000)) {
			System.out.println("Gagal:saldo anda " + formatRupiah.format(saldo)+ " minimal sisa saldo Rp.10.000,00");
		}
		else if(nominal >= 10000) {   
			saldo -= nominal;
			System.out.println("Tarik tunai " + formatRupiah.format(nominal) + " berhasil. Saldo saat ini: " + formatRupiah.format(saldo) );
		}
		else {
			System.out.println("Gagal: Saldo tidak mencukupi!");
		}
	}
	
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir : " + formatRupiah.format(saldo) );
		System.out.println("-----------------------");   
	}
	
}


