package fr.java.client.components.login;

import fr.java.client.services.AsyncService;

public class LoginAsync {
    private final String LOGIN_URL = "/login";
    private final String TEST_URL  = "/test";

    private final AsyncService asyncService = AsyncService.getInstance();


    LoginDTO getTest() throws Exception {
        return asyncService.get(TEST_URL, LoginDTO.class);
    }


}
