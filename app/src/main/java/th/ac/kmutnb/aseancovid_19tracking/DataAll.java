package th.ac.kmutnb.aseancovid_19tracking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataAll extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Data> datas = new ArrayList<>();
    static String Tag = "myapp";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_all);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        //get API data
        queue = Volley.newRequestQueue(this);
        String URL = "https://corona.lmao.ninja/v2/countries?sort=country";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String countryName;
                            Log.i(Tag, "โหลดข้อมูลได้");
                            for(int i=0;i<response.length();i++){
                                JSONObject jsonObject = response.getJSONObject(i);
                                JSONObject countryInfo = jsonObject.getJSONObject("countryInfo");
                                //Log.i(Tag, "ในลูป");
                                countryName = countryInfo.getString("iso2");
                                if((countryName.charAt(0)=='B')&&(countryName.charAt(1)=='N')){
                                    setData(jsonObject,R.drawable.brunei,"บรูไน");
                                }
                                else if((countryName.charAt(0)=='K')&&(countryName.charAt(1)=='H')){
                                    setData(jsonObject,R.drawable.cambo,"กัมพูชา");
                                }
                                else if((countryName.charAt(0)=='P')&&(countryName.charAt(1)=='H')){
                                    setData(jsonObject,R.drawable.flip,"ฟิลิปปินส์");
                                }
                                else if((countryName.charAt(0)=='I')&&(countryName.charAt(1)=='D')){
                                    setData(jsonObject,R.drawable.indo,"อินโดนีเซีย");
                                }
                                else if((countryName.charAt(0)=='M')&&(countryName.charAt(1)=='Y')){
                                    setData(jsonObject,R.drawable.malay,"มาเลเซีย");
                                }
                                else if((countryName.charAt(0)=='M')&&(countryName.charAt(1)=='M')){
                                    setData(jsonObject,R.drawable.myan,"เมียนม่า");
                                }
                                else if((countryName.charAt(0)=='S')&&(countryName.charAt(1)=='G')){
                                    setData(jsonObject,R.drawable.sing,"สิงคโปร์");
                                }
                                else if((countryName.charAt(0)=='T')&&(countryName.charAt(1)=='H')){
                                    setData(jsonObject,R.drawable.thai,"ไทย");
                                }
                                else if((countryName.charAt(0)=='V')&&(countryName.charAt(1)=='N')){
                                    setData(jsonObject,R.drawable.viet,"เวียดนาม");
                                }
                                else if((countryName.charAt(0)=='L')&&(countryName.charAt(1)=='A')){
                                    setData(jsonObject,R.drawable.lao,"ลาว");
                                }
                            }
                            setList();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    public void setData(JSONObject jsonObject,int mIcon,String country) throws JSONException {
        Data item = new Data();

        item.setmText1(country);
        item.setmNum(jsonObject.getInt("cases"));
        item.setTodayInfected(jsonObject.getInt("todayCases"));
        item.setDeath(jsonObject.getInt("deaths"));
        item.setTodayDeath(jsonObject.getInt("todayDeaths"));
        item.setRecovered(jsonObject.getInt("recovered"));
        item.setTodayRecovered(jsonObject.getInt("todayRecovered"));
        item.setmIcon(mIcon);

        datas.add(item);
    }

    public void setList(){
        ListView lv = findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this,datas);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick (AdapterView<?> adapterView, View view, int i, long l) {
        /*Toast.makeText(this, String.valueOf(i+1)+ " "
                + datas.get(i).getmText1(), Toast.LENGTH_SHORT).show();*/
        //Log.i(Tag, String.valueOf(i));

        Intent itn = new Intent( this , OneData.class);
        itn.putExtra("data",datas.get(i));
        startActivity(itn);

    }


}