package com.club.cordova.stt;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;


import android.media.MediaPlayer;

public class STT extends CordovaPlugin implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
	// private static final String IS_RECOGNITION_AVAILABLE = "isRecognitionAvailable";
	private static final String START_LISTENING = "startListening";
	// private static final String STOP_LISTENING = "stopListening";
	// private static final String GET_SUPPORTED_LANGUAGES = "getSupportedLanguages";
	// private static final String HAS_PERMISSION = "hasPermission";
	// private static final String REQUEST_PERMISSION = "requestPermission";

  	private MediaPlayer mediaPlayer;
  	private AssetFileDescriptor assetFileDescriptor;
  // private SpeechRecognizer recognizer;
     private CallbackContext callbackContext;

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    activity = cordova.getActivity();
    context = webView.getContext();
    view = webView.getView();

    view.post(new Runnable() {
      @Override
      public void run() {
        // recognizer = SpeechRecognizer.createSpeechRecognizer(activity);
        // SpeechRecognitionListener listener = new SpeechRecognitionListener();
        // recognizer.setRecognitionListener(listener);
        assetFileDescriptor = getResources().openRawResourceFd(R.raw.test_start));
      }
    });
  }

    @Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;
		try {

			if (action.equals(START_LISTENING)) {
		        startListening(args, callbackContext);
		    } else {
		        return false;
		    }
		} catch (Exception e) {
		  e.printStackTrace();
		  callbackContext.error(e.getMessage());
		}

		return false;
	}

	private void startListening(JSONArray args, CallbackContext callbackContext)
            throws JSONException, NullPointerException {
        JSONObject params = args.getJSONObject(0);

        if (params == null) {
            callbackContext.error(ERR_INVALID_OPTIONS);
            return;
        }

        if(this.assetFileDescriptor != null){
            playSound(assetFileDescriptor);
        }
	}


	public boolean playSound(AssetFileDescriptor afd) {
        boolean result = true;
        try {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

	@Override
    public void onCompletion(MediaPlayer mp) {

        if(callbackContext != null)
        {
            callbackContext.success("zoooom");
        }
//        if (isRecording) {
        // startRecording();
//        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
//        if (isRecording) {
//            startRecording(extras);
//        }

        if(callbackContext != null)
        {
            callbackContext.success("zoooom");
        }
        return false;
    }
}