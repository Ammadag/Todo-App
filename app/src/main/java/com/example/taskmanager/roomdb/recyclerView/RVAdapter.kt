package com.example.taskmanager.roomdb.recyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.ItemViewBinding
import com.example.taskmanager.roomdb.Room.UserInfo


class RVAdapter(val userIn: List<UserInfo?>, val listner: OnItemClick) : RecyclerView.Adapter<RVAdapter.viewHolder>() {
     inner class viewHolder(val binding: ItemViewBinding):RecyclerView.ViewHolder(binding.root)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
           val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context),
               parent,false)
          return viewHolder(binding)
     }

     override fun getItemCount(): Int {
          return userIn.size
     }

     override fun onBindViewHolder(holder: viewHolder, position: Int) {

          val data = userIn[position]
          holder.binding.tvTitle.text = data?.title
          holder.binding.tvDes.text = data?.description
          holder.binding.root.setOnClickListener{
               listner.onUpdate(data!!)

          }
          holder.binding.btnDel.setOnClickListener {
               listner.onDelete(data!!.id ?:0)
          }
     }
//     fun getList(): List<UserInfo>{
//          return listOf(
//               UserInfo(0,"lorem","loremss"),
//               UserInfo(1,"lorem","loremss"),
//               UserInfo(2,"lorem","loremss"),
//               UserInfo(3,"lorem","loremss"),
//               UserInfo(4,"lorem","loremss"),
//               UserInfo(5,"lorem","loremss"),
//               UserInfo(6,"lorem","loremss"),
//               UserInfo(7,"lorem","loremss"),
//               UserInfo(8,"lorem","loremss"),
//          )
     }


