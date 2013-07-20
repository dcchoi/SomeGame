package com.example.somegame;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;

import android.os.Bundle;
import android.app.Activity;
import android.service.wallpaper.WallpaperService.Engine;
import android.view.KeyEvent;
import android.view.Menu;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;
 
import java.io.IOException;
import java.io.InputStream;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.entity.sprite.Sprite;
import java.util.Stack;
import org.andengine.input.touch.TouchEvent;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import org.andengine.engine.*;
import org.andengine.*;
public class MainActivity extends BaseGameActivity {

	private MenuScene menuScene;



	//private ITextureRegion mBackgroundTextureRegion,  mO, mX,mMenuRegion1,mMenuRegion2;
	private Camera camera;
	private ResourcesManager resourcesManager;

	
	@Override
	public LimitedFPSEngine onCreateEngine(EngineOptions pEngineOptions) 
	{
	    return new LimitedFPSEngine(pEngineOptions, 60);
	}
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		 camera = new Camera(0, 0,  resourcesManager.CAMERA_WIDTH, resourcesManager.CAMERA_HEIGHT);
		    EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy( 800, 480), this.camera);
		    engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		    engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		    return engineOptions;
	}	
	

	@Override
	public void onCreateResources(	
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		 ResourcesManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
		    resourcesManager = ResourcesManager.getInstance();
		    pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
	    SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() 
	    {
	            public void onTimePassed(final TimerHandler pTimerHandler) 
	            {
	            	mEngine.unregisterUpdateHandler(pTimerHandler);
	                SceneManager.getInstance().createMenuScene();
	            }
	    }));
	    pOnPopulateSceneCallback.onPopulateSceneFinished();
		
	}
	


@Override
public boolean onKeyDown(int keyCode, KeyEvent event) 
{  
    if (keyCode == KeyEvent.KEYCODE_BACK)
    {
        SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
    }
    return false; 
}

}
