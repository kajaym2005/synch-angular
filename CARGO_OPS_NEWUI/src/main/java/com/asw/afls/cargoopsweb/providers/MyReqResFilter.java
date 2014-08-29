package com.asw.afls.cargoopsweb.providers;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.ws.rs.GET;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.asw.afls.cargoopsweb.annotations.CacheAnnotations.CacheMaxAge;
import com.asw.afls.cargoopsweb.annotations.CacheAnnotations.NoCache;

public class MyReqResFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		System.out.println("filter(ContainerRequestContext,ContainerResponseContext)");
		/*
		MediaType type = response.getMediaType();
		if (type != null) {
		    String contentType = type.toString();
		    if (!contentType.contains("charset")) {
		        contentType = contentType + ";charset=utf-8";
		        response.getHeaders().putSingle("Content-Type", contentType);
		    }
		}
		//https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS
		//response.getHeaders().addFirst("Access-Control-Allow-Origin", "http://localhost:8081");
		//response.getHeaders().addAll("Access-Control-Allow-Methods", "GET", "POST", "OPTIONS");
		*/

		response.getHeaders().addFirst("Access-Control-Allow-Origin", "*");
		String accessRequest = request.getHeaderString("Access-Control-Request-Headers");
		if (accessRequest != null && !accessRequest.equals("")) {
			response.getHeaders().addFirst("Access-Control-Allow-Headers", accessRequest);
		}
		accessRequest = request.getHeaderString("Access-Control-Request-Method");
		if (accessRequest != null && !accessRequest.equals("")) {
			response.getHeaders().addFirst("Access-Control-Allow-Methods", accessRequest);
		}

		final Method resourceMethod = resourceInfo.getResourceMethod();

		String cacheControl = null;
		if (resourceMethod != null && resourceMethod.getAnnotation(GET.class) != null) {
			NoCache noCache = resourceMethod.getAnnotation(NoCache.class);
			if (noCache != null) {
				cacheControl = "no-cache";
			}
			CacheMaxAge maxAge = resourceMethod.getAnnotation(CacheMaxAge.class);
			if (maxAge != null) {
				cacheControl = "max-age:" + maxAge.unit().toSeconds(maxAge.time());
			}
		}
		String cacheControlExists = request.getHeaderString(HttpHeaders.CACHE_CONTROL);
		if (cacheControlExists == null && cacheControl != null) {
			response.getHeaders().putSingle(HttpHeaders.CACHE_CONTROL, cacheControl);
		}

		for (String key : response.getHeaders().keySet()) {
			System.out.println(key + " : " + response.getHeaders().get(key).toString());
		}
	}

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		//System.out.println("filter(ContainerRequestContext)");

	}

}
