/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void GSONTest() {
        // Relative Path : "app/src/main/resources/recentquotes.json" --- "DOSE NOT WORK HER"
        Path path = Paths.get("app/src/main/resources/recentquotes.json");
        List<Quotes> quotes = App.jsonParser(path);
        assertEquals(quotes.size(), App.jsonParser(path).size());

    }

    @Test
    public void getQuotesFromAPI() {
        String url = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";
        APIQuotes quotes = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                //Relative path just work when run the program, but it not works with the Test!!!
               // quotes = App.APIQuotesGetter(connection, new File("app/src/main/resources/APIQuotes.json"));
                quotes = App.APIQuotesGetter(connection, new File("C:/Users/STUDENT/java-401/quotes/app/src/main/resources/APIQuotes.json"));
                connection.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(quotes, "The method should return a quote object");
    }
}
