package com.example.todo_for_blockchain.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.todo_for_blockchain.MySingleton
import com.example.todo_for_blockchain.databinding.FragmentAllUncompleteBinding
import com.example.todo_for_blockchain.model.Task
import com.example.todo_for_blockchain.util.CCcompanion
import com.example.todo_for_blockchain.util.CCcompanion.Companion.ADDRESS
import com.example.todo_for_blockchain.util.CCcompanion.Companion.MAIN_PATH
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AllUncompleteFragment : Fragment() {

    private lateinit var  taskAdapterAll : TaskAdapterAll
    private var _binding: FragmentAllUncompleteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAllUncompleteBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        getJSONObject()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    private fun fillNewsRecycle(tasks :ArrayList<Task>) {
        taskAdapterAll = TaskAdapterAll(binding.pb)

        taskAdapterAll.setData(tasks)
        binding.rvAllUncompleted.adapter = taskAdapterAll
        binding.rvAllUncompleted.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getJSONObject() {
       val uriGetMyActions = "${MAIN_PATH}/getTasksUnComplete?purse=${ADDRESS}&privateKey=${CCcompanion.PRIVATE_KEY}"
        binding.pb.visibility = View.VISIBLE
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, uriGetMyActions, null,
            { response ->

                val tasks :ArrayList<Task> = ArrayList()
                val jArray = response.getJSONArray("status")
                    for (task in 0 until jArray.length()){
                        val jsArray = jArray.getJSONArray(task)
                        val jObject = jsArray.getJSONObject(0)
                        val idInHex = jObject.getString("hex").substring(2)
                        val id = idInHex.toLong(radix = 16).toString()
                        val title = jsArray.getString(1)
                        val description = jsArray.getString(2)
                        val owner = jsArray.getString(3)
                        val jObject2 = jsArray.getJSONObject(4)
                        val rewardInHex = jObject2.getString("hex").substring(2)
                        val reward = rewardInHex.toLong(radix = 16).toString()
                        val winner = jsArray.getString(5)
                        val done = jsArray.getBoolean(6)
                        val jObject3 = jsArray.getJSONObject(7)
                        val dateInHex = jObject3.getString("hex").substring(2)
                        val date =getDateTime(dateInHex.toLong(radix = 16).toString())

                        val finished = jsArray.getBoolean(8)
                        Log.e("hzm", id)
                        Log.e("hzm", reward)
                        Log.e("hzm", date!!)
                        Log.e("hzm", title)
                        Log.e("hzm", description)
                        Log.e("hzm", owner)
                        Log.e("hzm", winner)
                        Log.e("hzm", done.toString())
                        Log.e("hzm", finished.toString())
                        tasks.add(Task(id,title,description,owner,reward,winner,date,done,finished))
                    }
                fillNewsRecycle(tasks)
                taskAdapterAll.notifyDataSetChanged()
                binding.pb.visibility = View.GONE
            },
            { error ->
                Log.e("hzm Error", error.toString())
                binding.pb.visibility = View.GONE
            })

        MySingleton.getInstance()!!.addRequestQueue(jsonObjectRequest)

    }
}