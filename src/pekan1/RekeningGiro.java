package pekan1;

public class RekeningGiro extends Rekening{
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	public boolean tarikTunai(double nominal) {
		if(nominal <= 0) {
			System.out.println("Gagal: nominal terik giro harus lebih dari Rp.0");
			return false;
		}else if(nominal > (saldo + batasOverdraft)) {
			System.out.println("Transaksi gagal: melebiihi batas overdraft! maksimal penarikan: Rp." + (saldo + batasOverdraft));
			return false;
		}else {
			saldo -= nominal;
			String idTrx = "TRX-G-" + System.currentTimeMillis();
			riwayatTransaksi.add(new Transaksi(idTrx, "Debit Giro", nominal));
			
			System.out.println("Tarik tunai giro Rp." + nominal + " berhasil. saldo saat ini: Rp. " + saldo);
			return true;
		}
	}
	
	public double getBatasOverdraft() {
		return batasOverdraft;
	}

}
