package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;

public class BookingSearchSteps {

    String hotelName;
    String actualScore;
    private SearchPage searchPage;

    @Given("Keyword for search is {string}")
    public void keywordForSearchIs(String hotelName){
        searchPage = new SearchPage();
        this.hotelName = hotelName;
        searchPage.openPage();
        searchPage.search(hotelName);
    }

    @When("User does search {string}")
    public void userDoesSearch(String hotelName) {
        this.hotelName = hotelName;
        searchPage = new SearchPage();

    }

    @Then("{string} is displayed on the first page")
    public void wikiPageIsDisplayedOnTheFirstPage(String hotelFullName) {
        searchPage.getHotels(hotelFullName);
    }

    @And("Score score of {string} is {string}")
    public void scoreIs(String hotelName,String score) {
        searchPage.validationHotelRating(hotelName, score);
    }
}

