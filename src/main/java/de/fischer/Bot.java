package de.fischer;

import java.util.List;

import at.mukprojects.giphy4j.exception.GiphyException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Bot {

	private String fireFoxUserProfile;
	private WebDriver driver;
	private WebDriverWait wait;
	GifProvider gifProvider;
	public Bot(String firefoxUserProfile) {
		this.fireFoxUserProfile = firefoxUserProfile;
	}

	public void spam(String chatName, String searchTag, int times) throws GiphyException {
		startUp();
		driver.get("https://web.whatsapp.com");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("input-search")));
		WebElement searchBar = driver.findElement(By.className("input-search"));
		searchBar.click();
		searchBar.sendKeys(chatName);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='" + chatName + "']")));
		driver.findElement(By.cssSelector("span[title='" + chatName + "']")).click();

		List<WebElement> list = driver.findElements(By.className("pluggable-input-body"));
		WebElement selectedElement = list.get(0);
		for (int i = 0; i < times; i++) {
			String processedUrl = gifProvider.provideRandom(searchTag);
			selectedElement.sendKeys(processedUrl);
			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "a"));
			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "c"));
			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "v"));

			wait.until(ExpectedConditions.elementToBeClickable(By.className("pluggable-input-default")));

			WebElement title = driver.findElement(By.className("pluggable-input-default"));
			title.click();
			title.sendKeys(Keys.chord(Keys.ENTER));
			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "a"));
			selectedElement.sendKeys(Keys.chord(Keys.DELETE));
		}

	}

	private void startUp() {
		gifProvider = new GifProvider();
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile firefoxProfile = profile.getProfile(fireFoxUserProfile);
		driver = new FirefoxDriver(firefoxProfile);
	}
}
