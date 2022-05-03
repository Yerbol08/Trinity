package com.enu.trinity.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.enu.trinity.R
import com.enu.trinity.ui.home.HomeItems
import com.enu.trinity.ui.home.ZakazItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class TableAdapter (private val list: List<TableItems>, var context: Context): RecyclerView.Adapter<TableAdapter.ViewHolder>() {

        class ViewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView){

            val imageView: ImageView = itemView.findViewById(R.id.imageviewTable)
            val number: TextView = itemView.findViewById(R.id.textviewNumber)
            val btnBron: Button = itemView.findViewById(R.id.ButtonBron)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.table_layout, parent, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val items = list[position]
            holder.number.text = items.number
            items.image?.let { holder.imageView.setImageResource(it)
                holder.btnBron.setOnClickListener(View.OnClickListener {
                    val mAuth = FirebaseAuth.getInstance();
                    val currentUser: FirebaseUser? =  mAuth.getCurrentUser()
                    val userName: String? = currentUser?.displayName
                    val db = FirebaseFirestore.getInstance()
                    val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault())
                    val currentDateandTime: String = sdf.format(Date())

                    val dbConference = db.collection("Table")
                    val data = ZakazItems(items.number, userName.toString(), currentDateandTime)
                    dbConference.add(data).addOnSuccessListener {
                        Toast.makeText(context, "Стол забронирован!", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener { e ->
                        Toast.makeText(context,"Fail to add course \n$e", Toast.LENGTH_SHORT).show()
                    }

                })}
        }

        override fun getItemCount(): Int {
            return list.size
        }
}