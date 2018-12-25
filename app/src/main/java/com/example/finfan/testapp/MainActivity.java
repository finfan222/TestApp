package com.example.finfan.testapp;

import android.os.Bundle;
import android.renderscript.Long2;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayDeque;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private static final String ACTION_FAILED = "Invalid value.";

    private TextView calcField, resultField;

    private Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bAdd,bSub,bEqual,bMul,bDiv,bClear,bDel;
    private boolean mustBeCalculated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		calcField = (TextView) findViewById(R.id.idown);
		resultField = (TextView) findViewById(R.id.iup);
		b0 = (Button) findViewById(R.id.b0);
		b0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b0.getText());
			}
		});

		b1 = (Button) findViewById(R.id.b1);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b1.getText());
			}
		});

		b2 = (Button) findViewById(R.id.b2);
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b2.getText());
			}
		});

		b3 = (Button) findViewById(R.id.b3);
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b3.getText());
			}
		});

		b4 = (Button) findViewById(R.id.b4);
		b4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b4.getText());
			}
		});

		b5 = (Button) findViewById(R.id.b5);
		b5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b5.getText());
			}
		});

		b6 = (Button) findViewById(R.id.b6);
		b6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b6.getText());
			}
		});

		b7 = (Button) findViewById(R.id.b7);
		b7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b7.getText());
			}
		});

		b8 = (Button) findViewById(R.id.b8);
		b8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b8.getText());
			}
		});

		b9 = (Button) findViewById(R.id.b9);
		b9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b9.getText());
			}
		});

		bAdd = (Button) findViewById(R.id.bAdd);
		bAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handle("\\+");
			}
		});

		bSub = (Button) findViewById(R.id.bSub);
		bSub.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handle("-");
			}
		});

		bMul = (Button) findViewById(R.id.bMul);
		bMul.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handle("×");
			}
		});

		bDiv = (Button) findViewById(R.id.bDiv);
		bDiv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handle("÷");
			}
		});

		bEqual = (Button) findViewById(R.id.bEqual);
		bEqual.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mustBeCalculated = true;
				final String calcText = calcField.getText().toString();
				if (calcText.contains("+")) {
					handle("\\+");
				} else if(calcText.contains("-")) {
					handle("-");
				} else if(calcText.contains("×")) {
					handle("×");
				} else if(calcText.contains("÷")) {
					handle("÷");
				}
			}
		});

		bDel = (Button) findViewById(R.id.bDel);
		bClear = (Button) findViewById(R.id.bClear);
	}

	private void handle(String operation) {
		if(mustBeCalculated) {
			String[] splitter = calcField.getText().toString().split(operation);
			int result = 0;
			switch(operation) {
				case "+":
					result = Integer.parseInt(splitter[0]) + Integer.parseInt(splitter[1]);
					break;
				case "-":
					result = Integer.parseInt(splitter[0]) - Integer.parseInt(splitter[1]);
					break;
				case "×":
					result = Integer.parseInt(splitter[0]) * Integer.parseInt(splitter[1]);
					break;
				case "÷":
					result = Integer.parseInt(splitter[0]) / Integer.parseInt(splitter[1]);
					break;
			}
			resultField.setText(String.valueOf(result));
			calcField.setText("");
			mustBeCalculated = false;
		} else {
			switch(operation) {
				case "+":
					calcField.append(bAdd.getText());
					break;
				case "-":
					calcField.append(bSub.getText());
					break;
				case "×":
					calcField.append(bMul.getText());
					break;
				case "÷":
					calcField.append(bDiv.getText());
					break;
			}
			mustBeCalculated = true;
		}
	}

	private void handleNumBtn(CharSequence btntext) {
		calcField.append(btntext);
	}
}
