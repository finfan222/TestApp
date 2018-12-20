package com.example.finfan.testapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private static final String ACTION_FAILED = "Invalid value.";

    private EditText inputText;
    private TextView outputResultText, outputOperationText;
    private double val1, val2;
    private double lastResult;

    //private Button buttonAdd, buttonSub, buttonMul, buttonDiv, buttonSqrt;

    private enum EOpType {
        ADD("+"),
        SUB("-"),
        MUL("*"),
        DIV("÷"),
        SQRT("√");

        private final String symbol;

        private EOpType(String symbol) {
        	this.symbol = symbol;
		}
    }

    private EOpType opType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText) findViewById(R.id.result);
        inputText.setSelection(0);

		outputResultText = (TextView) findViewById(R.id.journal);
		outputOperationText = (TextView) findViewById(R.id.journal2);

        /*buttonAdd = (Button) findViewById(R.id.btn_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EOperateType.handleButtonClick(v, buttonAdd.getId());
            }
        });

        buttonSub = (Button) findViewById(R.id.btn_sub);
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EOperateType.handleButtonClick(v, buttonSub.getId());
            }
        });

        buttonMul = (Button) findViewById(R.id.btn_mul);
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EOperateType.handleButtonClick(v, buttonMul.getId());
            }
        });

        buttonDiv = (Button) findViewById(R.id.btn_div);
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EOperateType.handleButtonClick(v, buttonDiv.getId());
            }
        });

        buttonSqrt = (Button) findViewById(R.id.btn_sqrt);
        buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EOperateType.handleButtonClick(v, buttonSqrt.getId());
            }
        });*/
    }

    public void onClickButton_Add(View v) {
		handleOperate(v, EOpType.ADD);
    }

    public void onClickButton_Sub(View v) {
		handleOperate(v, EOpType.SUB);
    }

    public void onClickButton_Mul(View v) {
		handleOperate(v, EOpType.MUL);
    }

    public void onClickButton_Div(View v) {
		handleOperate(v, EOpType.DIV);
    }
    public void onClickButton_Sqrt(View v) {

        if(checkNullOrEmptyField()) {
            inputText.setText(ACTION_FAILED);
            return;
        }

        val1 = Integer.parseInt(inputText.getText().toString());
        if(val1 < 0) {
            inputText.setText(ACTION_FAILED);
            return;
        }

        lastResult = Math.sqrt(val1);
		outputResultText.append(String.valueOf(lastResult) + "\n");
		outputOperationText.append(inputText.getText() + " " + EOpType.SQRT.symbol + "\n");
        inputText.setText("");
    }

    public void onClickButton_Clear(View v) {
        inputText.setText("");
        opType = null;
    }

    public void onClickButton_Equal(View v) {
        String text = inputText.getText().toString();

        if(text.equalsIgnoreCase("0") || text == null || text.isEmpty()) {
            inputText.setText("0");
            updateCursorSelection();
            return;
        }

        val2 = Integer.parseInt(text);

        switch(opType) {
            case ADD:
                lastResult = val1 + val2;
                break;

            case SUB:
                lastResult = val1 - val2;
                break;

            case MUL:
                lastResult = val1 * val2;
                break;

            case DIV:
                if(val2 <= 0) {
                    inputText.setText(ACTION_FAILED);
                    return;
                }
                lastResult = val1 / val2;
                break;
        }

		outputResultText.append(String.valueOf(lastResult) + "\n");
        opType = null;
        inputText.setText("");
    }

    public boolean needEqualize(View v, EOpType opType) {
        if(this.opType == null) {
            return false;
        }

        onClickButton_Equal(v);
        return true;
    }

    private void handleOperate(View v, EOpType type) {
		if(checkNullOrEmptyField()) {
			inputText.setText(ACTION_FAILED);
			return;
		}

		val1 = Integer.parseInt(inputText.getText().toString());
		if(needEqualize(v, EOpType.ADD)) {
			return;
		}

		this.opType = type;
		outputOperationText.append(inputText.getText() + " " + type.symbol + "\n");
		inputText.setText("");
	}

    private void updateCursorSelection() {
        inputText.setSelection(inputText.getText().length());
    }

    private boolean checkNullOrEmptyField() {
    	return inputText.getText() == null || inputText.getText().length() == 0 || inputText.getText().toString().equalsIgnoreCase(ACTION_FAILED);
	}
}
