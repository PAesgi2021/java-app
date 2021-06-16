package fr.java.client.components.login;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import com.google.gson.Gson;

public class LoginAsync {
    HttpClient client = HttpClient.newHttpClient();

    HttpResponse<String> doGETAsync(URI uri) throws Exception {
        var request = HttpRequest.newBuilder(uri)
                                 .header("Accept", "application/json")
                                 .build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                                                                 .toCompletableFuture();


       return response.get();
    }

    LoginDTO getTest() throws Exception {
        return new Gson().fromJson(doGETAsync(URI.create("http://localhost:3000/test")).body(), LoginDTO.class);
    }



}
