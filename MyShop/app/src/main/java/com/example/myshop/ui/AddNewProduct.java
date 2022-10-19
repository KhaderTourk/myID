package com.example.myshop.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshop.MainActivity;
import com.example.myshop.R;
import com.example.myshop.data.DBHelper;

import java.net.URI;

public class AddNewProduct extends AppCompatActivity {

    EditText productName;
    EditText productPrice;
    EditText productDescription;
    Switch productType;
    ImageView productImage;
    TextView btn_add;
    String setImageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        DBHelper mydb = new DBHelper(this);

        productName = findViewById(R.id.et_product_name);
        productPrice = findViewById(R.id.et_product_price);
        productDescription = findViewById(R.id.et_product_description);
        productType = findViewById(R.id.sw_is_cash);
        productImage = findViewById(R.id.iv_add_product);
        btn_add = findViewById(R.id.btn_add_product);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isCash = "notCash";
                productType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {productType.setActivated(true);}});
                if (productType.isActivated()){
                    isCash = "Cash";

                }

                if (mydb.insertProduct(productName.getText().toString(), productPrice.getText().toString(),
                        isCash, productDescription.getText().toString(),
                        setImageURI)) {
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

    }
    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), 200);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    setImageURI = selectedImageUri.toString();
                    productImage.setImageURI(selectedImageUri);
                }
            }
        }
    }
}