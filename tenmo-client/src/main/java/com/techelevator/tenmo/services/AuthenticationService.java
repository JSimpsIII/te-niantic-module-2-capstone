package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class AuthenticationService extends AuthenticatedApiService<UserCredentials>
{

<<<<<<< HEAD
    public AuthenticationService() {
    }
=======
    //private final String baseUrl;
//    public static String baseUrl;
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public AuthenticationService(String url)
//    {
//        baseUrl = url;
//    }
>>>>>>> kayla

    public AuthenticatedUser login(UserCredentials credentials)
    {
        HttpEntity<UserCredentials> entity = createCredentialsEntity(credentials);
        AuthenticatedUser user = null;
        try
        {
            ResponseEntity<AuthenticatedUser> response =
                    restTemplate.exchange(baseUrl + "login", HttpMethod.POST, entity, AuthenticatedUser.class);
            user = response.getBody();
        }
        catch (RestClientResponseException | ResourceAccessException e)
        {
            BasicLogger.log(e.getMessage());
        }
        return user;
    }

    public boolean register(UserCredentials credentials)
    {
        HttpEntity<UserCredentials> entity = createCredentialsEntity(credentials);
        boolean success = false;
        try
        {
            restTemplate.exchange(baseUrl + "register", HttpMethod.POST, entity, Void.class);
            success = true;
        }
        catch (RestClientResponseException | ResourceAccessException e)
        {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }

    private HttpEntity<UserCredentials> createCredentialsEntity(UserCredentials credentials)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(credentials, headers);
    }

}
