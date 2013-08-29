package com.example.suapinho;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.app.AlertDialog;

public class MainActivity extends Activity {

	private String url = "http://www.google.com/recaptcha/api/image?c=03AHJ_VutrE3KBvJFtd1MJXrjIajiZusZgSVZ5_3pNtfBdZh9QOmrAIGEYEB2_d-MBoLlmYq-0o2PTnSydXb_dosQXf82dnaB8uPyN6y_rR4MBQ4ySeFhX55X8pB8ZLphX3nSIFucEQjTK4ooiejs-aCUFfpZV8uUqRHyLd--hir6-F0RkKR0zu0o";
	private ImageView imaView;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void consultarLista(View view) {
		Intent i = new Intent();
		i.setClass(this, NovaConsulta.class);
		startActivity(i);
	}

	public void consulta(View view) {
		Intent i = new Intent();
		i.setClass(this, Consulta.class);
		startActivity(i);
	}
	
	public void consultaCapcha(View view) {
		Intent i = new Intent();
		i.setClass(this, Captcha.class);
		startActivity(i);
	}

	public void captcha(View view) {
		Intent i = new Intent();
		i.setClass(this, Captcha.class);
		startActivity(i);
	}
	
	public void voltarHome(View view) {
		Intent i = new Intent();
		i.setClass(this, this.getClass());
		startActivity(i);
	}

	public void buscarImagen(View view) {
		Drawable d = LoadImageFromWebOperations(this.url);
		//this.imaView = (ImageView) findViewById(R.id.imageView1);
		if (d == null) {
			System.out.println("esta nulo!!!");
		} else {
			imaView.setImageDrawable(d);

		}

	}

	private Drawable LoadImageFromWebOperations(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "captcha");
			return d;
		} catch (Exception e) {
			System.out.println("Exc=" + e);
			return null;
		}
	}

	public boolean loadImageFromURL(String fileUrl, ImageView iv) {
		try {

			URL myFileUrl = new URL(fileUrl);
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();

			InputStream is = conn.getInputStream();
			iv.setImageBitmap(BitmapFactory.decodeStream(is));

			return true;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
