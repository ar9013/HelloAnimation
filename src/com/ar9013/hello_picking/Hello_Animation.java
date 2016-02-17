package com.ar9013.hello_picking;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.Animation;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

/**
 * Sample 7 - how to load an OgreXML model and play an animation, using
 * channels, a controller, and an AnimEventListener.
 */

public class Hello_Animation extends SimpleApplication implements AnimEventListener {

	private AnimChannel channel;
	private AnimControl control;
	Node player;

	public static void main(String[] args) {
		Hello_Animation app = new Hello_Animation();
		app.start();

	}

	@Override
	public void simpleInitApp() {
		viewPort.setBackgroundColor(ColorRGBA.LightGray); // 視野背景亮灰色
		initKeys();
		DirectionalLight dl = new DirectionalLight();
		rootNode.addLight(dl);
		player = (Node) assetManager.loadModel("Models/Oto/Oto.mesh.xml");
		player.setLocalScale(0.5f);
		rootNode.attachChild(player);
		control = player.getControl(AnimControl.class);
	
		control.addListener(this);
		channel = control.createChannel();
		channel.setAnim("stand");

	}

	/** Custom Keybinding: Map named actions to inputs. */
	private void initKeys() {
		inputManager.addMapping("Walk", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addListener(actionListener, "Walk");

	}

	@Override
	public void onAnimChange(AnimControl arg0, AnimChannel arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
		if (animName.equals("walk")) {
			channel.setAnim("stand", 0.50f);
			channel.setLoopMode(LoopMode.DontLoop);
			channel.setSpeed(1f);
		}

	}

	private ActionListener actionListener = new ActionListener() {

		@Override
		public void onAction(String name, boolean keyPressed, float tpf) {
			  if (name.equals("Walk") && !keyPressed) {
			        if (!channel.getAnimationName().equals("Walk")) {
			          channel.setAnim("Walk", 0.50f);
			          channel.setLoopMode(LoopMode.Loop);
			        }
			      }
		}
	};

}
