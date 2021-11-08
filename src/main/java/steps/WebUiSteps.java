package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Затем;

import static com.codeborne.selenide.Selenide.$x;

public class WebUiSteps {
    protected final String buttonSelector = "//*[contains(@class,'btn')][contains(.,'%s')]";
    protected final String objectInfoButton = "//*[contains(@class,'collapse-item')][contains(.,'%s')]";
    protected final String resultList = "//div[@class='item__text'][contains(.,'%s')]";
    private final String ssoidAuthorization = "/api/auth/login-like?ssoid=";

    @Если("Открыть url {string}, затем авторизоваться через ssoid{string}")
    public void open(String url, String ssoid) {
        Selenide.open(url, "", "gorod", "pro15gorod");
        Selenide.open(url + ssoidAuthorization + ssoid);
    }

    @Затем("Кликнуть на элемент {string}")
    public void clickOnELement(String text) {
        String selector = String.format(buttonSelector, text);
        $x(selector).shouldBe(Condition.enabled).click();
    }

    @Затем("Найти объект {string} по адресу {string}")
    public void findObject(String category, String object) {
        String chooseCategoryInList = String.format(resultList, category);
        $x("//input[@placeholder='Поиск адреса, объекта, маршрута']").val(object);
        $x(chooseCategoryInList).click();
    }

    @Затем("На странице имеется элемент {string}")
    public void objectInfoAssert(String infoList) {
        String objectInfo = String.format(objectInfoButton, infoList);
        $x(objectInfo).scrollTo().click();
    }
}
