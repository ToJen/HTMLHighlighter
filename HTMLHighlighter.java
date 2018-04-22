import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

public class HTMLHighlighter {

	public static final String ANSI_RESET = "\u001B[0m";
	public static String[] colors = {"\033[0;31m","\033[0;32m","\033[0;33m","\033[0;34m","\033[0;35m",
									 "\033[0;36m","\033[0;37m","\033[1;30m","\033[1;31m","\033[1;32m",
									 "\033[1;33m","\033[1;34m","\033[1;35m","\033[1;36m","\033[1;37m",
									 "\033[4;30m","\033[4;31m","\033[4;32m","\033[4;33m","\033[4;34m",
									 "\033[4;35m","\033[4;36m","\033[4;37m","\033[40m","\033[41m",
									 "\033[42m","\033[43m","\033[44m","\033[45m","\033[46m","\033[47m",
									 "\033[0;90m","\033[0;91m","\033[0;92m","\033[0;93m","\033[0;94m",
									 "\033[0;95m","\033[0;96m","\033[0;97m","\033[1;90m","\033[1;91m",
									 "\033[1;92m","\033[1;93m","\033[1;94m","\033[1;95m","\033[1;96m",
									 "\033[1;97m","\033[0;100m","\033[0;101m","\033[0;102m","\033[0;103m",
									 "\033[0;104m","\033[0;105m","\033[0;106m","\033[0;107m"};

	public static ArrayList tags = new ArrayList();

   	public static void main(String[] args) throws IOException, URISyntaxException {

   		if(args.length > 0) {
	    	URL path = ClassLoader.getSystemResource(args[0]);
	      	File input = new File(path.toURI());

	      	Document document = Jsoup.parse(input, "UTF-8");
	      	Elements headElements = document.head().select("*");
	      	Elements bodyElements = document.body().select("*");

	      	Map map = new HashMap();

	      	int count = 0;
	      	for (Element element : headElements) {
	      		if(!map.containsValue(element)) {
	      			map.put(colors[count], element);
	      		}
	      		count++;
	      	}
	      	for (Element element : bodyElements) {
	      		if(!map.containsValue(element)) {
	      			map.put(colors[count], element);
	      		}
	      		count++;
	      	}
	      	System.out.println(map + ANSI_RESET);

   		} else {
   			System.out.println("Please rerun with the file path of a single HTML file.");
   		}
   	}
}