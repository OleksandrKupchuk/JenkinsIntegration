package patterns.pageobject.orangehrm;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private SelenideElement title = $x("//h6");

    public SelenideElement getTitle(){
        return title.shouldBe(Condition.visible);
    }
}
