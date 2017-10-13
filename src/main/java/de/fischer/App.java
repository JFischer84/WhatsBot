package de.fischer;

import at.mukprojects.giphy4j.exception.GiphyException;

public class App {

	private static final String CHAT_NAME = "Test mit Space";
	private static final int TIMES = 3;
	private static final String SEARCH_TAG = "kitten";
	private static final String FIREFOX_USER_PROFILE = "Testbenutzer";


	public static void main(String[] args) {
		Bot bot = new Bot(FIREFOX_USER_PROFILE);
		try {
			bot.spam(CHAT_NAME, SEARCH_TAG, TIMES);
		} catch (GiphyException e) {
			e.printStackTrace();
		}

	}
}
