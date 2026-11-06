package shop.payment;

public class EWalletPayment implements RefundablePayment{
    private final String provider;
    private long balance; 

    public EWalletPayment(String provider, long balance){
        if(provider == null || provider.isBlank()){
            throw new IllegalArgumentException("provider must not be blank");
        }
        if(balance < 0){
            throw new IllegalArgumentException("balance must not be negative");
        }
        this.provider = provider;
        this.balance = balance;
    }

    public String getName(){
        return "E-wallet " + provider;
    }

    public long getBalance(){
        return balance;
    }

    @Override
    public boolean pay(long amount) {
        // TODO Auto-generated method stub
        if(amount <= 0 || amount > balance){
            return false;
        }
        balance -= amount;
        return true; 
    }

    @Override
    public boolean refund(long amount) {
        // TODO Auto-generated method stub
        if(amount <= 0){
            return false;
        }
        balance += amount;
        return true; 
    }
}
