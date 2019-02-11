package com.example.user.parsing4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AsyncHttpClient client;
    JSONArray jarray;
    JSONObject jobject;
    RequestParams params;
    ListView lv,lv1,lv3;
    LayoutInflater inflater;

    ArrayList<String> categorynmeA;
    ArrayList<String> prdtnmeA;
    ArrayList<String> imageA;
    ArrayList<String> costA;

    ArrayList<String> electrocategorynmeA;
    ArrayList<String> electroprdtnmeA;
    ArrayList<String> electroimageA;
    ArrayList<String> electrocostA;

    ArrayList<String> footwrcategorynmeA;
    ArrayList<String> footwrprdtnmeA;
    ArrayList<String> footwrimageA;
    ArrayList<String> footwrcostA;

    TextView catC,proC,costC,catE,proE,costE;
    ImageView imageC,imageE;

    String url="http://srishti-systems.info/projects/online_shopping/viewproduct.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.listvw);
        lv1=findViewById(R.id.listvw1);
        lv3=findViewById(R.id.listvw3);

        client=new AsyncHttpClient();
        params=new RequestParams();

        categorynmeA=new ArrayList<String>();
        prdtnmeA=new ArrayList<String>();
        imageA=new ArrayList<String>();
        costA=new ArrayList<String>();

       electrocategorynmeA=new ArrayList<String>();
        electroprdtnmeA=new ArrayList<String>();
        electroimageA=new ArrayList<String>();
        electrocostA=new ArrayList<String>();

        footwrcategorynmeA=new ArrayList<String>();
        footwrprdtnmeA=new ArrayList<String>();
        footwrimageA=new ArrayList<String>();
        footwrcostA=new ArrayList<String>();



client.get(url,params,new AsyncHttpResponseHandler(){
    @Override
    public void onSuccess(String content) {
        super.onSuccess(content);
        try {
            jobject = new JSONObject(content);

            if (jobject.getString("status").equals("success")) {
                jarray = jobject.getJSONArray("Product_details");

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jobj1 = jarray.getJSONObject(i);



                   if (jobj1.getString("catname").equals("Dress")) {
                       String catnmeR = jobj1.getString("catname");
                       categorynmeA.add("catname"+catnmeR);
                        String pronme = jobj1.getString("productname");
                        prdtnmeA.add("Product name " + pronme);
                        String imageR = jobj1.getString("image");
                        imageA.add("" + imageR);
                        String costtA = jobj1.getString("cost");
                        costA.add("Cost is : " + costtA);
                    }

                else if(jobj1.getString("catname").equals("Electronics"))
                   {
                       String ElectrocatnmeR = jobj1.getString("catname");
                       electrocategorynmeA.add("catname"+ElectrocatnmeR);
                       String Electropronme = jobj1.getString("productname");
                       electroprdtnmeA.add("Product name " + Electropronme);
                       String ElectroimageR = jobj1.getString("image");
                       electroimageA.add("" + ElectroimageR);
                       String ElectrocosttA = jobj1.getString("cost");
                       electrocostA.add("Cost is : " + ElectrocosttA);
                   }
                   else if(jobj1.getString("catname").equals("Foot Wear"))
                   {
                       String footcatnmeR = jobj1.getString("catname");
                       footwrcategorynmeA.add("catname"+footcatnmeR);
                       String footpronme = jobj1.getString("productname");
                       footwrprdtnmeA.add("Product name " + footpronme);
                       String footimageR = jobj1.getString("image");
                       footwrimageA.add("" + footimageR);
                       String footwrcosttA = jobj1.getString("cost");
                       footwrcostA.add("Cost is : " + footwrcosttA);
                   }
                }

           }
            adapter adp=new adapter(MainActivity.this,"Dress");
            adapter adp1=new adapter(MainActivity.this,"Electronics");
            adapter adp2=new adapter(MainActivity.this,"Footwear");
            lv.setAdapter(adp);


             lv1.setAdapter(adp1);
             lv3.setAdapter(adp2);

        }catch (Exception e) {
        }
    }
});



    }
    class adapter extends BaseAdapter
    {
        private Context mContext;
        private String mType;

        private adapter(Context context, String type){
            this.mContext = context;
            this.mType=type;
        }
        @Override
        public int getCount() {
            int count=0;
            if(mType.equals("Dress")) {
                count= prdtnmeA.size();
            }
            if(mType.equals("Electronics")){
                count= electrocategorynmeA.size();
            }
            if(mType.equals("Footwear"))
                count=footwrcategorynmeA.size();
            return count;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflater=(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.product,null);

            catC=convertView.findViewById(R.id.catnmeee);
            proC=convertView.findViewById(R.id.pnme);
            imageC=convertView.findViewById(R.id.img11);
            costC=convertView.findViewById(R.id.costtxt2);


            if(mType.equals("Dress")){
                catC.setText(categorynmeA.get(position));
                proC.setText(prdtnmeA.get(position));

                costC.setText(costA.get(position));
                Picasso.with(getApplicationContext())
                        .load(imageA.get(position))
                        .into(imageC);
            }
           if(mType.equals("Electronics")) {
               catC.setText(electrocategorynmeA.get(position));
               proC.setText(electroprdtnmeA.get(position));
               costC.setText(electrocostA.get(position));
               Picasso.with(getApplicationContext()).load(electroimageA.get(position)).into(imageC);
           }
            if(mType.equals("Footwear")) {
                catC.setText(footwrcategorynmeA.get(position));
                proC.setText(footwrprdtnmeA.get(position));
                costC.setText(footwrcostA.get(position));
                Picasso.with(getApplicationContext()).load(footwrimageA.get(position)).into(imageC);
            }



            return convertView;
        }
     }


}
