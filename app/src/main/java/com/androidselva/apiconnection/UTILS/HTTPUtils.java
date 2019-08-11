package com.androidselva.apiconnection.UTILS;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class HTTPUtils {
    //static Context context;

    public static String gURLBase = "http://nndfndnmjdfhbfcfh/";

    public static <T, E> Object getDataFromServer(Object inObj, Class<T> inType, Class<E> outType, String servlet){
        HttpURLConnection urlConnection = null;
        PrintWriter out = null;
        InputStream in = null;
        Reader r = null;
        try {
            LogTrace.i("HTTPUtils", "Inside HTTP Utils");
            URL urlToRequest = new URL(gURLBase + servlet);
            //TrustAllCertificates();
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(30*1000);
            //urlConnection.setHostnameVerifier(hostNameVerifier);


            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Gson gson = gsonBuilder.create();
            String postParameters = gson.toJson(inObj,inType);
            // handle POST parameters
            if (postParameters != null) {
                LogTrace.i("HTTPUtils", "Before post request");
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setFixedLengthStreamingMode(
                        postParameters.getBytes().length);
                urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                //send the POST out
                out = new PrintWriter(urlConnection.getOutputStream());
                out.print(postParameters);
                out.close();

                // handle issues
                int statusCode = urlConnection.getResponseCode();
                LogTrace.i("HTTPUtils","HTTPS Response Status Code : " +  statusCode + urlToRequest);
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    in = new BufferedInputStream(urlConnection.getInputStream());
                    r = new InputStreamReader(in,"UTF-8");
                    Object responseObj = gson.fromJson(r, outType);
                    Log.d("findresponse", "getDataFromServer:checkit "+responseObj);
                    return responseObj;
                }
            }
        } catch (Exception e) {
            LogTrace.e("Hes@73bbad3TTPUtils", e.getMessage());
            e.printStackTrace();
            return null;
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            out = null;
            in = null;
            r = null;
            urlConnection = null;
        }
        return null;
    }

    final static HostnameVerifier hostNameVerifier = new HostnameVerifier(){
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }};

    private static SSLContext context = null;
    private static void TrustAllCertificates() {
        try {
            if(context == null){
                context = SSLContext.getInstance("TLS");
                context.init(null, new X509TrustManager[]{
                        new X509TrustManager(){
                            @Override
                            public void checkClientTrusted(X509Certificate[] chain,
                                                           String authType) throws CertificateException {

                            }
                            @Override
                            public void checkServerTrusted(X509Certificate[] chain,
                                                           String authType) throws CertificateException {

                            }
                            @Override
                            public X509Certificate[] getAcceptedIssuers() {
                                return new X509Certificate[0];
                            }}}, new SecureRandom());
            }
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

    private static void TrustAllCertificatesOld() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{
                    new X509TrustManager(){
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain,
                                                       String authType) throws CertificateException {

                        }
                        @Override
                        public void checkServerTrusted(X509Certificate[] chain,
                                                       String authType) throws CertificateException {

                        }
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
            LogTrace.e("HTTPUtils", e.getMessage());
        }
    }
    //	public static void SetContext(Context mcontext) {
    //		context = mcontext;
    //	}
}
