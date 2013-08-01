package com.example.somegame;


import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.example.somegame.SceneManager.SceneType;

/**
 * @author Nicolas Gramlich
 * @since 16:36:51 - 03.08.2010
 */
public class PopupScene extends BaseScene {
        // ===========================================================
        // Constants
        // ===========================================================

        // ===========================================================
        // Fields
        // ===========================================================

        // ===========================================================
        // Constructors
        // ===========================================================
/*
        public PopupScene(final Camera pCamera, final Scene pParentScene, final float pDurationSeconds) {
                this(pCamera, pParentScene, pDurationSeconds, null);
        }

        public PopupScene(final Camera pCamera, final Scene pParentScene, final float pDurationSeconds, final Runnable pRunnable) {
                super(pCamera);
                this.setBackgroundEnabled(false);
                pParentScene.setChildScene(this, false, true, true);

                this.registerUpdateHandler(new TimerHandler(pDurationSeconds, new ITimerCallback() {
                        @Override
                        public void onTimePassed(final TimerHandler pTimerHandler) {
                                PopupScene.this.unregisterUpdateHandler(pTimerHandler);
                                pParentScene.clearChildScene();
                                if(pRunnable != null) {
                                        pRunnable.run();
                                }
                        }
                }));
        }
        */

		@Override
		public void createScene() {
			// TODO Auto-generated method stub
			Sprite bg = new Sprite(0, 0, resourcesManager.popup_region, vbom);
	
			
			 bg.setPosition(0, 0);
	    	attachChild( bg);
		}

		@Override
		public void onBackKeyPressed() {
			SceneManager.getInstance().loadGameScene(engine);
		}

		@Override
		public SceneType getSceneType() {
			return SceneType.SCENE_POPUP;

		}

		@Override
		public void disposeScene() {
			// TODO Auto-generated method stub
			camera.setHUD(null);
		    camera.setCenter(ResourcesManager.CAMERA_WIDTH/2,ResourcesManager.CAMERA_HEIGHT/2);
		}

        // ===========================================================
        // Getter & Setter
        // ===========================================================

        // ===========================================================
        // Methods for/from SuperClass/Interfaces
        // ===========================================================

        // ===========================================================
        // Methods
        // ===========================================================

        // ===========================================================
        // Inner and Anonymous Classes
        // ===========================================================
}