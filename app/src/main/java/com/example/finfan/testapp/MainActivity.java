package com.example.finfan.testapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Current troubles:
 * <ul>
 *     <li>Double click on operation</li>
 *     <li>Trying to call equal button more than one times</li>
 *     <li>Minimize the text size automaticcally when numbers after point is more than now</li>
 *     <li>Feature: copy old result to set a new calc</li>
 *     <li>Can't set the positive or negative sign to a value</li>
 * </ul>
 */
public class MainActivity extends AppCompatActivity {

	private static final String ACTION_FAILED = "Invalid value.";

    private TextView calcField, resultField;

    private Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bAdd,bSub,bEqual,bMul,bDiv,bClear,bDel,bPoint,bModule;
    private boolean mustBeCalculated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		calcField = (TextView) findViewById(R.id.idown);
		resultField = (TextView) findViewById(R.id.iup);

		////////////////////////////////////////////////////////////////////////////// Number buttons handle

		b0 = (Button) findViewById(R.id.b0);
		b0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b0);
			}
		});

		b1 = (Button) findViewById(R.id.b1);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b1);
			}
		});

		b2 = (Button) findViewById(R.id.b2);
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b2);
			}
		});

		b3 = (Button) findViewById(R.id.b3);
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b3);
			}
		});

		b4 = (Button) findViewById(R.id.b4);
		b4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b4);
			}
		});

		b5 = (Button) findViewById(R.id.b5);
		b5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b5);
			}
		});

		b6 = (Button) findViewById(R.id.b6);
		b6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b6);
			}
		});

		b7 = (Button) findViewById(R.id.b7);
		b7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b7);
			}
		});

		b8 = (Button) findViewById(R.id.b8);
		b8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b8);
			}
		});

		b9 = (Button) findViewById(R.id.b9);
		b9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(b9);
			}
		});

		//////////////////////////////////////////////////////////////////////////// Operations Buttons

		// operation add val1 to val2
		bAdd = (Button) findViewById(R.id.bAdd);
		bAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleOperateBtn("+");
			}
		});

		// opeartion substract val1 from val2
		bSub = (Button) findViewById(R.id.bSub);
		bSub.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleOperateBtn("-");
			}
		});

		// operation miltiplies the val1 by val2
		bMul = (Button) findViewById(R.id.bMul);
		bMul.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleOperateBtn("×");
			}
		});

		// operation divide of val1 by val2
		bDiv = (Button) findViewById(R.id.bDiv);
		bDiv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleOperateBtn("÷");
			}
		});

		// operation equal (finalize) when other oepration is called
		bEqual = (Button) findViewById(R.id.bEqual);
		bEqual.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mustBeCalculated = true;
				final String calcText = calcField.getText().toString();
				if (calcText.contains("+")) {
					handleOperateBtn("+");
				} else if(calcText.contains("-")) {
					handleOperateBtn("-");
				} else if(calcText.contains("×")) {
					handleOperateBtn("×");
				} else if(calcText.contains("÷")) {
					handleOperateBtn("÷");
				} else if (calcText.contains("%")) {
					handleOperateBtn("%");
				} else {
					mustBeCalculated = false;
				}
			}
		});

		// operation delete last symbol
		bDel = (Button) findViewById(R.id.bDel);
		bDel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				StringBuffer buf = new StringBuffer(calcField.getText().toString());
				buf.deleteCharAt(buf.length() - 1);
				calcField.setText(buf);
			}
		});

		// operation clear all windows and data
		bClear = (Button) findViewById(R.id.bClear);
		bClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calcField.setText("");
				resultField.setText("");
				mustBeCalculated = false;
			}
		});

		// set the point in number (refactor number to double)
		bPoint = (Button) findViewById(R.id.bPoint);
		bPoint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(bPoint);
			}
		});

		// divide by module and get the remain as result
		bModule = (Button) findViewById(R.id.bModule);
		bModule.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNumBtn(bModule);
			}
		});
	}

	private void handleOperateBtn(String operation) {
    	if(calcField.length() <= 0) {
    		return;
		}

		if(mustBeCalculated) {
			String[] splitter = calcField.getText().toString().split(operation.equalsIgnoreCase("+") ? "\\+" : operation); //quick fix for regullar expression matches

			String first = splitter[0];
			String second = splitter[1];

			double value1 = Double.parseDouble(first);
			Double result = null;

			switch(operation) {
				case "+":
					result = value1 + (second != null ? Double.parseDouble(second) : value1);
					break;
				case "-":
					result = value1 - (second != null ? Double.parseDouble(second) : value1);
					break;
				case "×":
					result = value1 * (second != null ? Double.parseDouble(second) : value1);
					break;
				case "÷":
					result = value1 / (second != null ? Double.parseDouble(second) : value1);
					break;
				case "%":
					result = value1 % (second != null ? Double.parseDouble(second) : value1);
					break;
			}

			if(result != null) {
				String resultValue = String.valueOf(result);
				resultField.setText("= " + (resultValue.contains(".") ? resultValue : String.format("%", resultValue)));
			}

			calcField.setText("");
			mustBeCalculated = false;
		} else {
			switch(operation) {
				case "+":
					handleNumBtn(bAdd);
					break;
				case "-":
					handleNumBtn(bSub);
					break;
				case "×":
					handleNumBtn(bMul);
					break;
				case "÷":
					handleNumBtn(bDiv);
					break;
				case "%":
					handleNumBtn(bModule);
					break;
			}
			mustBeCalculated = true;
		}
	}

	private void handleNumBtn(Button btn) {
		calcField.append(btn.getText());
	}
}
