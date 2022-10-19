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

class Lab4Activity : AppCompatActivity() {


    private lateinit var rvModel : RecyclerView
    private lateinit var constraintLab : ConstraintLayout
    private lateinit var llRestart : LinearLayout
    private lateinit var btnRestart : Button
    private lateinit var btnBack : ImageView
    private lateinit var ivArm : ImageView
    private lateinit var ivFire : ImageView
    private lateinit var ivFlash : ImageView
    private lateinit var ivTube : ImageView
    private lateinit var componentsInSpace : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab4)
        rvModel = findViewById(R.id.rv_models)
        constraintLab = findViewById(R.id.constrain_lab_4)
        ivArm = findViewById(R.id.iv_arm)
        ivFire = findViewById(R.id.iv_fire)
        ivFlash = findViewById(R.id.iv_flash_4)
        ivTube = findViewById(R.id.iv_test_tube)
        llRestart = findViewById(R.id.ll_restart)
        btnRestart = findViewById(R.id.btn_restart)
        btnBack = findViewById(R.id.iv_back_btn)
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
        models.add(Model(4,"مسحوف كبريت", R.drawable.sulfur))
        models.add(Model(5,"برادة الخارصين", R.drawable.ic_spoon_black))


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
                            if (componentsInSpace.contains("زنك")){
                                ivTube.setImageResource(R.drawable.ic_z_s_tube)
                                componentsInSpace.add("mix")
                            }
                        }else{
                            Toast.makeText(this, "ضع أنبوب!!", Toast.LENGTH_SHORT).show()
                        }

                    }
                    "زنك" -> {
                        if (componentsInSpace.contains("أنبوب")){
                            ivTube.setImageResource(R.drawable.ic_z__tube)
                            componentsInSpace.add("زنك")
                            if (componentsInSpace.contains("كبريت")){
                                ivTube.setImageResource(R.drawable.ic_z_s_tube)
                                componentsInSpace.add("mix")
                            }
                        }else{
                            Toast.makeText(this, "ضع أنبوب!!", Toast.LENGTH_SHORT).show()
                        }

                    }

                }

                if (componentsInSpace.contains("mix")
                    && componentsInSpace.contains("لهب")){
                    lifecycleScope.launch {
                        delay(2000)
                        Glide.with(applicationContext).load(R.drawable.flash).into(ivFlash)
                        delay(4000)
                        ivFlash.setImageResource(0)
                        ivTube.setImageResource(R.drawable.ic_finesh_tube)
                        delay(2000)
                        reStart()
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
        ivFlash.setImageResource(0)
        ivTube.setImageResource(0)
        componentsInSpace.clear()
    }
}