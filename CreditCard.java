package oopprojecthalilkayra;


import java.util.Date;

public class CreditCard {
    
    //datafield
    private String CardNumber;
    private String CardOwnerName;
    private String SecurityCode;
    private Date expirationDate;

   
    //constructor
    public CreditCard(String CardNumber, String CardOwnerName, String SecurityCode, Date expirationDate) {
        this.CardNumber = CardNumber;
        this.CardOwnerName = CardOwnerName;
        this.SecurityCode = SecurityCode;
        this.expirationDate = expirationDate;
    }

    // Getter ve Setter metodları
    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    public String getCardOwnerName() {
        return CardOwnerName;
    }

    public void setCardOwnerName(String CardOwnerName) {
        this.CardOwnerName = CardOwnerName;
    }

    public String getSecurityCode() {
        return SecurityCode;
    }

    public void setSecurityCode(String SecurityCode) {
        this.SecurityCode = SecurityCode;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
}