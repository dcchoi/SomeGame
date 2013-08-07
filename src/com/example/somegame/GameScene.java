package com.example.somegame;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;
import com.example.somegame.SceneManager.SceneType;

public class GameScene extends BaseScene{
	public static boolean flag = true;
	final float xC = ResourcesManager.CAMERA_WIDTH/3;
	final float yC = ResourcesManager.CAMERA_HEIGHT/3;
	static int xScore = 0;
	static int oScore = 0;

	@Override
	public void createScene() {
	    createBackground();	
	    addMarkers();
	}

	@Override
	public void onBackKeyPressed() {
	    SceneManager.getInstance().loadMenuScene(engine);
		
	}

	@Override
	public SceneType getSceneType() {
        
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		camera.setHUD(null);
	    camera.setCenter(ResourcesManager.CAMERA_WIDTH/2,ResourcesManager.CAMERA_HEIGHT/2);

	    // TODO code responsible for disposing scene
	    // removing all game scene objects.
	}

private void createBackground()
{
	Sprite bg = new Sprite(0, 0, ResourcesManager.mBackgroundTextureRegion, vbom);
	setBackground(new SpriteBackground(bg));
    
    
}

public void addMarkers(){

		// Create X and O Sprites		
	final Rect[][] rect = new Rect[3][3];

				final Sprite[][] spO = new Sprite[3][3];
				final Sprite[][] spX = new Sprite[3][3];

				for(int i=0; i<3; i++){
					for(int j=0; j<3; j++){
						spO[i][j] = new Sprite(0,0, ResourcesManager.mO,  vbom);
						spX[i][j] = new Sprite(0,0, ResourcesManager.mX,  vbom);

					}
					}
//	
		 //Create touchevents from rectangles
				
				
				for(int i=0; i<3; i++){
					for(int j=0; j<3; j++){
						rect[i][j]= new Rect(i*xC,j*yC,xC,yC, vbom,i,j){
							boolean tag = true;
							@Override
							public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
								switch (pSceneTouchEvent.getAction()) {
			                    case TouchEvent.ACTION_DOWN: {    
			                    	if(tag == true){
			                    		if(flag){
			                    		attachChild(spO[rowID][colID]);
			                    		setMarker('O');
			                    		flag = false;		
			                    		if(checkWinCondition(rect,'O') == 'O'){
			                    			oScore++;
			                    			createPopup();
			                    		}
			                    		}else{
			                    		attachChild(spX[rowID][colID]);
			                    		setMarker('X');
			                    		flag = true;
			                    		if(checkWinCondition(rect,'X') == 'X'){
			                    			xScore++;
			                    			createPopup();
			                    		}
			                    		}
			                    		tag = false;
			                    	}
			                    	}
			                    }
								return true;
							}
					};
			
				}
				}
				

				
			//put rectangles into the scene, add color
				int k = 0;
				for(int i=2; i>=0; i--){
					for(int j=2; j>=0; j--){
						attachChild(rect[i][j]);
						registerTouchArea(rect[i][j]);
						rect[i][j].setAlpha(255);
						
						k++;
					}
				}
		
				setTouchAreaBindingOnActionDownEnabled(true);
				
}


		public void createPopup(){
			SceneManager.getInstance().loadPopupScene(engine);
		}
		
		public char checkWinCondition(Rect[][] rect,char marker){
			for(int i=0; i<rect.length; i++){
				if((rect[i][0].getMarker() == marker &&  // check all columns
				   rect[i][1].getMarker() == marker &&
				   rect[i][2].getMarker() == marker)
				   	||
				   	(rect[0][i].getMarker() == marker && //check all rows
					rect[1][i].getMarker() == marker &&
					rect[2][i].getMarker() == marker)
					||
					(rect[0][0].getMarker() == marker && //check diagonal left to right;
					rect[1][1].getMarker() == marker &&
					rect[2][2].getMarker() == marker
							)
					||
					(rect[0][2].getMarker() == marker && //check diagonal right to left;
					rect[1][1].getMarker() == marker &&
					rect[2][0].getMarker() == marker
							)	
						
						)
					{
					return marker;
				}
			}
			return ' ';
		}
		
		public static void resetGame(){
			oScore=0;
        	xScore=0;
        	flag = true;
		}
		
}
