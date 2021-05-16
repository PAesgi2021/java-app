package fr.java.client.services;

public class AsyncService {
    //TODO implem de okHTTP et GSON
    private static AsyncService asyncService;

    public static AsyncService getInstance() {
        if (asyncService == null) {
            asyncService = new AsyncService();
            return asyncService;
        }
        return asyncService;
    }
}
