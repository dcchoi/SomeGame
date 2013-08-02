package com.example.somegame;


import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.example.somegame.SceneManager.SceneType;



public class PopupScene extends BaseScene  implements IOnMenuItemClickListener{
	private MenuScene popupMenuChildScene;
	private final int POPUP_MENU_REPLAY = 0;
	private final int POPUP_MENU_MAINMENU = 1;
	
	
		@Override
		public void createScene() {
			Sprite popupBg = new Sprite(0,0, resourcesManager.popup_region, vbom);
			popupBg.setPosition((ResourcesManager.CAMERA_WIDTH/2)-(popupBg.getWidth()/2),(ResourcesManager.CAMERA_HEIGHT/2)-(popupBg.getHeight()/2));
	    	attachChild(popupBg);
	    	this.createMenuChildScene();
		}

		@Override
		public void onBackKeyPressed() {
			SceneManager.getInstance().loadMenuScene(engine);
		}

		@Override
		public SceneType getSceneType() {
			return SceneType.SCENE_POPUP;

		}

		@Override
		public void disposeScene() {
			camera.setHUD(null);
		    camera.setCenter(ResourcesManager.CAMERA_WIDTH/2,ResourcesManager.CAMERA_HEIGHT/2);
		}

		

		private void createMenuChildScene(){
			popupMenuChildScene = new MenuScene(camera);
			popupMenuChildScene.setPosition(400, 240);
			final IMenuItem replayMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem( POPUP_MENU_REPLAY , resourcesManager.replay_region, vbom), 1.2f, 1);
		    final IMenuItem mainmenuMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem( POPUP_MENU_MAINMENU, resourcesManager.mainmenu_region, vbom), 1.2f, 1);
		    
		    popupMenuChildScene.addMenuItem(replayMenuItem);
		    popupMenuChildScene.addMenuItem(mainmenuMenuItem);
		    
		    
		    popupMenuChildScene.buildAnimations();
		    popupMenuChildScene.setBackgroundEnabled(false);
		    
		    replayMenuItem.setPosition( replayMenuItem.getX() -400,  replayMenuItem.getY() - 300);
		    mainmenuMenuItem.setPosition(mainmenuMenuItem.getX() -400, mainmenuMenuItem.getY()-200 );
		    popupMenuChildScene.setOnMenuItemClickListener(this);
		    this.setChildScene(popupMenuChildScene);
		}
		
		@Override
		public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
				float pMenuItemLocalX, float pMenuItemLocalY) {
			switch(pMenuItem.getID())
	        {
	        case POPUP_MENU_REPLAY:
	        	//Load Game Scene!
	            SceneManager.getInstance().loadGameScene(engine);
	            return true;
	        case POPUP_MENU_MAINMENU:
	        	SceneManager.getInstance().loadMenuScene(engine);
	            return true;
	        default:
	            return false;
	    }
		}
		
		
}