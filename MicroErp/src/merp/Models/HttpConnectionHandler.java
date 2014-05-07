package merp.Models;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpConnectionHandler {
    private static String url = "http://localhost:8080/server/";


    public boolean sendPostEditRequest(String jsonObject) throws IOException {

        // url = server/insertNewContacts
        // keyword: json
        // maybe search auch per POST  ??
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url + "insertNewContacts");

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("json", "jsonObject"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return true;
    }

    public String sendPostSearchRequest(String firstName, String lastName, String name, String uid) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url + "searchContacts");
        List <NameValuePair> urlParameters = new ArrayList <NameValuePair>();
        if(!firstName.isEmpty()) urlParameters.add(new BasicNameValuePair("firstName", firstName));
        if(!lastName.isEmpty()) urlParameters.add(new BasicNameValuePair("lastName", lastName));
        if(!name.isEmpty()) urlParameters.add(new BasicNameValuePair("q", name));
        if(!uid.isEmpty()) urlParameters.add(new BasicNameValuePair("uid", uid));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(urlParameters, "UTF-8");
        httpPost.setEntity(urlEncodedFormEntity);
        HttpResponse response = client.execute(httpPost);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    public String sendGetRequest(String searchString){
        String responseBody ="";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url + "searchContacts?" + searchString);

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            responseBody = httpClient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseBody;

    }


}
