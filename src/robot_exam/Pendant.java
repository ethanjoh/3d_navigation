/*************************************
	programmed by gabson Joh
	
	http://members.xoom.com./vrml
	vrml-2@xoommail.com
*************************************/

import java.applet.*;
import java.awt.*;
import java.lang.Float;
import vrml.external.Browser; 
import vrml.external.Node; 
import vrml.external.field.EventInSFRotation; 

public class Pendant extends Applet
{
	TextField Text1;
	Label Label1;
	CheckboxGroup ChkboxGrp1, ChkboxGrp2;
	Checkbox ChkBox1, ChkBox2, ChkBox3, ChkBox4, ChkBox5, ChkBox6, CWChkBox, CCWChkBox; 
	Button Btn0, Btn1, Btn2, Btn3, Btn4, Btn5, Btn6, Btn7, Btn8, Btn9, PointBtn, RunBtn; 
	Button HomeBtn, ClrBtn;
	String Input = "";

	Browser browser;
	EventInSFRotation Rotation1, Rotation2, Rotation3, Rotation4, Rotation5, Rotation6; 
	float r1_xyz[] = new float[4];     /** (x, y, z orientation, angle) */
	float r2_xyz[] = new float[4];
	float r3_xyz[] = new float[4];
	float r4_xyz[] = new float[4];
	float r5_xyz[] = new float[4];
	float r6_xyz[] = new float[4];
	float Result_value;

	public void init() 
	{
		int i = 0;

		for (i; i<4; i++) {
			r1_xyz[i] = 0.0f; 
			r2_xyz[i] = 0.0f; 
			r3_xyz[i] = 0.0f;
			r4_xyz[i] = 0.0f;
			r5_xyz[i] = 0.0f;
			r6_xyz[i] = 0.0f;
		}

		setLayout(new BorderLayout());

		Panel SelectPanel1 = new Panel();
		SelectPanel1.setLayout(new FlowLayout());

		Text1 = new TextField(5);		SelectPanel1.add(Text1);
		Label1 = new Label("도");		SelectPanel1.add(Label1);

		ChkboxGrp1 = new CheckboxGroup();

		ChkBox1 = new Checkbox("1축", ChkboxGrp1, true);		SelectPanel1.add(ChkBox1);
		ChkBox2 = new Checkbox("2축", ChkboxGrp1, false);		SelectPanel1.add(ChkBox2);
		ChkBox3 = new Checkbox("3축", ChkboxGrp1, false);		SelectPanel1.add(ChkBox3);
		ChkBox4 = new Checkbox("4축", ChkboxGrp1, false);		SelectPanel1.add(ChkBox4);
		ChkBox5 = new Checkbox("5축", ChkboxGrp1, false);		SelectPanel1.add(ChkBox5);
		ChkBox6 = new Checkbox("6축", ChkboxGrp1, false);		SelectPanel1.add(ChkBox6);
		add("North", SelectPanel1);

		Panel OrientPanel = new Panel();
		OrientPanel.setLayout(new FlowLayout());

		ChkboxGrp2 = new CheckboxGroup();

		CWChkBox = new Checkbox("- 방향", ChkboxGrp2, false);
		OrientPanel.add(CWChkBox);
		CCWChkBox = new Checkbox("+ 방향", ChkboxGrp2, true);
		OrientPanel.add(CCWChkBox);
		add("Center", OrientPanel);
		ClrBtn = new Button("Clear");
		OrientPanel.add(ClrBtn);
		HomeBtn = new Button("Home position");
		OrientPanel.add(HomeBtn);
		
		Panel ButtonPanel = new Panel();
		ButtonPanel.setLayout(new GridLayout(1, 12));

		Btn1 = new Button("1");		ButtonPanel.add(Btn1);
		Btn2 = new Button("2");		ButtonPanel.add(Btn2);
		Btn3 = new Button("3");		ButtonPanel.add(Btn3);
		Btn4 = new Button("4");		ButtonPanel.add(Btn4);
		Btn5 = new Button("5");		ButtonPanel.add(Btn5);
		Btn6 = new Button("6");		ButtonPanel.add(Btn6);
		Btn7 = new Button("7");		ButtonPanel.add(Btn7);
		Btn8 = new Button("8");		ButtonPanel.add(Btn8);
		Btn9 = new Button("9");		ButtonPanel.add(Btn9);
		Btn0 = new Button("0");		ButtonPanel.add(Btn0);
		PointBtn = new Button(".");		ButtonPanel.add(PointBtn);
		RunBtn = new Button("RUN");		ButtonPanel.add(RunBtn);
		add("South", ButtonPanel);

		Text1.setText("0");
		/** String을 Float형으로 변환 */
		Result_value = Float.valueOf(Text1.getText()).floatValue();

		Browser browser = Browser.getBrowser(this);
		/** 1축부터 6축까지 설정 */
		Node position1 = browser.getNode("Parent12");
		Node position2 = browser.getNode("Parent23");
		Node position3 = browser.getNode("Parent34");
		Node position4 = browser.getNode("Parent45");
		Node position5 = browser.getNode("Parent56");
		Node position6 = browser.getNode("Axis6");
		
		Rotation1 = (EventInSFRotation) position1.getEventIn("set_rotation");
		Rotation2 = (EventInSFRotation) position2.getEventIn("set_rotation");
		Rotation3 = (EventInSFRotation) position3.getEventIn("set_rotation");
		Rotation4 = (EventInSFRotation) position4.getEventIn("set_rotation");
		Rotation5 = (EventInSFRotation) position5.getEventIn("set_rotation");
		Rotation6 = (EventInSFRotation) position6.getEventIn("set_rotation");
	}

	public boolean action(Event e, Object o) 
	{
		if (e.target.equals(Btn1))
		{
			Text1.setText(Btn1.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);
			
			/** String -> Float */
			Result_value = Float.valueOf(Text1.getText()).floatValue();
		}
		
		if (e.target.equals(Btn2))
		{
			Text1.setText(Btn2.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);

			Result_value = Float.valueOf(Text1.getText()).floatValue();
		}

		if (e.target.equals(Btn3))
		{
			Text1.setText(Btn3.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);
			
			Result_value = Float.valueOf(Text1.getText()).floatValue();
		}

		if (e.target.equals(Btn4))
		{
			Text1.setText(Btn4.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);
			
			Result_value = Float.valueOf(Text1.getText()).floatValue();
		}

		if (e.target.equals(Btn5))
		{
			Text1.setText(Btn5.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);
			
			Result_value = Float.valueOf(Text1.getText()).floatValue();
		}

		if (e.target.equals(Btn6))
		{
			Text1.setText(Btn6.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);

			Result_value = Float.valueOf(Text1.getText()).floatValue();
		}

		if (e.target.equals(Btn7))
		{
			Text1.setText(Btn7.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);

			Result_value = Float.valueOf(Text1.getText()).floatValue();
		}

		if (e.target.equals(Btn8))
		{
			Text1.setText(Btn8.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);

			Result_value = Float.valueOf(Text1.getText()).floatValue();
		}

		if (e.target.equals(Btn9))
		{
			Text1.setText(Btn9.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);

			Result_value = Float.valueOf(Text1.getText()).floatValue();
		}

		if (e.target.equals(Btn0))
		{
			Text1.setText(Btn0.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);

			Result_value = Float.valueOf(Text1.getText()).floatValue();
		}

		if (e.target.equals(PointBtn))
		{
			Text1.setText(PointBtn.getLabel());
			Input = Input + Text1.getText();
			Text1.setText(Input);
		}

		if (e.target.equals(ClrBtn))
		{
			Text1.setText("0");
			Input = "";
			Result_value = 0.0f;
		}

		if (e.target.equals(RunBtn))
		{
			if (ChkBox1.getState()) 
			{
				r1_xyz[0] = 0.0f; 
				r1_xyz[1] = 0.0f; 
				r1_xyz[2] = 1.0f; 

				if (CCWChkBox.getState())
				{
					r1_xyz[3] = r1_xyz[3] + (Result_value*.017444f); 
				}
				if (CWChkBox.getState())
				{
					r1_xyz[3] = r1_xyz[3] - (Result_value*.017444f); 
				}

				Rotation1.setValue(r1_xyz);
				Text1.setText("0");
				Input = "";
				Result_value = 0.0f;
				showStatus("실행완료. (Run completely)");
			}
			
			if (ChkBox2.getState()) 
			{
				r2_xyz[0] = 1.0f; 
				r2_xyz[1] = 0.0f; 
				r2_xyz[2] = 0.0f; 
				
				if (CCWChkBox.getState())
				{
					r2_xyz[3] = r2_xyz[3] + (Result_value*.017444f); 
				}
				if (CWChkBox.getState())
				{
					r2_xyz[3] = r2_xyz[3] - (Result_value*.017444f); 
				}				
				Rotation2.setValue(r2_xyz);
				Text1.setText("0");
				Input = "";
				Result_value = 0.0f;
				showStatus("실행완료. (Run completely)");
			}

			if (ChkBox3.getState()) 
			{
				r3_xyz[0] = 1.0f; 
				r3_xyz[1] = 0.0f; 
				r3_xyz[2] = 0.0f; 

				if (CCWChkBox.getState())
				{
					r3_xyz[3] = r3_xyz[3] + (Result_value*.017444f); 
				}
				if (CWChkBox.getState())
				{
					r3_xyz[3] = r3_xyz[3] - (Result_value*.017444f); 
				}
				Rotation3.setValue(r3_xyz);
				Text1.setText("0");
				Input = "";
				Result_value = 0.0f;
				showStatus("실행완료. (Run completely)");
			}

			if (ChkBox4.getState()) 
			{
				r4_xyz[0] = 0.0f; 
				r4_xyz[1] = 1.0f; 
				r4_xyz[2] = 0.0f; 

				if (CCWChkBox.getState())
				{
					r4_xyz[3] = r4_xyz[3] + (Result_value*.017444f); 
				}
				if (CWChkBox.getState())
				{
					r4_xyz[3] = r4_xyz[3] - (Result_value*.017444f); 
				}
				Rotation4.setValue(r4_xyz);
				Text1.setText("0");
				Input = "";
				Result_value = 0.0f;
				showStatus("실행완료. (Run completely)");
			}

			if (ChkBox5.getState()) 
			{
				r5_xyz[0] = 1.0f; 
				r5_xyz[1] = 0.0f; 
				r5_xyz[2] = 0.0f; 

				if (CCWChkBox.getState())
				{
					r5_xyz[3] = r5_xyz[3] + (Result_value*.017444f); 
				}
				if (CWChkBox.getState())
				{
					r5_xyz[3] = r5_xyz[3] - (Result_value*.017444f); 
				}
				Rotation5.setValue(r5_xyz);
				Text1.setText("0");
				Input = "";
				Result_value = 0.0f;
				showStatus("실행완료. (Run completely)");
			}

			if (ChkBox6.getState()) 
			{
				r6_xyz[0] = 0.0f; 
				r6_xyz[1] = 1.0f; 
				r6_xyz[2] = 0.0f; 

				if (CCWChkBox.getState())
				{
					r6_xyz[3] = r6_xyz[3] + (Result_value*.017444f); 
				}
				if (CWChkBox.getState())
				{
					r6_xyz[3] = r6_xyz[3] - (Result_value*.017444f); 
				}

				Rotation6.setValue(r6_xyz);
				Text1.setText("0");
				Input = "";
				Result_value = 0.0f;
				showStatus("실행완료. (Run completely)");
			} 
		}

		if (e.target.equals(HomeBtn))
		{
			r1_xyz[3] = 0.0f;
			r2_xyz[3] = 0.0f;
			r3_xyz[3] = 0.0f;
			r4_xyz[3] = 0.0f;
			r5_xyz[3] = 0.0f;
			r6_xyz[3] = 0.0f;

			ChkBox1.setState(true);
			CCWChkBox.setState(true);
			Rotation6.setValue(r6_xyz);
			Rotation5.setValue(r5_xyz);
			Rotation4.setValue(r4_xyz);
			Rotation3.setValue(r3_xyz);
			Rotation2.setValue(r2_xyz);
			Rotation1.setValue(r1_xyz);
		}

		return true;
	}
}


