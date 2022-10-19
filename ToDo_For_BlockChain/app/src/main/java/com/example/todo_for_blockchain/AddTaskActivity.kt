package com.example.todo_for_blockchain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.todo_for_blockchain.util.CCcompanion

class AddTaskActivity : AppCompatActivity() {

    private lateinit var title: EditText
    lateinit var description: EditText
    lateinit var reward: EditText
    private lateinit var add: Button
    private lateinit var back: ImageView
    private lateinit var pb: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        title = findViewById(R.id.et_title)
        description = findViewById(R.id.et_description)
        reward = findViewById(R.id.et_reward)
        add = findViewById(R.id.btn_add)
        pb = findViewById(R.id.pb_add)
        back = findViewById(R.id.iv_back_btn)

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        add.setOnClickListener {
            if (
                title.text.toString() != "" &&
                description.text.toString() != "" &&
                reward.text.toString().toInt() >= 50 &&
                200 >=  reward.text.toString().toInt() &&
                reward.text.toString() != ""
            ){
                val taskTitle = title.text.toString()
                val taskDescription = description.text.toString()
                val taskReward = reward.text.toString().toInt()

                pb.visibility = View.VISIBLE
                val uri = "${CCcompanion.MAIN_PATH}/newTask?purse=${CCcompanion.ADDRESS}&privateKey=${CCcompanion.PRIVATE_KEY}&title=$taskTitle&description=$taskDescription&reward=$taskReward"

                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST, uri, null,
                    { response ->
                        Log.e("hzm", response.toString())
                        Toast.makeText(this,"Success!",Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        pb.visibility = View.GONE
                        startActivity(intent)
                    },
                    { error ->
                        Log.e("hzm Error", error.toString())
                        Toast.makeText(this,"Something Wrong!",Toast.LENGTH_LONG).show()
                        pb.visibility = View.GONE
                    })

                MySingleton.getInstance()!!.addRequestQueue(jsonObjectRequest)

            }else{
                Toast.makeText(this,"Sure fill the fields and reward between 50/200",Toast.LENGTH_LONG).show()
            }
        }

    }
}