package com.example.waseef.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.waseef.R
import com.example.waseef.data.ServiceBuilder
import com.example.waseef.data.network.WaseefApi
import com.example.waseef.model.*
import com.example.waseef.util.Constants.USER_ID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback


@ExperimentalCoroutinesApi
class AddNewInvoiceActivity : AppCompatActivity() {
    private lateinit var progress: ProgressBar
    private lateinit var projectCode: TextView
    private lateinit var contractNumber: TextView
    private lateinit var invoiceNumber: TextView
    private lateinit var chargeType: TextView
    private lateinit var totalAmount: TextView
    private lateinit var paidAmount: TextView
    private lateinit var pendingAmount: TextView
    private lateinit var invoiceDate: TextView
    private lateinit var invoiceOldNotes: TextView
    private lateinit var note: EditText
    private lateinit var code: EditText
    private lateinit var search: Button
    private lateinit var send: Button
    private lateinit var ok: Button
    private lateinit var message: TextView
    private lateinit var include: CardView
    private lateinit var radioGroup: RadioGroup
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_invoice)

        (this).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (this).supportActionBar!!.setDisplayShowHomeEnabled(true)

        scrollView = findViewById(R.id.scroll_view)
        invoiceOldNotes = findViewById(R.id.tv_in_notes)
        progress = findViewById(R.id.add_progress)
        code = findViewById(R.id.et_code)
        pendingAmount = findViewById(R.id.tv_pending_amount)
        invoiceDate = findViewById(R.id.tv_invoice_date)
        note = findViewById(R.id.et_invoice_notes)
        projectCode = findViewById(R.id.tv_project_code)
        contractNumber = findViewById(R.id.tv_contract_number)
        invoiceNumber = findViewById(R.id.tv_invoice_number)
        totalAmount = findViewById(R.id.tv_total_amount)
        paidAmount = findViewById(R.id.tv_paid_amount)
        chargeType = findViewById(R.id.tv_charge_type)
        search = findViewById(R.id.btn_search)
        send = findViewById(R.id.btn_send_invoice)
        ok = findViewById(R.id.btn_ok)
        message = findViewById(R.id.tv_message)
        include = findViewById(R.id.included_dialog)
        radioGroup = findViewById(R.id.radioGroup)

        search.setOnClickListener {
            if (validateCode() == "" ) {
                progress.visibility = View.VISIBLE
                val type: Int = when (radioGroup.checkedRadioButtonId) {
                    R.id.rb_invoice -> 1
                    R.id.rb_order -> 2
                    else -> -1
                }

                val loginService = ServiceBuilder.buildService(WaseefApi::class.java)
                val requestCall =
                    loginService.search(code = code.text.toString().toInt(), type = type)

                requestCall.enqueue(object : Callback<SearchResponse> {
                    override fun onResponse(
                        call: Call<SearchResponse>,
                        response: retrofit2.Response<SearchResponse>
                    ) {

                        if (response.isSuccessful) {
                            val userLogged = response.body() // Use it or ignore it
                            if (userLogged != null) {
                                scrollView.fullScroll(View.FOCUS_DOWN)
                                projectCode.text = userLogged.projectCode
                                contractNumber.text = userLogged.contractNumber
                                invoiceNumber.text = userLogged.invoiceNumber.toString()
                                chargeType.text = userLogged.chargeType
                                totalAmount.text = userLogged.totalAmount.toString()
                                paidAmount.text = userLogged.paidAmount.toString()
                                pendingAmount.text = userLogged.pendingAmount.toString()
                                invoiceDate.text = userLogged.invoiceDate
                                invoiceOldNotes.text = userLogged.invoiceNotes

                                send.isEnabled = true
                            } else {
                                Toast.makeText(
                                    this@AddNewInvoiceActivity,
                                    "Error Data Entered!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val emptyText =
                                    this@AddNewInvoiceActivity.resources.getString(R.string.empty)
                                projectCode.text = emptyText
                                contractNumber.text = emptyText
                                invoiceNumber.text = emptyText
                                chargeType.text = emptyText
                                totalAmount.text = emptyText
                                paidAmount.text = emptyText
                                pendingAmount.text = emptyText
                                invoiceDate.text = emptyText
                                invoiceOldNotes.text = emptyText

                                send.isEnabled = false
                            }
                            progress.visibility = View.GONE
                        } else {
                            displayDialog(message = "Maybe server error!!")
                        }
                    }

                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        displayDialog(message = "Check Connection")
                    }
                })
            }else
                Toast.makeText(this, validateCode(),Toast.LENGTH_SHORT).show()
        }

        send.setOnClickListener {
            if (validateNote() == "" ) {
                progress.visibility = View.VISIBLE

                val newResponse = InvoiceDataAdd()
                newResponse.Note = note.text.toString()
                newResponse.InvoiceID = invoiceNumber.text.toString().toInt()
                newResponse.CollertorID = USER_ID

                val loginService = ServiceBuilder.buildService(WaseefApi::class.java)
                val requestCall = loginService.newInvoice(note = newResponse)

                requestCall.enqueue(object: Callback<NewInvoiceResponse> {
                    override fun onResponse(call: Call<NewInvoiceResponse>, response: retrofit2.Response<NewInvoiceResponse>) {
                        if (response.isSuccessful) {
                            val userLogged = response.body() // Use it or ignore it
                            Log.e("type",response.code().toString())
                            Log.e("type",response.toString())
                            if (userLogged!!.code == 200) {
                                displayDialog(message = "Successfully Add")
                            }else {
                                displayDialog(message = "Failed")
                            }
                        } else {
                            displayDialog(message = "Maybe server error")
                            }
                    }

                    override fun onFailure(call: Call<NewInvoiceResponse>, t: Throwable) {
                        displayDialog(message = "Check Connection")
                        }
                })
            }else
                Toast.makeText(this, validateNote(),Toast.LENGTH_SHORT).show()
        }
        }

private fun validateCode(): String{
    return if (code.text.toString() == "")
        "Please fill code field!!"
    else
        ""
}
    private fun validateNote(): String{
        return  if (note.text.toString() == "")
            "Please fill note field!!"
        else
            ""
    }
    private fun displayDialog(message: String){
        include.visibility = View.VISIBLE
        val intent = Intent(this, MainActivity::class.java)
       this.message.text = message
        ok.setOnClickListener {
            if (progress.visibility == View.VISIBLE)
            progress.visibility = View.GONE

            include.visibility = View.GONE
        startActivity(intent)
        finish()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }

}