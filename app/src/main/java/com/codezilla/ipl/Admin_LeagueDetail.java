package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Admin_LeagueDetail extends AppCompatActivity {
    String date;
    String time;
    String title;
    static leagueAdditionListener reference;
    public static void initInterface(leagueAdditionListener referencex)
    {
        reference=referencex;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_league_detail);
        TextView txtdate=findViewById(R.id.txtdate);
        TextView txttime=findViewById(R.id.txttime);
        EditText TXTitle= findViewById(R.id.txtlgname);
        Calendar c= Calendar.getInstance();
        Button btndate= findViewById(R.id.btndate);
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd2 = new DatePickerDialog(Admin_LeagueDetail.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String year = Integer.toString(i);
                        String month = Integer.toString(i1 + 1);
                        String day = Integer.toString(i2);
                        date=year + "-" + month + "-" + day;
                        txtdate.setText(date);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));

                dpd2.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd2.show();
            }
        });
        Button btntime= findViewById(R.id.btntime);
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpd= new TimePickerDialog(Admin_LeagueDetail.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        time=Integer.toString(i)+":"+Integer.toString(i1)+":00";
                        txttime.setText(time);
                    }
                },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),false);
                tpd.show();
            }
        });

        Button btn=findViewById(R.id.setleague);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              title=TXTitle.getText().toString();

              if(!(title.isEmpty())){
              addleague();}
            }
        });

    }
    void addleague()
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.addleagueURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Admin_LeagueDetail.this, "successfully league added", Toast.LENGTH_LONG).show();
                        reference.onLeagueAddition();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        String e= error.toString();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title",title);
                params.put("date", date);
                params.put("time",time);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Admin_LeagueDetail.this);
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(stringRequest);
    }
    public interface leagueAdditionListener{
        public void onLeagueAddition();
    }
}