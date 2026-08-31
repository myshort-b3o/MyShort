package com.myshort.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;

    private static final String WEBSITE_URL =
            "https://shortprodly.github.io/";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);

        // Aktifkan JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        // Aktifkan DOM Storage untuk login/session website
        webView.getSettings().setDomStorageEnabled(true);

        // Izinkan database/storage website
        webView.getSettings().setDatabaseEnabled(true);

        // Izinkan cookies
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(webView, true);

        // WebView menangani halaman di dalam aplikasi
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view,
                    WebResourceRequest request) {

                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view,
                    String url) {

                view.loadUrl(url);
                return true;
            }
        });

        // Dukungan JavaScript alert/dialog dan fitur web lainnya
        webView.setWebChromeClient(new WebChromeClient());

        // Buka website MyShort
        webView.loadUrl(WEBSITE_URL);
    }

    @Override
    public void onBackPressed() {

        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
