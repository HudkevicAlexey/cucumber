package steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class BookingSearchSteps {
    String hotelName;
    private SearchPage searchPage;

    @Given("Keyword for search is {string}")
    public void keywordForSearchIs(String hotelName) {
        Configuration.clickViaJs = true;
        searchPage = new SearchPage();
        this.hotelName = hotelName;
    }

    @When("User does search")
    public void userDoesSearch() {
        searchPage.openPage();
        searchPage.search(hotelName);
    }

    @Then("{string} is displayed on the first page")
    public void wikiPageIsDisplayedOnTheFirstPage(String hotelFullName) {
        assertThat("Отель отсутствует на странице", searchPage.getHotels(), hasItem(hotelFullName));
    }

    @And("Score of {string} is {string}")
    public void scoreIs(String hotelName, String score) {
        searchPage.hotelRatingShouldBeCorrect(hotelName, score);
    }
}

