import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class WikipediaTests {

    private final String baseURL = "https://www.wikipedia.org/";
    private static WebDriver driver;

    @BeforeClass
    public static void initWebDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void searchInputTestByIdAndCssSelector(){
        driver.get(baseURL);
        WebElement searchInput = driver.findElement(By.id("searchInput"));
        WebElement submitButton = driver.findElement(By.cssSelector(".pure-button.pure-button-primary-progressive"));

        searchInput.sendKeys("Armenia");
        submitButton.click();

        String pageTitle = driver.getTitle();
        Assert.assertEquals("Armenia - Wikipedia", pageTitle);
    }

    @Test
    public void searchInputTestByIdAndTagName(){
        driver.get(baseURL);
        WebElement searchInput = driver.findElement(By.id("searchInput"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        searchInput.sendKeys("Armenia");
        submitButton.click();

        String pageTitle = driver.getTitle();
        Assert.assertEquals("Armenia - Wikipedia", pageTitle );
    }

    @Test
    public void searchInputTestByNameAndXpath(){
        driver.get(baseURL);
        WebElement searchInput = driver.findElement(By.name("search"));
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class, 'pure-button') and contains(@class, 'pure-button-primary-progressive')]"));
        searchInput.sendKeys("Armenia");
        submitButton.click();
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Armenia - Wikipedia", pageTitle);
    }

    @Test
    public void clickEnglishLinkByIdAndXPathAndLinkText(){
        driver.get(baseURL);
        WebElement englishLink = driver.findElement(By.xpath("//a[strong[text()='English']]"));
        englishLink.click();

        WebElement titleBox = driver.findElement(By.id("articlecount"));
        WebElement englishText = titleBox.findElement(By.linkText("English"));

        Assert.assertEquals("English",englishText.getText());
    }

    @Test
    public void clickEnglishLinkByIdAndXPathAndLinkTextWithEnterKey(){
        driver.get(baseURL);
        WebElement englishLink = driver.findElement(By.xpath("//a[strong[text()='English']]"));
        englishLink.click();

        WebElement searchInput = driver.findElement(By.className("cdx-text-input__input"));
        searchInput.sendKeys("Armenia");
        searchInput.sendKeys(Keys.ENTER);

        String pageTitle = driver.getTitle();
        Assert.assertEquals("Armenia - Wikipedia", pageTitle);
    }

    @Test
    public void clickTalkTest(){
        driver.get(baseURL);
        WebElement englishLink = driver.findElement(By.xpath("//a[strong[text()='English']]"));
        englishLink.click();

        WebElement searchInput = driver.findElement(By.className("cdx-text-input__input"));
        searchInput.sendKeys("Armenia");
        searchInput.sendKeys(Keys.ENTER);

        WebElement talkLink = driver.findElement(By.xpath("//li[@id='ca-talk']//a//span[contains(text(),'Talk')]"));
        talkLink.click();

        WebElement firstHeading = driver.findElement(By.id("firstHeading"));
        Assert.assertEquals("Talk:Armenia", firstHeading.getText());
    }

    @Test
    public void searchInputInsideTalkTest(){
        driver.get(baseURL);
        WebElement englishLink = driver.findElement(By.xpath("//a[strong[text()='English']]"));
        englishLink.click();

        WebElement searchInput = driver.findElement(By.className("cdx-text-input__input"));
        searchInput.sendKeys("Armenia");
        searchInput.sendKeys(Keys.ENTER);

        WebElement talkLink = driver.findElement(By.xpath("//li[@id='ca-talk']//a//span[contains(text(),'Talk')]"));
        talkLink.click();

        WebElement talkSearchInput = driver.findElement(By.cssSelector(".mw-searchInput.searchboxInput.mw-ui-input.mw-ui-input-inline"));
        talkSearchInput.sendKeys("Hello");
        talkSearchInput.sendKeys(Keys.ENTER);

        WebElement firstHeading = driver.findElement(By.id("firstHeading"));
        Assert.assertEquals("Search results", firstHeading.getText());
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}