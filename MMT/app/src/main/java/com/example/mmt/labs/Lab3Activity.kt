package com.example.mmt.labs

import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mmt.ExperimentsActivity
import com.example.mmt.R
import com.example.mmt.adapter.ModelAdapter
import com.example.mmt.model.Model
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Lab3Activity : AppCompatActivity() {

    private lateinit var rvModel : RecyclerView
    private lateinit var constraintLab : ConstraintLayout
    private lateinit var llRestart : LinearLayout
    private lateinit var btnRestart : Button
    private lateinit var btnBack : ImageView
    private lateinit var ivArm : ImageView
    private lateinit var ivFire : ImageView
    private lateinit var ivSmoke : ImageView
    private lateinit var ivTube : ImageView
    private lateinit var ivLitmuse : ImageView
    private lateinit var tvAction : TextView
    private lateinit var componentsInSpace : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab3)
        rvModel = findViewById(R.id.rv_models)
        constraintLab = findViewById(R.id.constrain_lab_3)
        ivArm = findViewById(R.id.iv_arm)
        ivFire = findViewById(R.id.iv_fire)
        ivSmoke = findViewById(R.id.iv_smoke)
        ivTube = findViewById(R.id.iv_test_tube)
        ivLitmuse = findViewById(R.id.iv_litmus)
        llRestart = findViewById(R.id.ll_restart)
        btnRestart = findViewById(R.id.btn_restart)
        btnBack = findViewById(R.id.iv_back_btn)
        tvAction = findViewById(R.id.tv_action)
        componentsInSpace = ArrayList()

        fillNewsRecycle()
        rvModel.setOnDragListener(dragListener)
        constraintLab.setOnDragListener(dragListener)

        btnBack.setOnClickListener {
            val intent = Intent(this, ExperimentsActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnRestart.setOnClickListener {
            clearSpace()
        }

    }

    private fun fillNewsRecycle() {
        val newsAdapter = ModelAdapter()
        val models =ArrayList<Model>()
        models.add(Model(1,"الحامل", R.drawable.ic_arm))
        models.add(Model(2,"لهب بنزن", R.drawable.fire))
        models.add(Model(3,"أنبوب", R.drawable.ic_test_tube))
        models.add(Model(4,"كبريت", R.drawable.sulfur))
        models.add(Model(5,"دوار الشمس الأحمر", R.drawable.ic_litmus_red))
        models.add(Model(6,"دوار الشمس الأزرق", R.drawable.ic_litmus_blue))


        newsAdapter.setData(models)
        rvModel.adapter = newsAdapter
        rvModel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private val dragListener = View.OnDragListener{ view, event ->
        when(event.action){
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true
            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                val item = event.clipData.getItemAt(0)
                when(item.text){
                    "الحامل" -> {
                        ivArm.setImageResource(R.drawable.ic_arm)
                        componentsInSpace.add("الحامل")
                    }
                    "أنبوب" -> {
                        if (componentsInSpace.contains("الحامل")){
                            ivTube.setImageResource(R.drawable.ic_test_tube)
                            componentsInSpace.add("أنبوب")
                        }else{
                            Toast.makeText(this, "أولاً ضع الحامل!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    "لهب بنزن" -> {
                        Glide.with(this).load(R.drawable.fire).into(ivFire)
                        componentsInSpace.add("لهب")
                    }

                    "كبريت" -> {
                        if (componentsInSpace.contains("أنبوب")){
                            ivTube.setImageResource(R.drawable.ic_s_tube)
                            componentsInSpace.add("كبريت")
                        }else{
                            Toast.makeText(this, "ضع أنبوب!!", Toast.LENGTH_SHORT).show()
                        }

                    }
                    "دوار الشمس الأحمر" -> {
                        if (componentsInSpace.contains("mix") && componentsInSpace.contains("كبريت")){
                            ivLitmuse.setImageResource(R.drawable.ic_red_b)
                            componentsInSpace.add("الأحمر")
                            tvAction.text = "نلاحظ أنه لا يتغير لون ورقة دوار الشمس الحمراء"

                            lifecycleScope.launch {
                                delay(3000)
                                reStart()
                            }

                        }else{
                            Toast.makeText(this, "اصنع تفاعل الكبريت أولاَ!!", Toast.LENGTH_SHORT).show()
                        }

                    }
                    "دوار الشمس الأزرق" -> {
                        if (componentsInSpace.contains("mix") && componentsInSpace.contains("كبريت")){
                            ivLitmuse.setImageResource(R.drawable.ic_blue_b)
                            componentsInSpace.add("الأزرق")
                            tvAction.text = "نلاحظ تغير لون ورقة دوار الشمس الزرقاء للأحمر"
                            lifecycleScope.launch {
                                delay(2000)
                                ivLitmuse.setImageResource(R.drawable.ic_red_to_blue)
                                delay(3000)
                                reStart()
                            }
                        }else{
                            Toast.makeText(this, "اصنع تفاعل الكبريت أولاَ!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                if (componentsInSpace.contains("كبريت")
                    && componentsInSpace.contains("لهب")){
                    lifecycleScope.launch {
                        delay(2000)
                        Glide.with(applicationContext).load(R.drawable.smoke).into(ivSmoke)
                        ivTube.setImageResource(R.drawable.ic_s_red)
                        componentsInSpace.add("mix")
                    }
                }

                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            else -> false
        }

    }

    private fun reStart(){
        llRestart.visibility = View.VISIBLE
    }

    private fun clearSpace(){
        llRestart.visibility = View.GONE
        ivArm.setImageResource(0)
        ivFire.setImageResource(0)
        ivLitmuse.setImageResource(0)
        ivSmoke.setImageResource(0)
        ivTube.setImageResource(0)
        componentsInSpace.clear()
    }
}