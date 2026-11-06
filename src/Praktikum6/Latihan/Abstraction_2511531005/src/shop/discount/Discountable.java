package shop.discount;

public interface Discountable {

    int discounPercent();

    default long applyDiscount(long price){
        return price - percentOf(price, safePercent());
    }

    static long percentOf(long amount, int percent){
        return amount * percent/ 100;
    }

    private int safePercent(){
        int percent = discounPercent();
        if(percent < 0){
            return 0;
        }
        return Math.min(percent, 100);
    }
}
