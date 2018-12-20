package com.example.finfan.testapp;

import android.view.View;

@Deprecated
public enum EOperateType {
	Add(new OperAdd());

	public static final int SIZE = values().length;

	private final IOperate handler;
	private EOperateType(IOperate e) {
		handler = e;
	}

	public static void handleButtonClick(View v, int id) {
		IOperate handler = null;
		for(EOperateType next : values()) {
			if(next.ordinal() == id) {
				handler = next.handler;
				break;
			}
		}

		if(handler == null) {
			throw new NullPointerException(id + " not supported.");
		}

		handler.onButtonClick(v, id);
	}
}
