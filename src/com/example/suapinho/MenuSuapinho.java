package com.example.suapinho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuSuapinho extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		
	}
	
	public void consultar(View view) {
		Intent i = new Intent();
		i.setClass(this, NovaConsulta.class);
		startActivity(i);
	}
	
	public void remover(View view) {
		Intent i = new Intent();
		i.setClass(this, RemoverProcesso.class);
		startActivity(i);
	}
	
	
	
}
