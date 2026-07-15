////////////////////////////////////////////////////////
//  NC.java - Transform translation
//	Programmed by wisdom.
//	Copyleft by OneStop VRML 1998.
//	(http://members.xoom.com/vrml/)
//
//	Created by Visual J++ 1.1
////////////////////////////////////////////////////////

import java.awt.*; 
import java.applet.*;
import vrml.external.Browser; 
import vrml.external.Node; 
import vrml.external.field.EventInSFVec3f; 
 
public class NC extends Applet {
	///////////////////////////////////////////////////////////////
	// EventOut을 위해 VRML Class로부터 event member variable을 생성한다.
	///////////////////////////////////////////////////////////////
	EventInSFVec3f bite_EvtOut = null; 
	EventInSFVec3f table_EvtOut = null; 
	
	Scrollbar bite_scroll , table_scroll; 
	Image img;
	
	///////////////////////////////////////////////////////////////
	// VRML의 translation은 각 축방향으로의 이동값을 저장
	///////////////////////////////////////////////////////////////
	float bite_T[] = new float[3]; 
	float table_T[] = new float[3]; 

	public void init() { 
		img = getImage(getDocumentBase(), "ncbg.jpg");

		bite_T[0] = 0.0f; 
		bite_T[1] = 0.0f; 
		bite_T[2] = 0.0f; 

		table_T[0] = 0.0f; 
		table_T[1] = 0.0f; 
		table_T[2] = 0.0f; 

		//////////////////////////////////////////////////////////////
		//	Layout manager를 특별히 지정하지 않을 경우 
		//	default는 FlowLayout이 되며 add method를 사용하여 control을 
		//  추가한다. null은 레이아웃 매니저를 사용하지 않겠다는 뜻.
		//////////////////////////////////////////////////////////////
		setLayout(null);  
		
		///////////////////////////////////////////////////////////////
		// bite_scroll을 scrollbar로부터 생성하며, Scrollbar.VERTICAL은
		// 1로 대치할 수 있다.
		// Scrollbar(int  orientation, int  value, int  visible, int  minimum, int  maximum)
		///////////////////////////////////////////////////////////////
		bite_scroll = new Scrollbar(Scrollbar.VERTICAL, 0, 0, -30, 10); 
		add(bite_scroll); 
		table_scroll = new Scrollbar(Scrollbar.VERTICAL, 0, 0, -20, 20); 
		add(table_scroll); 

		bite_scroll.reshape(130,0,20,150);
		table_scroll.reshape(250,0,20,150);

		///////////////////////////////////////////////////////////////
		// 현재 browser로부터 객체를 생성한다.
		///////////////////////////////////////////////////////////////
		Browser browser = Browser.getBrowser(this);

		///////////////////////////////////////////////////////////////
		// VRML 파일로부터 Node를 가져온다. (case sensitive)
		///////////////////////////////////////////////////////////////
		Node bite_Node = browser.getNode("bite"); 
		Node table_Node = browser.getNode("parent"); 

		///////////////////////////////////////////////////////////////
		// 각 EventIn을 받아들이고 type casting 한다.
		///////////////////////////////////////////////////////////////
		bite_EvtOut = (EventInSFVec3f) bite_Node.getEventIn("set_translation");
		table_EvtOut = (EventInSFVec3f) table_Node.getEventIn("set_translation");
	} 

	///////////////////////////////////////////////////////////
	// Applet의 배경에 이미지를 넣는다.
	///////////////////////////////////////////////////////////
	public void paint (Graphics g) 
	{
		g.drawImage(img, 0, 0, 395, 150, this);
	}

	///////////////////////////////////////////////////////////
	// event를 처리한다.
	// JDK 1.1부터는 processEvent(Event event)로 바뀌나
	// 4.0 web browser에서 지원하지 않는다.
	///////////////////////////////////////////////////////////
	public boolean handleEvent(Event event) {
		if (event.target instanceof Scrollbar) { 
			if(event.target.equals(bite_scroll)) {
				int x_value = bite_scroll.getValue();

				bite_T[1] = (bite_T[1]-x_value)/10.0f;
				bite_EvtOut.setValue(bite_T); 
			} 

			if(event.target.equals(table_scroll)) {
				int x_value = table_scroll.getValue();

				table_T[0] = (table_T[0]-x_value)/10.0f;
				table_EvtOut.setValue(table_T); 
			} 
		} 
		return true; 
	} 
} 