package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import download.SequentialDownloader;
import newsanalyzer.ctrl.Controller;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;
import newsapi.enums.Language;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;

public class UserInterface 
{

	private Controller ctrl = new Controller();
	public static final String APIKEY = "247Nm937Fk2017m1502";

	public void getDataFromCtrl1(){
		System.out.println("National");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setLanguage(Language.de)
				.setSourceCategory(Category.entertainment)
				.createNewsApi();

		ctrl.process(newsApi);
	}

	private void setQ(String s) {
	}

	private void setApiKey(String apikey) {
	}

	public void getDataFromCtrl2(){
		System.out.println("Foreign Countries");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.gb)
				.setLanguage(Language.en)
				.setSourceCategory(Category.business)
				.createNewsApi();

		ctrl.process(newsApi);
	}

	public void getDataFromCtrl3(){
		System.out.println("Science");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.us)
				.setLanguage(Language.en)
				.setSourceCategory(Category.science)
				.createNewsApi();

		ctrl.process(newsApi);

	}
	
	public void getDataForCustomInput() {
		System.out.println("Keyword:");
		Scanner scan = new Scanner(System.in);
		String key = scan.nextLine();
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setQ(key)
				.createNewsApi();

		ctrl.process(newsApi);
		
	}

	public void getDownloadLastSearch() {
		SequentialDownloader sequentialDownloader = new SequentialDownloader();
		sequentialDownloader.process(Controller.urlList);
		System.out.println("Download last search");
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Choice ABC", this::getDataFromCtrl1);
		menu.insert("b", "Choice DEF", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Imput:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
