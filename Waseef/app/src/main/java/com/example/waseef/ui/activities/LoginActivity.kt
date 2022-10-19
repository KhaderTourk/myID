package com.example.waseef.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.waseef.R
import com.example.waseef.data.ServiceBuilder
import com.example.waseef.data.network.WaseefApi
import com.example.waseef.model.LoginResponse
import com.example.waseef.model.UserDataLogin
import com.example.waseef.util.Constants.USER_ID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback

@ExperimentalCoroutinesApi
class LoginActivity : AppCompatActivity() {
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progress = findViewById(R.id.login_progress)
        loginButton = findViewById(R.id.btn_login)
        userName = findViewById(R.id.et_login_user_name)
        password = findViewById(R.id.et_login_password)
        val intent = Intent(this, MainActivity::class.java)

        loginButton.setOnClickListener {
            if (validate() == "" ) {
                progress.visibility = View.VISIBLE
                val newResponse = UserDataLogin()
                newResponse.UserName = userName.text.toString()
                newResponse.Password = password.text.toString()

                val loginService = ServiceBuilder.buildService(WaseefApi::class.java)
                val requestCall = loginService.login(login= newResponse)

                requestCall.enqueue(object: Callback<LoginResponse> {

                    override fun onResponse(call: Call<LoginResponse>, response: retrofit2.Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            val userLogged = response.body() // Use it or ignore it
                            if (userLogged!!.code == 200) {
                                USER_ID = userLogged.response.UserID
                                Log.e("USER_ID",USER_ID.toString())
                                if (progress.visibility == View.VISIBLE)
                                progress.visibility = View.GONE
                                startActivity(intent)
                                finish()
                            }else {
                                if (progress.visibility == View.VISIBLE)
                                progress.visibility = View.GONE
                                Toast.makeText(this@LoginActivity, "User Not Found", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            if (progress.visibility == View.VISIBLE)
                            progress.visibility = View.GONE
                            Toast.makeText(this@LoginActivity, "Maybe server error", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        if (progress.visibility == View.VISIBLE)
                        progress.visibility = View.GONE
                        Toast.makeText(this@LoginActivity, "Check Connection", Toast.LENGTH_SHORT).show()
                    }
                })
            }else
                Toast.makeText(this, validate(),Toast.LENGTH_SHORT).show()
        }
    }
    private fun validate(): String{
        return if (userName.text.toString() == "" && password.text.toString() == "")
            "Please fill the fields!!"
        else if (userName.text.toString() == "")
            "Please fill user name field!!"
        else if (password.text.toString() == "")
            "Please fill password field!!"
        else
            ""
    }
}