package com.guser.service.common;

import android.os.Bundle;
import android.util.Log;

public class GuserMessage {

	String msg_name = "";
	String msg_caption = "";
	String msg_description = "";
	String msg_link = "";
	String msg_picture = "";
	int msg_id = 369;
	Boolean msg_call_dir;
	
	public GuserMessage(){}
	
	public GuserMessage(Bundle bundleDate)
	{
		if(bundleDate.containsKey("name")==true)
			setMsg_name(bundleDate.getString("name"));
		
		if(bundleDate.containsKey("caption")==true)
			setMsg_caption(bundleDate.getString("caption"));

		if(bundleDate.containsKey("description")==true)
			setMsg_description(bundleDate.getString("description"));
		
		if(bundleDate.containsKey("link")==true)
			setMsg_link(bundleDate.getString("link"));
		
		if(bundleDate.containsKey("picture")==true)
			setMsg_picture(bundleDate.getString("picture"));
		
		if(bundleDate.containsKey("id")==true)
			setMsg_id(bundleDate.getInt("id"));
		
		if(bundleDate.containsKey("dir")==true)
			setMsg_call_dir(bundleDate.getBoolean("dir"));
	}
	public GuserMessage(String name,String caption, String description,String link, String picture, int id, Boolean call_dir)
	{
		setMsg_name(name);
		setMsg_caption(caption);
		setMsg_description(description);
		setMsg_link(link);
		setMsg_picture(picture);
		setMsg_id(id);
		setMsg_call_dir(call_dir);
	}
	public void Log()
	{
		Log.i("GuserMessage", "getMsg_name - " + this.getMsg_name());
		Log.i("GuserMessage", "getMsg_caption - " + this.getMsg_caption());
		Log.i("GuserMessage", "getMsg_description - " + this.getMsg_description());
		Log.i("GuserMessage", "getMsg_link - " + this.getMsg_link());
		Log.i("GuserMessage", "getMsg_picture - " + this.getMsg_picture());
		Log.i("GuserMessage", "getMsg_id - " + this.getMsg_id());
		Log.i("GuserMessage", "getMsg_call_dir - " + this.getMsg_call_dir());
		Log.i("GuserMessage", "----------- END -----------");
		
	}
	
	/**
	 * @return the msg_name
	 */
	public String getMsg_name() {
		return msg_name;
	}

	/**
	 * @param msg_name the msg_name to set
	 */
	public void setMsg_name(String msg_name) {
		this.msg_name = msg_name;
	}

	/**
	 * @return the msg_caption
	 */
	public String getMsg_caption() {
		return msg_caption;
	}

	/**
	 * @param msg_caption the msg_caption to set
	 */
	public void setMsg_caption(String msg_caption) {
		this.msg_caption = msg_caption;
	}

	/**
	 * @return the msg_description
	 */
	public String getMsg_description() {
		return msg_description;
	}

	/**
	 * @param msg_description the msg_description to set
	 */
	public void setMsg_description(String msg_description) {
		this.msg_description = msg_description;
	}

	/**
	 * @return the msg_link
	 */
	public String getMsg_link() {
		return msg_link;
	}

	/**
	 * @param msg_link the msg_link to set
	 */
	public void setMsg_link(String msg_link) {
		this.msg_link = msg_link;
	}

	/**
	 * @return the msg_picture
	 */
	public String getMsg_picture() {
		return msg_picture;
	}

	/**
	 * @param msg_picture the msg_picture to set
	 */
	public void setMsg_picture(String msg_picture) {
		this.msg_picture = msg_picture;
	}

	/**
	 * @return the msg_id
	 */
	public int getMsg_id() {
		return msg_id;
	}

	/**
	 * @param msg_id the msg_id to set
	 */
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}
	
	/**
	 * @return the msg_call_dir
	 */
	public Boolean getMsg_call_dir() {
		return msg_call_dir;
	}

	/**
	 * @param msg_call_dir the msg_call_dir to set
	 */
	public void setMsg_call_dir(Boolean msg_call_dir) {
		this.msg_call_dir = msg_call_dir;
	}	
}
