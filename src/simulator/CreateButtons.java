package simulator;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CreateButtons {
	
	CreateButtons(){
		
	}
	CreateButtons(String btnName, GridPane pane, int y, int x){
		BrailleTextTranslator txtToBraille = new BrailleTextTranslator();		
		
		Button btn = new Button(btnName);
		btn.setOnAction((event)->{
			System.out.println(txtToBraille.translate(btnName));
		});
		pane.add(btn, y, x);
	}
	
	static void createNumButtons(GridPane pane){
		int y  = 0;
		int x = 0;		
		for(int i = 0;i < 10;i++){			
			String buttonName = Integer.toString(i);
			if(i%2 == 0){
				y = 0;
				x += 1;
			}
			else{y=1;}
			CreateButtons btn = new CreateButtons(buttonName, pane,y,x);			
		}
	}
	
	
}
