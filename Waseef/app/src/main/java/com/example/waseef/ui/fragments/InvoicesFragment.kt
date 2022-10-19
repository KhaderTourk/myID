package com.example.waseef.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waseef.adapter.InvoicesAdapter
import com.example.waseef.data.ServiceBuilder
import com.example.waseef.data.network.WaseefApi
import com.example.waseef.databinding.FragmentInvoicesBinding
import com.example.waseef.model.*
import com.example.waseef.util.Constants.USER_ID
import retrofit2.Call
import retrofit2.Callback

class InvoicesFragment : Fragment() {
    private var _binding: FragmentInvoicesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInvoicesBinding.inflate(inflater, container, false)
        getData()

        return binding.root
    }

    private fun fillNewsRecycle(invoicesList: List<InvoiceListItem>) {
        val invoiceAdapter = InvoicesAdapter()

        invoiceAdapter.setData(invoicesList)
        binding.rvInvoices.adapter = invoiceAdapter
        binding.rvInvoices.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
    }

    private fun getData(){
        val newResponse = InvoicesListData()
        newResponse.UserID = USER_ID
        newResponse.TransactionType = 0

        val loginService = ServiceBuilder.buildService(WaseefApi::class.java)
        val requestCall = loginService.getInvoicesList(login= newResponse)

        requestCall.enqueue(object: Callback<AllInvoicesResponse> {

            override fun onResponse(call: Call<AllInvoicesResponse>, response: retrofit2.Response<AllInvoicesResponse>) {
                if (response.isSuccessful) {
                    val userLogged = response.body() // Use it or ignore it
                    if (userLogged!!.code == 200) {
                        fillNewsRecycle(invoicesList = userLogged.response)
                    }else {
                        Toast.makeText(binding.root.context, "No Data!!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(binding.root.context, "Maybe server error!!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AllInvoicesResponse>, t: Throwable) {
                Toast.makeText(binding.root.context, "Check Connection", Toast.LENGTH_SHORT).show()
            }
        })
    }
}