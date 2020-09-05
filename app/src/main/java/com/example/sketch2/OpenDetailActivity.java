package com.example.sketch2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.sketch2.Pojo.Row;

@RequiresApi(api = Build.VERSION_CODES.O)
public class OpenDetailActivity extends AppCompatActivity {

    ImageView detail_image;
    TextView detail_title, detail_reason, detail_date, detail_product_1_content, detail_product_2_content,
            detail_product_3_content, detail_product_4_content, detail_business_1_content,
            detail_business_2_content, detail_business_3_content;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_detail);

        Toolbar myChildToolbar = (Toolbar)findViewById(R.id.my_child_toolbar);
        setSupportActionBar(myChildToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        detail_image = (ImageView) findViewById(R.id.detail_image);

        detail_title = (TextView) findViewById(R.id.detail_title);
        detail_reason = (TextView) findViewById(R.id.detail_reason);
        detail_date = (TextView) findViewById(R.id.detail_date);
        detail_product_1_content = (TextView)findViewById(R.id.detail_product_1_content);
        detail_product_2_content = (TextView)findViewById(R.id.detail_product_2_content);
        detail_product_3_content = (TextView)findViewById(R.id.detail_product_3_content);
        detail_product_4_content = (TextView)findViewById(R.id.detail_product_4_content);
        detail_business_1_content = (TextView)findViewById(R.id.detail_business_1_content);
        detail_business_2_content = (TextView)findViewById(R.id.detail_business_2_content);
        detail_business_3_content = (TextView)findViewById(R.id.detail_business_3_content);

        Intent intent = getIntent();
        Row dangerDetail = intent.getExtras().getParcelable("dangerDetail");

        Glide.with(this).load(setUrl(dangerDetail.getIMG_FILE_PATH())[0]).into(detail_image);

        detail_title.setText(dangerDetail.getPRDTNM());
        detail_reason.setText(dangerDetail.getRTRVLPRVNS());
        detail_date.setText(dangerDetail.getCRET_DTM());
        detail_product_1_content.setText(dangerDetail.getMNFDT());
        detail_product_2_content.setText(dangerDetail.getDISTBTMLMT());
        detail_product_3_content.setText(dangerDetail.getFRMLCUNIT());
        detail_product_4_content.setText(dangerDetail.getPRDLST_TYPE());
        detail_business_1_content.setText(dangerDetail.getBSSHNM());
        detail_business_2_content.setText(dangerDetail.getADDR());
        detail_business_3_content.setText(dangerDetail.getPRCSCITYPOINT_TELNO());


    }

    public String[] setUrl(String Urls){
        String[] parsedUrl = Urls.split(",");
        return parsedUrl;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
