package com.kilogod.code.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author Anding
 * @describe
 */
public class HttpUtils {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();

    private static HttpUtils instance = null;

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        if (instance == null) {
            instance = new HttpUtils();
        }
        return instance;
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     */
    public Map sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:json)
     */
    public Map sendHttpPost(String httpUrl, String params) {
        log.info("发送 post请求 参数："+params);
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            //设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * post 请求
     * 数据格式 form-data
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public String sendHttpPostFromData(String url, Map<String, String> map) throws Exception {
        String result = "";
        CloseableHttpClient client;
        CloseableHttpResponse response;

        client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        URIBuilder uriBuilder = new URIBuilder(url);
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Connection", "Keep-Alive");
        httpPost.setHeader("Charset", "utf-8");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        List<NameValuePair> params = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            params.add(pair);
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        try {
            response = client.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }
        } catch (ClientProtocolException e) {
            throw new RuntimeException("创建连接失败" + e);
        } catch (IOException e) {
            throw new RuntimeException("创建连接失败" + e);
        }

        return result;
    }

    /**
     * post请求上传文件
     * @param map
     * @param url
     * @return
     */
    public Map sendHttpPostFile(String url,Map<String, ContentBody> map) {
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        CloseableHttpResponse response;
        HttpEntity entity;
        Map responseContent;
        
        try {
            String boundary = UUID.randomUUID().toString();

            MultipartEntityBuilder multipart = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setBoundary("--------------------" + boundary)
                    .setCharset(Charset.defaultCharset());

            Iterator<Map.Entry<String, ContentBody>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, ContentBody> entry = it.next();
                multipart.addPart(entry.getKey(),entry.getValue());
            }
            HttpEntity reqEntity = multipart.build();
            HttpPost request = new HttpPost(url);
            request.setEntity(reqEntity);
            request.addHeader("Content-Type", "multipart/form-data; boundary=--------------------"+boundary);


            response = httpClient.execute(request);
            entity = response.getEntity();
            log.info("返回结果："+ JSONObject.toJSONString(entity));
            responseContent = new Gson().fromJson(EntityUtils.toString(entity, "UTF-8"), Map.class);
        } catch (ClientProtocolException e) {
            throw new RuntimeException("创建连接失败" + e);
        } catch (IOException e) {
            throw new RuntimeException("创建连接失败" + e);
        }
        return responseContent;
    }

    /**
     * 发送post请求得到文件
     * 获取file
     * @param url
     * @param params
     * @return
     */
    public byte[] sendHttpPostGetFile(String url, String params) {
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpRequestGetFile(httpPost);
    }

    /**
     * 发送Post请求
     *
     * @param httpPost
     * @return
     */
    private Map sendHttpPost(HttpPost httpPost) {
        return sendHttpRequest(httpPost);
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl
     */
    public Map sendHttpGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpGet(httpGet);
    }

    /**
     * 发送Get请求
     *
     * @param httpGet
     * @return
     */
    private Map sendHttpGet(HttpGet httpGet) {
        return sendHttpRequest(httpGet);
    }

    private Map sendHttpRequest(HttpRequestBase httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity;
        Map responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            log.info("返回结果："+ JSONObject.toJSONString(entity));
            responseContent = new Gson().fromJson(EntityUtils.toString(entity, "UTF-8"), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doFinally(httpClient, response);
        }
        return responseContent;
    }

    /**
     * 发送post请求得到文件
     * 得到file
     * @param httpPost
     * @return
     */
    private byte[] sendHttpRequestGetFile(HttpRequestBase httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity;
        InputStream inputStream;
        byte[] bytes=null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            log.info("返回结果："+ JSONObject.toJSONString(entity));
            long contentLength = entity.getContentLength();
            if (contentLength<0){
                return null;
            }
            inputStream = entity.getContent();
            bytes = readInputStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doFinally(httpClient, response);
        }
        return bytes;
    }

    private byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    private void doFinally(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            // 关闭连接,释放资源
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map doXMLParse(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流
        in.close();

        return m;
    }

    private String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    public Map getRequestXml2Map(HttpServletRequest request) throws IOException, JDOMException {
        InputStream inputStream = null;
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        String s;
        try {
            inputStream = request.getInputStream();
            in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        log.info("储值回调入参!" + sb.toString());
        return HttpUtils.getInstance().doXMLParse(sb.toString());
    }
    
    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:json)
     */
    public String post(String httpUrl, String params) {
        log.info("发送 post请求 参数："+params);
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            //设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post(httpPost);
    }
    
    /**
     * 发送Post请求
     *
     * @param httpPost
     * @return
     */
    private String post(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = JSONObject.toJSONString(entity);
            log.info("返回结果："+ responseContent );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doFinally(httpClient, response);
        }
        return responseContent;
    }
    
    /**
     * post请求传输json参数
     * 
     * @param url url地址
     * @param jsonParam 参数
     * @return
     */
    public static String httpPost(String url, JSONObject jsonParam)
    {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        String str = "";
        // 设置请求和传输超时时间
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build());
        try
        {
            if (null != jsonParam)
            {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                try
                {
                    // 读取服务器返回过来的json字符串数据
                    str = EntityUtils.toString(result.getEntity(), "utf-8");
                }
                catch (Exception e)
                {
                }
            }
        }
        catch (IOException e)
        {
        }
        finally
        {
            httpPost.releaseConnection();
        }
        return str;
    }
    
}
