import java.util.List;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchRandom;
import at.mukprojects.giphy4j.exception.GiphyException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class Bot {

	private static final String CHAT_NAME = "Test mit Space";
	private static final int TIMES = 3;
	private static final String GIPHY_API_KEY = "FiswI79DR5yAcxTLvAvVqbk0K26azCUl";


	public static void main(String[] args) throws GiphyException, InterruptedException {
		Giphy giphy = new Giphy(GIPHY_API_KEY);
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile firefoxProfile = profile.getProfile("Testbenutzer");
		WebDriver driver = new FirefoxDriver(firefoxProfile);
		driver.get("https://web.whatsapp.com");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("span[title='" + CHAT_NAME + "']")).click();

		List<WebElement> list = driver.findElements(By.className("pluggable-input-body"));
		WebElement selectedElement = list.get(0);
		for (int i = 0; i < TIMES; i++) {
			SearchRandom giphyData = giphy.searchRandom("cat");
			String rootUrl = giphyData.getData().getImageUrl();
			String[] urlParts = rootUrl.split("\\.");
			urlParts[0] = urlParts[0].substring(0, urlParts[0].length() - 1);
			String processedUrl = String.join(".", urlParts);
			selectedElement.sendKeys(processedUrl);
			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "a"));
			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "c"));
			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "v"));
			Thread.sleep(1000);

			WebElement title = driver.findElement(By.className("pluggable-input-default"));
			title.sendKeys(Keys.chord(Keys.ENTER));
			selectedElement.sendKeys(Keys.chord(Keys.COMMAND, "a"));
			selectedElement.sendKeys(Keys.chord(Keys.DELETE));
		}
	}
}
