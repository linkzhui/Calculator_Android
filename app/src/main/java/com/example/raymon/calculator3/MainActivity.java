package com.example.raymon.calculator3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_add,button_minus,button_mult,button_div,button_equal,button_c;

    LinkedList<String> input = new LinkedList<>();
    String temp_input = "";
    boolean error_flag = false;
    boolean stack_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set onClickListener on every single button
        button_0 = (Button) findViewById(R.id.btn_0);
        button_0.setOnClickListener(this);
        button_1 = (Button) findViewById(R.id.btn_1);
        button_1.setOnClickListener(this);
        button_2 = (Button) findViewById(R.id.btn_2);
        button_2.setOnClickListener(this);
        button_3 = (Button) findViewById(R.id.btn_3);
        button_3.setOnClickListener(this);
        button_4 = (Button) findViewById(R.id.btn_4);
        button_4.setOnClickListener(this);
        button_5 = (Button) findViewById(R.id.btn_5);
        button_5.setOnClickListener(this);
        button_6 = (Button) findViewById(R.id.btn_6);
        button_6.setOnClickListener(this);
        button_7 = (Button) findViewById(R.id.btn_7);
        button_7.setOnClickListener(this);
        button_8 = (Button) findViewById(R.id.btn_8);
        button_8.setOnClickListener(this);
        button_9 = (Button) findViewById(R.id.btn_9);
        button_9.setOnClickListener(this);
        button_add = (Button) findViewById(R.id.btn_add);
        button_add.setOnClickListener(this);
        button_minus = (Button) findViewById(R.id.btn_minus);
        button_minus.setOnClickListener(this);
        button_c = (Button) findViewById(R.id.btn_c);
        button_c.setOnClickListener(this);
        button_mult = (Button) findViewById(R.id.btn_mult);
        button_mult.setOnClickListener(this);
        button_div = (Button) findViewById(R.id.btn_div);
        button_div.setOnClickListener(this);
        button_equal = (Button) findViewById(R.id.btn_equl);
        button_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        TextView result = (TextView) findViewById(R.id.result_area);
        Button b = (Button) v;
        String user_input = b.getText().toString();
        if(v.getId() == R.id.btn_c)
        {
            clear(result);
        }
        else if(!error_flag && !stack_flag)
        {
            if(is_number(v)) {
//                System.out.println("number");
                int input_num = Integer.parseInt(user_input);
                if (!input.isEmpty())
                {
                    if(input.peekFirst().equals("*")||input.peekFirst().equals("/"))
                    {
                        System.out.println("the error flag is true");
                        error_flag = true;
                        result.setText("ERROR");

                    }
                    else if(input.size()==1 && !is_compute_sign(input.peekFirst()))
                    {
                        temp_input = "";
                        input.pollFirst();
                    }
                }
                if(!error_flag && !error_flag)
                {
                    temp_input = temp_input.length()==0||temp_input.equals("0")? input_num+"":temp_input+input_num;
                    if(temp_input.length()>7)
                    {
                        stack_flag = true;
                        result.setText("STACK OVERFLOW");
                    }
                    else{
                        result.setText(temp_input);
                    }
                }

            }
            else if(v.getId()==R.id.btn_equl)
            {

                if(temp_input.length()!=0)
                {
                    input.addLast(temp_input);
                }
                String cal_result = get_result();
                if(!stack_flag && !error_flag)
                {
                    result.setText(cal_result);
                    clear(result);
                    input.offerFirst(cal_result.toString());
                }
                result.setText(cal_result);
            }
            else{
                //compute sign
//                System.out.println("compute symbol");
                if(!input.isEmpty() && temp_input.equals("0") && input.peekLast().equals("/"))
                {
                    System.out.println("the error flag is true");
                    result.setText("ERROR");
                    error_flag = true;
                }
                else{
                    if(temp_input.length()!=0)
                    {
                        input.offerLast(temp_input);
                        temp_input = "";
                    }
                    if(!input.isEmpty() && is_compute_sign(input.peekLast()))
                    {
                        input.pollLast();
                    }
                    input.offerLast(user_input.toString());
//                    result.setText(temp_input);
                }


            }
        }

    }

    void clear(TextView result){
        input.clear();
        temp_input = "";
        error_flag = false;
        stack_flag = false;
        result.setText("");
    }

    boolean is_compute_sign(String a)
    {
        if(a.equals("+")||a.equals("-")||a.equals("/")||a.equals("*"))
        {
            return true;
        }
        return false;
    }

    boolean is_number(View v)
    {

        if(v.getId()==R.id.btn_0||v.getId()==R.id.btn_1||v.getId()==R.id.btn_2||v.getId()==R.id.btn_3||v.getId()==R.id.btn_4||v.getId()==R.id.btn_5||v.getId()==R.id.btn_6||v.getId()==R.id.btn_7||v.getId()==R.id.btn_8||v.getId()==R.id.btn_9)
        {
            return true;
        }

        return false;
    }

    String get_result()
    {
        System.out.println(Arrays.toString(input.toArray()));
        float result = 0;
        while(!input.isEmpty())
        {
            String a = input.pollFirst();

            if(is_compute_sign(a))
            {
                switch(a)
                {
                    case "+":
                        if(!input.isEmpty())
                        {
                            result+= Integer.parseInt(input.pollFirst());
                        }
                        break;

                    case "-":
                        if(!input.isEmpty())
                        {
                            result-= Integer.parseInt(input.pollFirst());
                        }
                        break;

                    case "*":
                        if(!input.isEmpty())
                        {
                            result *= Integer.parseInt(input.pollFirst());
                        }
                        break;

                    case "/":
                        if(!input.isEmpty())
                        {
                            String b = input.pollFirst();
                            if(Integer.parseInt(b)==0)
                            {
                                error_flag = true;
                                return "ERROR";
                            }
                            result /= Integer.parseInt(b);
                        }

                        break;
                }
            }
            else{
                result = Integer.parseInt(a);
            }
        }
        int sign_symbol = result>0? 1:-1;
        return Math.round(Math.abs(result))*sign_symbol+"";


    }
}
