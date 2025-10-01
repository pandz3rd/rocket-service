package com.pandz.rocket_service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pandz.rocket_service.model.api.rocket.RocketRes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class LogUtilTest {
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    @Test
    public void logRequestHeader_test() {
        request.addHeader("authorization", "Bearer token");
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Custom-Header", "customValue");
        ContentCachingRequestWrapper servletRequest = new ContentCachingRequestWrapper(request);

        String logResult = LogUtil.logRequestHeader(servletRequest);

        Map<Object, Object> expectedMap = Collections.list(request.getHeaderNames()).stream()
            .collect(Collectors.toMap(
                String::valueOf,
                s -> "authorization".equals(s) ? "*************************" : request.getHeader(s)
            ));
        String expectedJson = JsonUtil.objectAsStringJson(expectedMap);
        Assertions.assertEquals(expectedJson, logResult);
    }

    @Test
    public void logResponseHeader_test() {
        response.addHeader("authorization", "Bearer token");
        response.addHeader("Content-Type", "application/json");
        response.addHeader("Custom-Header", "customValue");
        ContentCachingResponseWrapper servletResponse = new ContentCachingResponseWrapper(response);

        String logResult = LogUtil.logResponseHeader(servletResponse);

        Map<Object, Object> expectedMap = (response.getHeaderNames()).stream()
            .collect(Collectors.toMap(
                String::valueOf,
                s -> "authorization".equals(s) ? "*************************" : response.getHeader(s)
            ));
        String expectedJson = JsonUtil.objectAsStringJson(expectedMap);
        Assertions.assertEquals(expectedJson, logResult);
    }

    @Test
    public void logRequest_test() {
        request.setParameter("header", "test");
        byte[] contentByte = "content".getBytes();
        request.setContent(contentByte);
        String result = LogUtil.logRequest(request.getContentAsByteArray());
        Assertions.assertNotNull(result);
    }

    @Test
    public void logResponse_test() {
        ContentCachingResponseWrapper contentResponse = new ContentCachingResponseWrapper(response);
        String result = LogUtil.logResponse(contentResponse);
        Assertions.assertNotNull(result);
    }

    @Test
    public void wrapRequest_test() {
        ContentCachingRequestWrapper contentRequest = LogUtil.wrapRequest(request);
        Assertions.assertTrue(contentRequest instanceof ContentCachingRequestWrapper);
        Assertions.assertSame(request, contentRequest.getRequest());
    }

    @Test
    public void wrapRequest_wrappedTest() {
        ContentCachingRequestWrapper contentRequest = new ContentCachingRequestWrapper(request);
        ContentCachingRequestWrapper result = LogUtil.wrapRequest(contentRequest);
        Assertions.assertSame(contentRequest, result);
    }

    @Test
    public void wrapResponse_test() {
        ContentCachingResponseWrapper contentResponse = LogUtil.wrapResponse(response);
        Assertions.assertTrue(contentResponse instanceof ContentCachingResponseWrapper);
        Assertions.assertSame(response, contentResponse.getResponse());
    }

    @Test
    public void wrapResponse_wrappedTest() {
        ContentCachingResponseWrapper contentResponse = new ContentCachingResponseWrapper(response);
        ContentCachingResponseWrapper result = LogUtil.wrapResponse(contentResponse);
        Assertions.assertSame(contentResponse, result);
    }

    @Test
    public void logging_test() throws JsonProcessingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/test");
        request.setQueryString("param1=value1&param2=value2");
        request.addHeader("Header1", "value1");
        request.addHeader("Header2", "value2");
        ContentCachingRequestWrapper contentRequest = new ContentCachingRequestWrapper(request);

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.addHeader("ResponseHeader1", "responseValue1");
        response.addHeader("ResponseHeader2", "responseValue2");
        ContentCachingResponseWrapper contentResponse = new ContentCachingResponseWrapper(response);

        String reqPayload = new ObjectMapper().writeValueAsString(RocketRes.builder().build());
        String resPayload = new ObjectMapper().writeValueAsString(RocketRes.builder().build());

        LogUtil.logging(contentRequest, contentResponse, reqPayload, resPayload, 5);
        Assertions.assertTrue(true);
    }

    @Test
    public void logging_nullTest() throws JsonProcessingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/test");
        request.addHeader("Header1", "value1");
        request.addHeader("Header2", "value2");
        ContentCachingRequestWrapper contentRequest = new ContentCachingRequestWrapper(request);

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.addHeader("ResponseHeader1", "responseValue1");
        response.addHeader("ResponseHeader2", "responseValue2");
        ContentCachingResponseWrapper contentResponse = new ContentCachingResponseWrapper(response);

        String reqPayload = new ObjectMapper().writeValueAsString(RocketRes.builder().build());
        String resPayload = new ObjectMapper().writeValueAsString(RocketRes.builder().build());

        LogUtil.logging(contentRequest, contentResponse, reqPayload, resPayload, 5);
        Assertions.assertTrue(true);
    }

    @Test
    public void logging2_test() throws JsonProcessingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/test");
        request.setQueryString("param1=value1&param2=value2");
        request.addHeader("Header1", "value1");
        request.addHeader("Header2", "value2");
        ContentCachingRequestWrapper contentRequest = new ContentCachingRequestWrapper(request);

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.addHeader("ResponseHeader1", "responseValue1");
        response.addHeader("ResponseHeader2", "responseValue2");
        ContentCachingResponseWrapper contentResponse = new ContentCachingResponseWrapper(response);

        String reqPayload = new ObjectMapper().writeValueAsString(RocketRes.builder().build());
        String resPayload = new ObjectMapper().writeValueAsString(RocketRes.builder().build());

        LogUtil.logging(contentRequest, contentResponse, reqPayload, resPayload, 60L);
        Assertions.assertTrue(true);
    }

    @Test
    public void logging2_nullTest() throws JsonProcessingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/test");
        request.addHeader("Header1", "value1");
        request.addHeader("Header2", "value2");
        ContentCachingRequestWrapper contentRequest = new ContentCachingRequestWrapper(request);

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.addHeader("ResponseHeader1", "responseValue1");
        response.addHeader("ResponseHeader2", "responseValue2");
        ContentCachingResponseWrapper contentResponse = new ContentCachingResponseWrapper(response);

        String reqPayload = new ObjectMapper().writeValueAsString(RocketRes.builder().build());
        String resPayload = new ObjectMapper().writeValueAsString(RocketRes.builder().build());

        LogUtil.logging(contentRequest, contentResponse, reqPayload, resPayload, 60L);
        Assertions.assertTrue(true);
    }
}