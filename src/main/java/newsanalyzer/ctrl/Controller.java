// Link repository: https://github.com/NadineFeurhuber/NewsAnalyzer.git

package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;
import newsapi.enums.Language;
import newsapi.NewsApiBuilder;
import newsapi.beans.NewsReponse;
import newsapi.beans.Source;
// import newsapi.NewsException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "247Nm937Fk2017m1502";

	private String keyword;
	private Category category;
	private Endpoint endpoint;
	private Language language;
	private Country country;
	private Exception NewsException;

	private int getSizeReports(List<Article> articles) {
		return articles.size();
	}

	public String getAuthor(List<Article> authorList) {
		Article authors = authorList.stream().filter(a -> a.getAuthor() != null)
				.sorted(Comparator.comparingInt(a -> a.getAuthor().length())).findFirst().orElse(new Article());
		return authors.getAuthor();
	}

	public String getProvider(List<Article> providerList) {
		Map<String, Long> frequencyMap = providerList.stream().collect(Collectors.groupingBy(provider -> provider.getSource().getName(), Collectors.counting()));
		String number = frequencyMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey().toString();
		String provider = frequencyMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue().toString();
		return "Most Articles: " + number + "(" + provider + ")";
	}

	public List<Article> getTitlesSort(List<Article> sortList) {
		List sort = sortList.stream().sorted(Comparator.comparingInt(sortarticle -> sortarticle.getTitle().length())).collect(Collectors.toList());
		return sort;
	}



	public void process(NewsApi newsApi) {
		System.out.println("Start process");

		try {
			NewsReponse newsReponse = newsApi.getNews();;
			List<Article> articles = null;
			articles = newsReponse.getArticles();
			int numberArticles = getSizeReports(articles);
			System.out.println("Number of Articles: " + numberArticles);
			System.out.println(getAuthor(articles));
			System.out.println(getProvider(articles));
			getTitlesSort(articles).forEach(article -> System.out.println(article.getTitle()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Scanner san = new Scanner(System.in);
		System.out.println("Download here.");


		//TODO implement Error handling

		//TODO load the news based on the parameters

		//TODO implement methods for analysis

		System.out.println("End process");
	}
	

	public Object getData() {
		
		return null;
	}
}
