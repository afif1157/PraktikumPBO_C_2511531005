package shop.payment;

public class CreditCardPayment implements RefundablePayment, InstallmentPayment{
    private final String cardHolder;
    private final long creditLimit;
    private long usedCredit;

    public CreditCardPayment(String cardHolder, long creditLimit){
        if(cardHolder == null){
            throw new IllegalArgumentException("Card holder must not be blank");
        }
        if(creditLimit < 0){
            throw new IllegalArgumentException("Credit limit must not be negative");
        }
        this.cardHolder = cardHolder;
        this.creditLimit = creditLimit;
        this.usedCredit = 0;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Kartu Kredit (" + cardHolder + ")";
    }

    @Override
    public boolean pay(long amount) {
        // TODO Auto-generated method stub
        if(amount <= 0) return false;
        if(usedCredit + amount > creditLimit) return false;

        usedCredit += amount;
        return true;
    }

    @Override
    public boolean refund(long amount) {
        if(amount <= 0) return false;
        if(amount > usedCredit) return false;
        usedCredit -= amount;
        return true;
    }

    @Override
    public int maxInstallmentMonths() {
        return 12;
    }

    public long getUsedCredit() {
        return usedCredit;
    }



}
