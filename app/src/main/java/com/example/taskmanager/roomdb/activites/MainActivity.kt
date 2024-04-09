package com.example.taskmanager.roomdb.activites

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.ViewGroup
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.databinding.ActivityMainBinding
import com.example.taskmanager.databinding.DialogBoxBinding
import com.example.taskmanager.roomdb.Room.UserInfo
import com.example.taskmanager.roomdb.recyclerView.OnItemClick
import com.example.taskmanager.roomdb.recyclerView.RVAdapter
import com.example.taskmanager.roomdb.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var vM: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vM = ViewModelProvider(this)[TodoViewModel::class.java]
        clickListener()
        setupUI()
    }

    fun String.toEditable(): Editable {
        return Editable.Factory.getInstance().newEditable(this)
    }

    private fun setupUI() {
        binding.RV.layoutManager = LinearLayoutManager(this)
        vM.getData().observe(this) {
            binding.RV.adapter = RVAdapter(it, object : OnItemClick{
                override fun onUpdate(userinfo: UserInfo) {
                    showAlertDialog(userinfo)
                }

                override fun onDelete(id: Int) {
                    vM.deleteItem(id)

                }


            })
        }
    }
    private fun clickListener() {
        binding.addTask.setOnClickListener {
            showAlertDialog(null)
        }
    }
    private fun showAlertDialog(userinfo: UserInfo?) {
        val dialog = Dialog(this)
        val dialogBinding = DialogBoxBinding.inflate(layoutInflater)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(false)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        dialog.show()
        if (userinfo != null) {
            dialogBinding.todoTitle.editText?.text = userinfo.title.toEditable()
            dialogBinding.todoDesc.editText?.text = userinfo.description.toEditable()
        }
        val title = dialogBinding.todoTitle.editText?.text
        val desc = dialogBinding.todoDesc.editText?.text
        dialogBinding.btnAdd.setOnClickListener {
            if (userinfo != null) {
                vM.updateData(UserInfo(userinfo.id, title.toString(), desc.toString()))
                dialog.dismiss()
            }else{
                vM.insertData(UserInfo(0, title.toString(), desc.toString()))
                dialog.dismiss()

            }


        }
        dialogBinding.cancelDialog.setOnClickListener {
            dialog.dismiss()
        }

    }


}

