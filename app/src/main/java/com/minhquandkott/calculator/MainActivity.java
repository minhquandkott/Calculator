package com.minhquandkott.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nhapso;
    private TextView ketqua;

    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;
    private Button num0;

    private Button numplus;
    private Button numminus;
    private Button nummulti;
    private Button numdevide;

    private Button numc;
    private Button numac;
    private Button numphay;
    private Button numberequal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setEventClickViews();
    }

    public void initWidget(){
        ketqua= (TextView) findViewById(R.id.ketqua);
        nhapso= (EditText) findViewById(R.id.nhapso);
        num0=(Button) findViewById(R.id.num0);
        num1=(Button)findViewById(R.id.num1);
        num2=(Button)findViewById(R.id.num2);
        num3=(Button)findViewById(R.id.num3);
        num4=(Button)findViewById(R.id.num4);
        num5=(Button)findViewById(R.id.num5);
        num6=(Button)findViewById(R.id.num6);
        num7=(Button)findViewById(R.id.num7);
        num8=(Button)findViewById(R.id.num8);
        num9=(Button)findViewById(R.id.num9);
        numminus=(Button)findViewById(R.id.numminus);
        numplus=(Button)findViewById(R.id.numplus);
        nummulti=(Button)findViewById(R.id.nummulti);
        numdevide=(Button)findViewById(R.id.numdevide);
        numphay=(Button)findViewById(R.id.numphay);
        numac=(Button)findViewById(R.id.numac);
        numc=(Button)findViewById(R.id.numc);
        numberequal=(Button)findViewById(R.id.numberequal);
    }
    public void setEventClickViews(){
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        numminus.setOnClickListener(this);
        numplus.setOnClickListener(this);
        nummulti.setOnClickListener(this);
        numdevide.setOnClickListener(this);
        numphay.setOnClickListener(this);
        numac.setOnClickListener(this);
        numc.setOnClickListener(this);
        numberequal.setOnClickListener(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.num0:
                nhapso.append("0");
                break;
            case R.id.num1:
                nhapso.append("1");
                break;
            case R.id.num2:
                nhapso.append("2");
                break;
            case R.id.num3:
                nhapso.append("3");
                break;
            case R.id.num4:
                nhapso.append("4");
                break;
            case R.id.num5:
                nhapso.append("5");
                break;
            case R.id.num6:
                nhapso.append("6");
                break;
            case R.id.num7:
                nhapso.append("7");
                break;
            case R.id.num8:
                nhapso.append("8");
                break;
            case R.id.num9:
                nhapso.append("9");
                break;
            case R.id.numminus:
                nhapso.append("-");
                break;
            case R.id.numplus:
                nhapso.append("+");
                break;
            case R.id.nummulti:
                nhapso.append("*");
                break;
            case R.id.numdevide:
                nhapso.append("/");
                break;
            case R.id.numphay:
                nhapso.append(".");
                break;
            case R.id.numc:
                BaseInputConnection textFieldInputConnection = new BaseInputConnection(nhapso, true);
                textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_DEL));
                break;
            case R.id.numac:
                nhapso.setText("");
                ketqua.setText("");
                break;
            case R.id.numberequal:
                DecimalFormat df = new DecimalFormat("###.#######");
                double result = 0;
                addOperation(nhapso.getText().toString());
                addNumber(nhapso.getText().toString());
                // Thuật toán tính toán biểu thức
                if(arrOperation.size()>=arrNumber.size() ||arrOperation.size()<1){
                    ketqua.setText("Lỗi định dạng");
                }else {
                    for (int i = 0; i < (arrNumber.size() - 1); i++) {
                        switch (arrOperation.get(i)) {
                            case "+":
                                if (i == 0) {
                                    result = arrNumber.get(i) + arrNumber.get(i + 1);
                                } else {
                                    result = result + arrNumber.get(i + 1);
                                }
                                break;
                            case "-":
                                if (i == 0) {
                                    result = arrNumber.get(i) - arrNumber.get(i + 1);
                                } else {
                                    result = result - arrNumber.get(i + 1);
                                }
                                break;
                            case "*":
                                if (i == 0) {
                                    result = arrNumber.get(i) * arrNumber.get(i + 1);
                                } else {
                                    result = result * arrNumber.get(i + 1);
                                }
                                break;
                            case "/":
                                if (i == 0) {
                                    result = arrNumber.get(i) / arrNumber.get(i + 1);
                                } else {
                                    result = result / arrNumber.get(i + 1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    ketqua.setText(df.format(result) + "");
                }
                break;

        }

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //Mảng chứa các phép tính +, - , x, /
    public ArrayList<String> arrOperation;
    //Mảng chứa các số
    public ArrayList<Double> arrNumber;

    //Lấy tất cả các phép tính lưu vào mảng arrOperation
    public int addOperation(String input) {
        arrOperation = new ArrayList<>();

        char[] cArray = input.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '*':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }
    //Lấy tất cả các số lưu vào mảng arrNumber
    public void addNumber(String stringInput) {
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
}
