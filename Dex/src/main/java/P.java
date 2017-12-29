import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class P {
    public static void main(String[] args) throws IOException {
        String endpoint = "http://xxxxx";
        URL url = new URL(endpoint);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla 5.0");

        connection.getInputStream();
    }
}
