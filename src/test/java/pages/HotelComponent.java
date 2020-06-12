package pages;

import com.codeborne.selenide.SelenideElement;

public class HotelComponent {
    SelenideElement name;
    SelenideElement rating;
    SelenideElement address;

    public HotelComponent (SelenideElement name, SelenideElement rating, SelenideElement address) {
        this.name = name;
        this.rating = rating;
        this.address = address;
    }

    public void clickToMap () {
        address.click();
    }
    //TODO add all methods to interact with elements of particular hotel
}
