package com.example.mmt.labs

import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
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

class MainActivity : AppCompatActivity() {

    private lateinit var rvModel : RecyclerView
    private lateinit var constraintLab : ConstraintLayout
    private lateinit var llRestart : LinearLayout
    private lateinit var btnRestart : Button
    private lateinit var btnBack : ImageView
    private lateinit var ivArm : ImageView
    private lateinit var ivFire : ImageView
    private lateinit var ivFlash : ImageView
    private lateinit var ivMaterial : ImageView
    private lateinit var componentsInSpace : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvModel = findViewById(R.id.rv_models)
        constraintLab = findViewById(R.id.constrain_lab)
        ivArm = findViewById(R.id.iv_arm)
        ivFire = findViewById(R.id.iv_fire)
        ivFlash = findViewById(R.id.iv_flash)
        ivMaterial = findViewById(R.id.iv_material)
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

        ivArm.setOnLongClickListener {
            ivArm.setImageResource(0)
            componentsInSpace.remove("zinc")
            componentsInSpace.remove("mixed")
            true
        }
        ivFire.setOnLongClickListener {
            ivFire.setImageResource(0)
            componentsInSpace.remove("fire")
            true
        }
      //  Glide.with(applicationContext).load(R.drawable.boom).into(ivFlash)
    }

    private fun fillNewsRecycle() {
        val newsAdapter = ModelAdapter()
        val models =ArrayList<Model>()
        models.add(Model(1,"الحامل", R.drawable.ic_tripod_empty))
        models.add(Model(2,"لهب بنزن", R.drawable.fire))
        models.add(Model(4,"الشبكة", R.drawable.ic_mesh))
        models.add(Model(3,"زنك", R.drawable.ic_spoon_black))
        models.add(Model(3,"كبريت", R.drawable.sulfur))


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
                            ivArm.setImageResource(R.drawable.ic_tripod_empty)
                            componentsInSpace.add("tripod")
                        }
                        "الشبكة" -> {
                            if (componentsInSpace.contains("tripod")){
                            ivArm.setImageResource(R.drawable.ic_tripod_and_mesh)
                            componentsInSpace.add("mesh")
                            }else{
                                Toast.makeText(this, "أولاً ضع الحامل!!",Toast.LENGTH_SHORT).show()
                            }
                        }
                        "لهب بنزن" -> {
                            Glide.with(this).load(R.drawable.fire).into(ivFire)
                            componentsInSpace.add("fire")
                        }

                        "زنك" -> {
                            if (componentsInSpace.contains("mesh")){
                                ivMaterial.setImageResource(R.drawable.ic_material_black)
                                componentsInSpace.add("zinc")
                            }else{
                                Toast.makeText(this, "ضع الشبكة!!",Toast.LENGTH_SHORT).show()
                            }

                            if(componentsInSpace.contains("mesh")
                                && componentsInSpace.contains("sulfur")){
                                ivMaterial.setImageResource(R.drawable.ic_material_gray)
                                componentsInSpace.add("mix")
                            }

                        }
                        "كبريت" -> {
                            if (componentsInSpace.contains("mesh")){
                                ivMaterial.setImageResource(R.drawable.ic_material_white)
                                componentsInSpace.add("sulfur")
                            }else{
                                Toast.makeText(this, "ضع الشبكة!!",Toast.LENGTH_SHORT).show()
                            }

                            if(componentsInSpace.contains("mesh")
                                && componentsInSpace.contains("zinc")){
                                ivMaterial.setImageResource(R.drawable.ic_material_gray)
                                componentsInSpace.add("mix")
                            }
                        }
                    }

                        if(componentsInSpace.contains("mix") &&
                            componentsInSpace.contains("fire")){
                                lifecycleScope.launch {
                                    delay(2000)
                                    Glide.with(applicationContext).load(R.drawable.boom).into(ivFlash)

                                    delay(1500)
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
        ivMaterial.setImageResource(0)
        ivFlash.setImageResource(0)
        componentsInSpace.clear()
    }
}