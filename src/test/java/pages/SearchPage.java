package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchPage {
    boolean actualHotelName;

   By searchFieldId = By.id("ss");
   String searchButtonCss = ".sb-searchbox__button";
   String scoresLocator = "//span[contains(text(),'%s')]/ancestor::div[contains(@class, 'sr_item')]";
   String hotelNameLocator ="//span[contains(text(),'%s')]";
    public SearchPage openPage() {
        open("https://www.booking.com/searchresults.en-gb.html#map_closed");
        return new SearchPage();

    }

    public SearchPage search(String hotelName) {
        $(searchFieldId).sendKeys(hotelName);
        $(searchFieldId).click();
        $(searchButtonCss).click();
        return this;
    }

    public SearchPage getHotels(String hotelName) {
        $(searchFieldId).click();
        actualHotelName =$(By.xpath(String.format(hotelNameLocator, hotelName ))).isDisplayed();
        return this;
    }

    public void validationHotelRating(String hotelName, String hotelScore) {
        String actualScore = $(By.xpath(String.format(scoresLocator, hotelName ))).getAttribute("data-score");
        Assert.assertEquals(hotelScore, actualScore);


    }
}
