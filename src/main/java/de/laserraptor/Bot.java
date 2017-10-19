package de.laserraptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.mukprojects.giphy4j.exception.GiphyException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Bot {

	private String fireFoxUserProfile;
	private WebDriver driver;
	private GifProvider gifProvider;


	public Bot(String firefoxUserProfile) {
		this.fireFoxUserProfile = firefoxUserProfile;
	}

	public void spam(String chatName, String searchTag, int times) throws GiphyException {
		startUp();
		driver.get("https://web.whatsapp.com");
		WebDriverWait wait = new WebDriverWait(driver, 8);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[title=\"Suchen oder neuen Chat beginnen\"]")));
		WebElement searchBar = driver.findElement(By.cssSelector("input[title=\"Suchen oder neuen Chat beginnen\"]"));
		searchBar.click();
		searchBar.sendKeys(chatName);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='" + chatName + "']")));
		driver.findElement(By.cssSelector("span[title='" + chatName + "']")).click();
		List<WebElement> list = driver.findElements(By.className("pluggable-input-body"));
		WebElement selectedElement = list.get(0);
		String processedUrl;
		WebElement title;

		for (int i = 0; i < times; i++) {
			processedUrl = gifProvider.provideRandom(searchTag);
			selectedElement.sendKeys(processedUrl);
			Actions actions = new Actions(driver);

			actions.sendKeys(selectedElement, Keys.chord(Keys.COMMAND, "a"));


//			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "a"));
//			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "c"));
//			selectedElement.sendKeys(Keys.chord(Keys.DELETE));
//			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "v"));

//			wait.until(ExpectedConditions.elementToBeClickable(By.className("pluggable-input-default")));
//
//			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[data-icon='\"send-light\"']")));
//
//			driver.findElement(By.cssSelector("span[title='" + chatName + "']")).click();
//
//			title = driver.findElement(By.className("pluggable-input-default"));
//			title.click();
//			title.sendKeys(Keys.chord(Keys.ENTER));
//			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "a"));

		}
	}

	private void startUp() {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver_2_33");
		gifProvider = new GifProvider();
//		ProfilesIni profile = new ProfilesIni();
//		FirefoxProfile firefoxProfile = profile.getProfile(fireFoxUserProfile);
//		FirefoxOptions firefoxOptions = new FirefoxOptions();
//		firefoxOptions.setProfile(firefoxProfile);

		String userProfile = "/Users/jfische1/Library/Application Support/Google/Chrome/Default";
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--user-data-dir=" + userProfile);



		driver = new ChromeDriver(chromeOptions);
	}
}
