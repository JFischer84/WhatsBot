package de.laserraptor;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchRandom;
import at.mukprojects.giphy4j.exception.GiphyException;

public class GifProvider {
	private static final String GIPHY_API_KEY = "FiswI79DR5yAcxTLvAvVqbk0K26azCUl";
	private Giphy giphy;
	private SearchRandom giphyData;
	private String[] urlParts;

	public String provideRandom(String search) throws GiphyException {
		giphy = new Giphy(GIPHY_API_KEY);
		giphyData = giphy.searchRandom(search);
		urlParts =  giphyData.getData().getImageUrl().split("\\.");
		urlParts[0] = urlParts[0].substring(0, urlParts[0].length() - 1);
		return String.join(".", urlParts);
	}
}
