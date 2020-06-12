package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SearchPage {
    By searchFieldId = By.id("ss");
    String searchButtonCss = ".sb-searchbox__button";
    String hotelsNameLocator = ".sr-hotel__name";
    String hotelComponentLocator = ".sr_item";
    String hotelNameLocator = "//*[contains(text(),'%s') and contains(@class, 'sr-hotel__name')]";
    String scoresLocator = hotelNameLocator + "/ancestor::div[contains(@class, 'sr_item')]//*[contains(@class, 'bui-review-score__badge')]";
    String numberOfPropertiesLocator = ".sorth1";
    Map<String, HotelComponent> hotelComponents = new HashMap<>();

    public SearchPage openPage() {
        open("https://www.booking.com/searchresults.en-gb.html");
        return this;
    }

    public SearchPage search(String hotelName) {
        $(searchFieldId).sendKeys(hotelName);
        $(searchButtonCss).click();
        $(numberOfPropertiesLocator).shouldBe(Condition.visible);
        initializeResults();
        return this;
    }

    public SearchPage initializeResults() {
        List<SelenideElement> names = $$(hotelComponentLocator);
        List<SelenideElement> ratings = $$(".bui-review-score__badge");
        List<SelenideElement> addresses =  $$("[data-google-track=\"Click/Action: sr_map_link_used\"]");
        for (int i = 0; i < names.size(); i++) {
            hotelComponents.put(names.get(i).getText(),
                    new HotelComponent(
                            names.get(i),
                            ratings.get(i),
                            addresses.get(i)
                    ));
        }
        return this;
    }

    public List<String> getHotels() {
        List<String> hotels = new ArrayList<>();
        for(SelenideElement element: $$(hotelsNameLocator)) {
            hotels.add(element.getText());
        }
        return hotels;
        //return $$(hotelsNameLocator).stream().map(SelenideElement::getText).collect(Collectors.toList());
    }

    public void hotelRatingShouldBeCorrect(String hotelName, String hotelScore) {
        String actualScore = $(By.xpath(String.format(scoresLocator, hotelName))).getText().trim();
        assertThat("Рейтинг отеля некорректный", actualScore, is(hotelScore));
    }
}
