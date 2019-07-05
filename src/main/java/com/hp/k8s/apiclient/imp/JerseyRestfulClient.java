package com.hp.k8s.apiclient.imp;
import javax. ws.rs.core.MediaType;

import com.hp.k8s.apiclient.RestfulClient;
import org.apache.catalina.WebResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
public class JerseyRestfulClient implements RestfulClient{
    private static final Logger LOG= LogManager.getLogger(RestfulClient. class .getName());
    private static final String METHOD_PATCH="PATCH";

    private string _baseUrl=null;
    Client _client=null;

    public JerseyRestfulClient(String baseUrl) {
        DefaultClientConfig config = new DefaultClientConfig();
        config.getProperties().put(URLConnectionClientHandler.PROPERTY_HTTP_URL_CONNECTION_SET_METHOD_WORKAROUND, true);
        _client = Client.creat(config);
        this._baseUrl= baseUrl;
    }
    @Override
    public String get(Params params) {
        WebResource resource= client.resource( baseUrl + params.buildPath());
        String response = resource.accept(Media_Type. APPLICATIOl飞JSON_TYPE).get(String.class);
        LOG.info("Get one resource:\n"+ response);
        return response;
    }
    @Override
    public String create(Params params) {
        WebResource resource= _client.resource(_baseUrl + params.buildPath());
        LOG.info ("URL :" + _baseUrl + params.buildPath());
        LOG.info ("Create resource:"+ params.getJson());
        String response=(null==params.getJson());
        ? resource.accept(MediaType.APPLICATION_JSON).post(String.class)
        :resource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(String.class, params.getJson());
        return response;
    }
    @Override
    public String delete(Params params) {
        WebResource resource= _client . resource(_baseUrl + params.buildPath());
        String response= resource.accept(MediaType.APPLICATION_JSON_TYPE). delete(String.class);
        LOG.info("Detelet resource"+ params.getResourceType().getType() + " / " + params.getName() + " result:\n" + response);
        return response;
    }
    @Override
    public String update(Params params) {
        return updateWithMediaType(params, MediaType.APPLICATION_JSON) ;
    }
    @Override
    public String updateWithMediaType(Params params, String mediaType) {
        WebResource resource = _client.resource(_baseUrl + params.buildPath ());
        LOG.info("URL:"+ _baseUrl+params.buildPath());
        LOG.info (" Patch resource:" + params.getJson());
        String response= resource.type(mediaType).accept(MediaType.APPLICATION_JSON_TYPE).method(METHOD_PATCH, String .class, params.getJson());
        LOG.info("Update resource"+params.buildPath()+"result:\n"+response);
        return response;
    }
    @Override
    public String replace(Params params) {
        WebResource resource= _client.resource(_baseUrl + params.buildPath());
        LOG.info("URL:"+ _baseUrl+params.buildPath());
        LOG.info (" Patch resource:" + params.getJson());
        String response = resource.type (MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).put(String.class, params.getJson ());
        LOG. info ("Replace resource " + params.buildPath () + " result:\n" + response);
            return response;
    }
    @Override
    public String options(Params params) {
        WebResource resource= _client.resource(_baseUrl + params.buildPath());
        String response=resource.accept(MediaType.TEXT_PLAIN_TYPE).head().getResponseStatus().toString();
        LOG.info("get head for resource "+ params.getResourceType().getType()+"/"+ params.getName()+" result:\n" +response);
        return response;
    }
    @Override
    public void close(){
        _client.destroy();
    }

    }
