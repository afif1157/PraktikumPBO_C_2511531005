package shop.model;

import shop.discount.Discountable;

public class DigitalProduct extends Product implements Discountable{
    private final String downloadUrl;
    private final int DIGITAL_DISCOUNT_PERCENT = 10;

    public DigitalProduct(String sku, String name, long price, String downloadUrl){
        super(sku, name, price);

        if(downloadUrl == null){
            throw new IllegalArgumentException("URL must not be null");
        }
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl(){
        return downloadUrl;
    }

    public String category(){
        return "Digital";
    }

    @Override
    public int discounPercent() {
        // TODO Auto-generated method stub
        return DIGITAL_DISCOUNT_PERCENT;
    }

    @Override
    public long getFinalPrice() {
        // TODO Auto-generated method stub
        return applyDiscount(getPrice());
    }
}
