package com.dlwx.hzquser.utiles;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayUtils {
	// SoundPool����
	public static SoundPool mSoundPlayer = new SoundPool(10,
			AudioManager.STREAM_SYSTEM, 5);
	public static SoundPlayUtils soundPlayUtils;
	// ������
	static Context mContext;

	/**
	 * ��ʼ��
	 * 
	 * @param context
	 */
	public static SoundPlayUtils init(Context context) {
		if (soundPlayUtils == null) {
			soundPlayUtils = new SoundPlayUtils();
		}

		// ��ʼ������
		mContext = context;

//		mSoundPlayer.load(mContext, R.raw.pop, 1);

		return soundPlayUtils;
	}

	/**
	 * ��������
	 * 
	 * @param soundID
	 */
	public static void play(int soundID,float rate) {
		mSoundPlayer.play(soundID, 1, 1, 0, 0, rate);
	}
}
