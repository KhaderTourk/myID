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
import com.example.mmt.ExperimentsActivity
import com.example.mmt.R
import com.example.mmt.adapter.ModelAdapter
import com.example.mmt.model.Model
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Lab2Activity : AppCompatActivity() {

    private lateinit var rvModel: RecyclerView
    private lateinit var constraintLab: ConstraintLayout
    private lateinit var llRestart: LinearLayout
    private lateinit var btnRestart: Button
    private lateinit var btnBack: ImageView
    private lateinit var ivN: ImageView
    private lateinit var tvAction : TextView
    private lateinit var componentsInSpace: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab2)
        rvModel = findViewById(R.id.rv_models)
        constraintLab = findViewById(R.id.constrain_lab_2)
        ivN = findViewById(R.id.iv_n)
        llRestart = findViewById(R.id.ll_restart)
        btnRestart = findViewById(R.id.btn_restart)
        btnBack = findViewById(R.id.iv_back_btn)
        tvAction = findViewById(R.id.action_2)

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
        val models = ArrayList<Model>()
        models.add(Model(1, "وعاء", R.drawable.ic_n))
        models.add(Model(2, "ماء", R.drawable.ic_water))
        models.add(Model(4, "كالسيوم", R.drawable.ic_calcium))
        models.add(Model(3, "دوار الشمس الأحمر", R.drawable.ic_red_b))
        models.add(Model(3, "دوار الشمس الأزرق", R.drawable.ic_blue_b))


        newsAdapter.setData(models)
        rvModel.adapter = newsAdapter
        rvModel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private val dragListener = View.OnDragListener { view, event ->
        when (event.action) {
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
                when (item.text) {
                    "وعاء" -> {
                        ivN.setImageResource(R.drawable.ic_n)
                        componentsInSpace.add("وعاء")
                    }
                    "ماء" -> {
                        if (componentsInSpace.contains("كالسيوم")) {
                            ivN.setImageResource(R.drawable.ic_n_w_c)
                            componentsInSpace.add("ماء")
                        } else if (componentsInSpace.contains("وعاء")&& !componentsInSpace.contains("كالسيوم")) {
                            ivN.setImageResource(R.drawable.ic_n_w_w)
                            componentsInSpace.add("ماء")
                        } else {
                            Toast.makeText(this, "أولاً ضع الوعاء!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    "كالسيوم" -> {
                        if (componentsInSpace.contains("وعاء") && componentsInSpace.contains("ماء")) {
                            ivN.setImageResource(R.drawable.ic_n_w_c)
                            componentsInSpace.add("كالسيوم")
                        } else if (componentsInSpace.contains("وعاء")&& !componentsInSpace.contains("ماء")) {
                            ivN.setImageResource(R.drawable.ic_n_w_c_e)
                            componentsInSpace.add("كالسيوم")
                        } else {
                            Toast.makeText(this, "أولاً ضع الوعاء!!", Toast.LENGTH_SHORT).show()
                        }


                    }

                    "دوار الشمس الأحمر" -> {
                        if (componentsInSpace.contains("وعاء")
                            && componentsInSpace.contains("ماء")
                            && componentsInSpace.contains("كالسيوم"))
                        {
                            ivN.setImageResource(R.drawable.ic_red_i_n)
                            componentsInSpace.add("الأحمر")
                            tvAction.text = "نلاحظ تغير لون ورقة دوار الشمس الحمراء للأزرق"
                            componentsInSpace.add("mix")

                        } else if (componentsInSpace.contains("وعاء")
                            && componentsInSpace.contains("ماء"))
                        {
                            Toast.makeText(this, "إن لم تضع الكالسيوم لن يحدث شئ!!", Toast.LENGTH_SHORT).show()
                        }else if (componentsInSpace.contains("وعاء")
                            && componentsInSpace.contains("كالسيوم"))
                        {
                            Toast.makeText(this, "لم تضع الماء!!", Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this, "أولاً ضع الوعاء!!", Toast.LENGTH_SHORT).show()
                        }

                    }
                    "دوار الشمس الأزرق" -> {
                        if (componentsInSpace.contains("وعاء")
                            && componentsInSpace.contains("ماء")
                            && componentsInSpace.contains("كالسيوم"))
                        {
                            ivN.setImageResource(R.drawable.ic_blue_i_n)
                            componentsInSpace.add("الأزرق")
                            tvAction.text = "نلاحظ أنه لا يتغير لون ورقة دوار الشمس الزرقاء"
                            componentsInSpace.add("mix")

                        } else if (componentsInSpace.contains("وعاء")
                            && componentsInSpace.contains("ماء"))
                        {
                            Toast.makeText(this, "إن لم تضع الكالسيوم لن يحدث شئ!!", Toast.LENGTH_SHORT).show()
                        }else if (componentsInSpace.contains("وعاء")
                            && componentsInSpace.contains("كالسيوم"))
                        {
                            Toast.makeText(this, "لم تضع الماء!!", Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this, "أولاً ضع الوعاء!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                if (componentsInSpace.contains("mix")
                ) {
                    lifecycleScope.launch {
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

    private fun reStart() {
        llRestart.visibility = View.VISIBLE
    }

    private fun clearSpace() {
        llRestart.visibility = View.GONE
        ivN.setImageResource(0)
        componentsInSpace.clear()
    }
}