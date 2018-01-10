package dev.lb.cellpacker.structure.resource;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonResource extends Resource{

	private JsonElement content;
	
	public JsonResource(String name, byte[] data) {
		isInitialized = false;
		this.data = data;
		this.name = name;
	}

	@Override
	public void init() {
		if(isInitialized)
			return;
		content = new JsonParser().parse(new String(data));
	}
	
	public JsonElement getJsonElement(){
		if(!isInitialized)
			init();
		return content;
	}
	
	@Override
	public Object getContent() {
		return getJsonElement();
	}

	@Override
	public Component getComponent() {
		JTextArea display = new JTextArea();
		display.setText(new Gson().toJson(getJsonElement()));
		return new JScrollPane(display);
	}

	@Override
	public Resource clone() {
		return new JsonResource(getName(), getData());
	}

	@Override
	public FileFilter getFileFilter() {
		return new FileNameExtensionFilter("JSON File", "*.json", ".json", "json");
	}
	
}