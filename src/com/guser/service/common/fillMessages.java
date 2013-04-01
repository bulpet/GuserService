package com.guser.service.common;

import android.content.Context;

import com.guser.service.DB.DatabaseHandler;

public class fillMessages {
	private Context context;
	
	public fillMessages(Context context) {
		this.context = context;
	}
	
	public void fillOnce()
	{
		GuserMessage msg = new GuserMessage();
		msg.setMsg_name("Мужичок неказист, да в плечах харчист");
		msg.setMsg_caption("");
		msg.setMsg_description("call from #C, Monitoring call - #H hr #M min #S sec,   Full time - #FT");
		msg.setMsg_link("http://www.wtr.ru/aphorism/prov1.htm");
	
		DatabaseHandler db = new DatabaseHandler(this.context);
		db.addMessage(msg);
		
		msg = new GuserMessage();
		msg.setMsg_name("Куда голова, туда и животы");
		msg.setMsg_caption("");
		msg.setMsg_description("call from #C, Monitoring call - #H hr #M min #S sec,   Full time - #FT");
		msg.setMsg_link("http://www.wtr.ru/aphorism/prov1.htm");
		
		db.addMessage(msg);
		
		msg = new GuserMessage();
		msg.setMsg_name("Красно, да полиняло; умно, да поветшало");
		msg.setMsg_caption("");
		msg.setMsg_description("call from #C, Monitoring call - #H hr #M min #S sec,   Full time - #FT");
		msg.setMsg_link("http://www.wtr.ru/aphorism/prov1.htm");
		
		db.addMessage(msg);
		
		msg = new GuserMessage();
		msg.setMsg_name("Попытка не пытка, а спрос не беда");
		msg.setMsg_caption("");
		msg.setMsg_description("call from #C, Monitoring call - #H hr #M min #S sec,   Full time - #FT");
		msg.setMsg_link("http://www.wtr.ru/aphorism/prov1.htm");
		
		db.addMessage(msg);
		
		msg = new GuserMessage();
		msg.setMsg_name("Поглядел дурак на дурака да плюнул: эка невидаль - кака");
		msg.setMsg_caption("");
		msg.setMsg_description("call from #C, Monitoring call - #H hr #M min #S sec,   Full time - #FT");
		msg.setMsg_link("http://www.wtr.ru/aphorism/prov1.htm");
		
		db.addMessage(msg);
		
		msg = new GuserMessage();
		msg.setMsg_name("Полегчало нашей бабушке - пореже стала дышать");
		msg.setMsg_caption("");
		msg.setMsg_description("call from #C, Monitoring call - #H hr #M min #S sec,   Full time - #FT");
		msg.setMsg_link("http://www.wtr.ru/aphorism/prov1.htm");
		
		db.addMessage(msg);
		
		msg = new GuserMessage();
		msg.setMsg_name("С чем свыкнешься, с тем не расстанешься");
		msg.setMsg_caption("");
		msg.setMsg_description("call from #C, Monitoring call - #H hr #M min #S sec,   Full time - #FT");
		msg.setMsg_link("http://www.wtr.ru/aphorism/prov1.htm");
		
		db.addMessage(msg);
		
	}
}
