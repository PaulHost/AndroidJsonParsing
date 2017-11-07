package ph.hostev.paul.androidjsonparsing;

import android.app.Application;
import org.jetbrains.annotations.NotNull;
import ph.hostev.paul.androidjsonparsing.httpClient.HttpClient;

public class App extends Application {
    private static HttpClient client = null;

    @NotNull
    public static HttpClient http() {
        if (client == null) client = new HttpClient();
        return client;
    }
}
