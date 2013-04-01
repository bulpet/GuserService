package com.guser.service.common;

public class GuserMessage {

	String msg_name;
	String msg_caption;
	String msg_description;
	String msg_link;
	String msg_picture;
	int msg_id;
	Boolean msg_call_dir;
	
	public GuserMessage()
	{
		
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
