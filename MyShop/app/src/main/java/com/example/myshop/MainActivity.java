package com.example.myshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myshop.adapters.ProductAdapter;
import com.example.myshop.data.DBHelper;
import com.example.myshop.data.Product;
import com.example.myshop.ui.AddNewProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        DBHelper mydb = new DBHelper(this);

        if (mydb.numberOfRows() > 0) {
        ArrayList<Product> myListData = mydb.getAllProducts();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycle_view);
        ProductAdapter adapter = new ProductAdapter(myListData);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), AddNewProduct.class);
                        startActivity(intent);
            }
        });
    }

//    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//         builder.setMessage(R.string.deleteContact)
//            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//        public void onClick(DialogInterface dialog, int id) {
//            mydb.deleteContact(id_To_Update);
//            Toast.makeText(getApplicationContext(), "Deleted Successfully",
//                    Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//            startActivity(intent);
//        }
//    })
//            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
//        public void onClick(DialogInterface dialog, int id) {
//            // User cancelled the dialog
//        }
//    });
//
//    AlertDialog d = builder.create();
//         d.setTitle("Are you sure");
//         d.show();
//
//
//
//         if(mydb.insertContact(name.getText().toString(), phone.getText().toString(),
//				   email.getText().toString(), street.getText().toString(),
//				   place.getText().toString())){
//        Toast.makeText(getApplicationContext(), "done",
//                Toast.LENGTH_SHORT).show();
//    } else{
//        Toast.makeText(getApplicationContext(), "not done",
//                Toast.LENGTH_SHORT).show();
//    }
//    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//    startActivity(intent);
}