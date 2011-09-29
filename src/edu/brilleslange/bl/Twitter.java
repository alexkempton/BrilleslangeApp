package edu.brilleslange.bl;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;


public class Twitter {
	/*
	private String authUrl;
	private Token requestToken;
	private Token accessToken;
	private OAuthService service;
	
	Twitter(){
		service = new ServiceBuilder()
	    .provider(TwitterApi.class)
	    .apiKey("gOXNPuCfr7WM1O36pv0Vg")
	    .apiSecret("xUYGXpdd8wFOfh5idT0bG4Q0LL5PvFrrwkNE2aeowM")
	    .callback("http://localhost:8888/twitter")
	    .build();
	}
	
	String getSignInUrl(){
		requestToken = service.getRequestToken();
		authUrl = service.getAuthorizationUrl(requestToken);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("user");
		q.addFilter("name", Query.FilterOperator.EQUAL, "alex");
		PreparedQuery pq = datastore.prepare(q);
		Entity user =  pq.asSingleEntity();
		if(user==null){
			user = new Entity("user");
			user.setProperty("name", "alex");
		}
		user.setProperty("token",  requestToken.getToken());
		user.setProperty("tokensecret", requestToken.getSecret());
		datastore.put(user);
		return authUrl;
	}
	
	Token getRequestToken(){
		return requestToken;
	}
	
	void setAccessToken(String verifier){
		Verifier v = new Verifier(verifier);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("user");
		q.addFilter("name", Query.FilterOperator.EQUAL, "alex");
		PreparedQuery pq = datastore.prepare(q);
		Entity user =  pq.asSingleEntity();
		String token = (String) user.getProperty("token");
		String secret = (String) user.getProperty("tokensecret");
		Token requestToken = new Token(token,secret);
		accessToken = service.getAccessToken(requestToken, v); 
		user.setProperty("accessToken", accessToken.getToken());
		user.setProperty("accessTokenSecret", accessToken.getSecret());
		datastore.put(user);
	}
	
	String getRequest(String url){
		if(accessToken==null){
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query q = new Query("user");
			q.addFilter("name", Query.FilterOperator.EQUAL, "alex");
			PreparedQuery pq = datastore.prepare(q);
			Entity user =  pq.asSingleEntity();
			String token = (String) user.getProperty("accessToken");
			String secret = (String) user.getProperty("accessTokenSecret");
			accessToken = new Token(token,secret);
		}
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		service.signRequest(accessToken, request); 
		Response response = request.send();
		return response.getBody();
	}
*/	
}
