package example.com.myapplication;

public class AccountModel {
    String name_account,account_number,nominal,expired;

    public AccountModel(String name_account, String account_number, String nominal, String expired) {
        this.name_account = name_account;
        this.account_number = account_number;
        this.nominal = nominal;
        this.expired = expired;
    }

    public String getName_account() {
        return name_account;
    }

    public void setName_account(String name_account) {
        this.name_account = name_account;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }
}
