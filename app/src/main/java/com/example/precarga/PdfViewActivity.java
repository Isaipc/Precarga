package com.example.precarga;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PdfViewActivity extends AppCompatActivity {

    private String url = "";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        url = getIntent().getStringExtra(HomeFragment.URL);
        setUpWebView();
        getSupportActionBar().hide();
    }

    protected void setUpWebView() {
        WebView webView = findViewById(R.id.pdfView);
        webView.getSettings().setJavaScriptEnabled(true);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando documento");
        progressDialog.setCancelable(false);

        webView.requestFocus();
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100)
                    progressDialog.dismiss();

                if (newProgress < 100)
                    progressDialog.show();
            }
        });
    }
}