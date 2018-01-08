package com.Pau.ImapNotes2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import jp.wasabeef.richeditor.RichEditor;


public class NewNoteActivity extends Activity{
	
    private static final int SAVE_BUTTON = 5;
	private static final String TAG = "IN_NewNoteActivity";
	private String sticky;
	private String color = "NONE";
	private boolean isBold = true;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
		);

		final RichEditor richEditor = (RichEditor) findViewById(R.id.editNote);
		richEditor.setHtml("New Note");

		this.ResetColors();
		this.sticky = (String)getIntent().getExtras().get("usesSticky");

		findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				richEditor.setBold();
	}
		});
		findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				richEditor.setItalic();
			}
		});
		findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				richEditor.setSubscript();
			}
		});
		findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				richEditor.setSuperscript();
			}
		});
		findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				richEditor.setStrikeThrough();
			}
		});
		findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				richEditor.setUnderline();
			}
		});
		findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				richEditor.setBullets();
			}
		});
		findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				richEditor.insertTodo();
			}
		});
		findViewById(R.id.action_insert_checkbox).setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				return false;
			}
		});
	}

	
	private void ResetColors(){
		((RichEditor)findViewById(R.id.editNote)).setEditorBackgroundColor(Color.TRANSPARENT);
	    ((RichEditor)findViewById(R.id.editNote)).setEditorFontColor(Color.BLACK);
		((RichEditor)findViewById(R.id.editNote)).setEditorFontSize(18);

	}

	
    public boolean onCreateOptionsMenu(Menu menu){
    	getMenuInflater().inflate(R.menu.newnote, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
		RichEditor viewById = (RichEditor) findViewById(R.id.editNote);
        switch (item.getItemId()){
		case R.id.save:
                	Intent intent=new Intent();
                	intent.putExtra("SAVE_ITEM", viewById.getHtml());
                    if (this.sticky.equals("true")) {
                		this.color="YELLOW";
                    }
                    intent.putExtra("SAVE_ITEM_COLOR",this.color);
                	setResult(SAVE_BUTTON, intent);
                	finish();//finishing activity
                	return true;
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		default:
			return super.onOptionsItemSelected(item);
        }
    }
}
