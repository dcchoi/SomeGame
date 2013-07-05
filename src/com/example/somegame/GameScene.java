package com.example.somegame;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import com.example.somegame.SceneManager.SceneType;

public class GameScene extends BaseScene{
	public static boolean flag = true;
	final float xC = 266.6f;
	final float yC = 160;
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
	    camera.setCenter(400, 240);

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
	Rect[][] rect = new Rect[3][3];

				/*final Sprite[][] spO = new Sprite[3][3];
				final Sprite[][] spX = new Sprite[3][3];

				for(int i=0; i<3; i++){
					for(int j=0; j<3; j++){
						spO[i][j] = new Sprite(i*xC,j*yC, ResourcesManager.mO,  vbom);
						spX[i][j] = new Sprite(i*xC,j*yC, ResourcesManager.mX,  vbom);

					}
					}*/
	
					final Sprite sp = new Sprite(1,1,ResourcesManager.mO,  vbom);
					final Sprite sp1 = new Sprite(xC/2,0,ResourcesManager.mX,  vbom);
		 //Create touchevents from rectangles
				for(int i=0; i<3; i++){
					for(int j=0; j<3; j++){
						//rect[i][j]= new Rect(i*100,j*100,(i*100)+100,(j*100)+100, vbom,i,j){
						rect[i][j]= new Rect(i*xC,j*yC,(i*xC)+xC,(j*yC)+yC, vbom,i,j){
							boolean tag = true;
							@Override
							public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {


								switch (pSceneTouchEvent.getAction()) {
			                    case TouchEvent.ACTION_DOWN: {    
			                    	if(tag == true){
			                    		if(flag){
			                    	//	attachChild(spO[rowID][colID]);
			                    			attachChild(sp);
			                    		setMarker('O');
			                    		flag = false;
			                    		}else{
			                    			attachChild(sp1);
			                    	//	attachChild(spX[rowID][colID]);
			                    		setMarker('X');
			                    		flag = true;
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
						rect[i][j].setColor(10*k,20*k,0);
						rect[i][j].setAlpha(200);
						
						k++;
					}
				}
				
				setTouchAreaBindingOnActionDownEnabled(true);
}

}
