package com.example.ejuklakapps;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebFragment extends Fragment {

	ProgressDialog mProgress;
	WebView webview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.web_fragment, container,
				false);
		Bundle bundle = getArguments();
		String url = bundle.getString("url");
		webview = (WebView) rootView.findViewById(R.id.webview1);

		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setBuiltInZoomControls(true);

		mProgress = ProgressDialog.show(getActivity(), "Loading",
				"Please wait for a moment...");

		webview.loadUrl(url);

		webview.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				webview.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				if (mProgress.isShowing()) {
					mProgress.dismiss();
				}
			}
			/*
			 * public void onReceivedError(WebView view, int errorCode, String
			 * description, String failingUrl) { int found =
			 * failingUrl.indexOf('#');
			 * 
			 * if (found > 0) { anchor = failingUrl.substring(found + 1,
			 * failingUrl.length()); view.loadUrl(failingUrl.substring(0,
			 * found)); } }
			 * 
			 * public void onPageFinished(WebView view, String url) { if
			 * (this.anchor != null) {
			 * view.loadUrl("javascript:window.location.hash='" + this.anchor +
			 * "'"); this.anchor = null; } }
			 */
		});

		return rootView;
	}

}