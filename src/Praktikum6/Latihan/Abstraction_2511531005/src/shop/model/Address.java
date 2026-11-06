package shop.model;

public final class Address {
    private final String street, city, postalCode;

    public Address(String street, String city, String postalCode){
        if(street == null || street.isBlank()){
            throw new IllegalArgumentException("street must not be blank");
        }
        if(city == null || city.isBlank()){
            throw new IllegalArgumentException("city must not be blank");
        }
        if(postalCode == null || postalCode.isBlank()){
            throw new IllegalArgumentException("postcode must not be blank");
        }
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getStreet(){
        return street;
    }
    public String getCity(){
        return city;
    }
    public String getPostalCode(){
        return postalCode;
    }

    @Override
    public String toString() {
        return street + ", " + city + " " +postalCode;
    }
}
