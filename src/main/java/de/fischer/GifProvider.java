package de.fischer;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchRandom;
import at.mukprojects.giphy4j.exception.GiphyException;

public class GifProvider {
	private static final String GIPHY_API_KEY = "FiswI79DR5yAcxTLvAvVqbk0K26azCUl";

	public String provideRandom(String search) throws GiphyException {
		Giphy giphy = new Giphy(GIPHY_API_KEY);
		SearchRandom giphyData = giphy.searchRandom(search);
		String rootUrl = giphyData.getData().getImageUrl();
		String[] urlParts = rootUrl.split("\\.");
		urlParts[0] = urlParts[0].substring(0, urlParts[0].length() - 1);
		return String.join(".", urlParts);
	}

}
