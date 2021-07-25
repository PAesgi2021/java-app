package fr.java.client.services;

import com.google.gson.Gson;
import fr.java.client.utils.types.ConfirmationDTO;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class AsyncService {

    private final String BASE_URL = "http://localhost:3000";

    //Singleton for Thread-safe
    private static AsyncService asyncService;

    public static AsyncService getInstance() {
        if (asyncService == null) {
            asyncService = new AsyncService();
            return asyncService;
        }
        return asyncService;
    }

    // Request function
    public <T> T get(String url, Type dtoClass) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        URI        uri    = URI.create(BASE_URL + url);
        var request = HttpRequest.newBuilder(uri)
                                 .header("Accept", "application/json")
                                 .build();
        return executeRequest(dtoClass, client, request, url);
    }

    public <T> T post(String url, Type dtoClass, Object... dto) throws Exception {
        HttpClient client   = HttpClient.newHttpClient();
        URI        uri      = URI.create(BASE_URL + url);
        Gson       gson     = new Gson();
        String     bodyJson = "";
        if (dto.length > 0) {
            bodyJson = gson.toJson(dto[0]);
        }

        var request = HttpRequest.newBuilder(uri)
                                 .POST(HttpRequest.BodyPublishers.ofString(bodyJson))
                                 .header("Content-Type", "application/json")
                                 .build();
        return executeRequest(dtoClass, client, request, url);
    }

    public <T> T put(String url, Type dtoClass, Object... dto) throws Exception {
        HttpClient client   = HttpClient.newHttpClient();
        URI        uri      = URI.create(BASE_URL + url);
        Gson       gson     = new Gson();
        String     bodyJson = "";
        if (dto.length > 0) {
            bodyJson = gson.toJson(dto[0]);
        }
        var request = HttpRequest.newBuilder(uri)
                                 .PUT(HttpRequest.BodyPublishers.ofString(bodyJson))
                                 .header("Content-Type", "application/json")
                                 .build();

        return executeRequest(dtoClass, client, request, url);
    }

    public <T> T delete(String url, Type dtoClass) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        URI        uri    = URI.create(BASE_URL + url);
        Gson       gson   = new Gson();
        var request = HttpRequest.newBuilder(uri)
                                 .DELETE()
                                 .header("Accept", "application/json")
                                 .build();
        return executeRequest(dtoClass, client, request, url);
    }

    // Executor method with error handling
    private <T> T executeRequest(Type dtoClass, HttpClient client, HttpRequest request, String url) throws Exception {
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                                                                 .toCompletableFuture();
        if (response != null && response.get().statusCode() != 200 && response.get().statusCode() != 201) {
            throw new Exception("unable to upload: {} response code: {}. response: {}" + url + response.get()
                                                                                                       .statusCode() + response
                    .get()
                    .body());
        }
        if (response != null) {
            if (dtoClass == String.class) {
                return (T) new ConfirmationDTO(response.get().body());
            }
            return new Gson().fromJson(response.get().body(), dtoClass);
        }
        throw new Exception("error");
    }

}
