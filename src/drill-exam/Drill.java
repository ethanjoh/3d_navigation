////////////////////////////////////////////////////////
//  Drill.java - Transform translation and rotation
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
import vrml.external.field.EventInSFRotation; 
 
public class Drill extends Applet {
	///////////////////////////////////////////////////////////////
	// EventOut을 위해 VRML Class로부터 event member variable을 생성한다.
	///////////////////////////////////////////////////////////////
	EventInSFVec3f Chuck_EvtOut = null; 
	//EventInSFVec3f hTbl_EvtOut = null; 
	EventInSFVec3f vTbl_EvtOut = null; 
	EventInSFRotation Handle_EvtOut = null;
	EventInSFRotation hHandle_EvtOut = null;
	EventInSFRotation vHandle_EvtOut = null;
	
	Image img;
	Scrollbar vTbl_scroll , Chuck_scroll; //hTbl_scroll
	
	///////////////////////////////////////////////////////////////
	// VRML의 translation은 각 축방향으로의 이동값을 저장하며,
	// rotation은 각 축의 방위과 회전각을 저장한다.
	// (x, y, z orientation, angle) 
	///////////////////////////////////////////////////////////////
	float Chuck_T[] = new float[3]; 
	//float hTbl_T[] = new float[3]; 
	float vTbl_T[] = new float[3]; 
	float Chuck_R[] = new float[4];     
	float hHandle_R[] = new float[4];     
	float vHandle_R[] = new float[4];     

	///////////////////////////////////////////////////////////////
	// Drill Handle의 회전 방향을 정하기 위해 scrollbar의 값을
	// 저장한다.
	///////////////////////////////////////////////////////////////
	int old_value1, old_value2, old_value3;

	///////////////////////////////////////////////////////////////
	// Handle은 Y축은 중심으로 회전하므로 rotation member variable의 
	// [1]값을 1로 초기화한다.
	///////////////////////////////////////////////////////////////
	public void init() { 
		img = getImage(getDocumentBase(), "drillbg.jpg");

		Chuck_T[0] = 0.0f; 
		Chuck_T[1] = 0.0f; 
		Chuck_T[2] = 0.0f; 

	//	hTbl_T[0] = 0.0f; 
	//	hTbl_T[1] = 0.0f; 
	//	hTbl_T[2] = 0.0f; 

		vTbl_T[0] = 0.0f; 
		vTbl_T[1] = 0.0f; 
		vTbl_T[2] = 0.0f; 

		Chuck_R[0] = 0.0f;
		Chuck_R[1] = 1.0f;
		Chuck_R[2] = 0.0f;
		Chuck_R[3] = 0.0f;

		hHandle_R[0] = 0.0f;
		hHandle_R[1] = 0.0f;
		hHandle_R[2] = 1.0f;
		hHandle_R[3] = 0.0f;

		vHandle_R[0] = 1.0f;
		vHandle_R[1] = 0.0f;
		vHandle_R[2] = 0.0f;
		vHandle_R[3] = 0.0f;
		
		//////////////////////////////////////////////////////////////
		//	Layout manager를 특별히 지정하지 않을 경우 
		//	default는 FlowLayout이 되며 add method를 사용하여 control을 
		//  추가한다. null은 레이아웃 매니저를 사용하지 않겠다는 뜻.
		//////////////////////////////////////////////////////////////
		setLayout(null);  
		
		///////////////////////////////////////////////////////////////
		// Chuck_scroll을 scrollbar로부터 생성하며, Scrollbar.VERTICAL은
		// 1로 대치할 수 있다.
		// Scrollbar(int  orientation, int  value, int  visible, int  minimum, int  maximum)
		///////////////////////////////////////////////////////////////
	//	hTbl_scroll = new Scrollbar(Scrollbar.VERTICAL, 0, 0, -20, 20); 
		Chuck_scroll = new Scrollbar(Scrollbar.VERTICAL, 0, 0, 0, 10); 
		vTbl_scroll = new Scrollbar(Scrollbar.VERTICAL, 0, 0, -10, 10); 
	//	add(hTbl_scroll); 
		add(Chuck_scroll); 
		add(vTbl_scroll); 

	//	hTbl_scroll.reshape(130,0,20,150); 
		Chuck_scroll.reshape(130,0,20,150);
		vTbl_scroll.reshape(250,0,20,150);

		///////////////////////////////////////////////////////////////
		// 현재 browser로부터 객체를 생성한다.
		///////////////////////////////////////////////////////////////
		Browser browser = Browser.getBrowser(this);

		///////////////////////////////////////////////////////////////
		// VRML 파일로부터 Node를 가져온다. (case sensitive)
		// hhandle2는 테이블 우측 핸들
		///////////////////////////////////////////////////////////////
		Node Chuck_Node = browser.getNode("Chuck"); 
		Node Handle_Node = browser.getNode("Handle"); 
		Node hHandle_Node = browser.getNode("hhandle2"); 
	//	Node hTbl_Node = browser.getNode("htable"); 
		Node vTbl_Node = browser.getNode("ParentTbl"); 
		Node vHandle_Node = browser.getNode("vhandle"); 

		///////////////////////////////////////////////////////////////
		// 각 EventIn을 받아들이고 type casting 한다.
		///////////////////////////////////////////////////////////////
		Chuck_EvtOut = (EventInSFVec3f) Chuck_Node.getEventIn("set_translation");
	//	hTbl_EvtOut = (EventInSFVec3f) hTbl_Node.getEventIn("set_translation");
		vTbl_EvtOut = (EventInSFVec3f) vTbl_Node.getEventIn("set_translation");
		Handle_EvtOut = (EventInSFRotation) Handle_Node.getEventIn("set_rotation");
		hHandle_EvtOut = (EventInSFRotation) hHandle_Node.getEventIn("set_rotation");
		vHandle_EvtOut = (EventInSFRotation) vHandle_Node.getEventIn("set_rotation");
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
	/*		if(event.target.equals(hTbl_scroll)) {
				int x_value = hTbl_scroll.getValue();

				if (old_value1 < x_value) {
					hTbl_T[2] = (hTbl_T[2]-x_value)/10.0f;
					hHandle_R[3] = hHandle_R[3] + 0.52332f;
				}
				else {
					hTbl_T[2] = (hTbl_T[2]-x_value)/10.0f;
					hHandle_R[3] = hHandle_R[3] - 0.52332f;
				}

				old_value1 = hTbl_scroll.getValue();

				///////////////////////////////////////////////////////////////
				// 변환된 값들을 EventOut으로 보낸다.
				///////////////////////////////////////////////////////////////														
				hTbl_EvtOut.setValue(hTbl_T); 
				hHandle_EvtOut.setValue(hHandle_R);
			} */

			if(event.target.equals(Chuck_scroll)) {
				int y_value = Chuck_scroll.getValue(); 

				Chuck_T[1] = -1.0f*y_value/10.0f; 
						
				if (old_value2 < y_value) {
					Chuck_R[3] = Chuck_R[3] + 0.52332f;
				}
				else
					Chuck_R[3] = Chuck_R[3] - 0.52332f;

				old_value2 = Chuck_scroll.getValue();

				Chuck_EvtOut.setValue(Chuck_T); 
				Handle_EvtOut.setValue(Chuck_R);
				
				///////////////////////////////////////////////////////////////
				// Play Drill sound 
				///////////////////////////////////////////////////////////////
				if (old_value2 == 10)
					play(getCodeBase(), "drill.au");
			}

			if(event.target.equals(vTbl_scroll)) {
				int x_value = vTbl_scroll.getValue();

				if (old_value3 < x_value) {
					vTbl_T[0] = (vTbl_T[0]-x_value)/10.0f;
					vHandle_R[3] = vHandle_R[3] - 0.52332f;
				}
				else {
					vTbl_T[0] = (vTbl_T[0]-x_value)/10.0f;
					vHandle_R[3] = vHandle_R[3] + 0.52332f;
				}

				old_value3 = vTbl_scroll.getValue();
				
				vTbl_EvtOut.setValue(vTbl_T); 
				vHandle_EvtOut.setValue(vHandle_R);
			} 
		} 
		return true; 
	} 
} 