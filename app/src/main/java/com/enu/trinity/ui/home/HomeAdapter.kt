package com.enu.trinity.ui.home

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class HomeAdapter(private val list: List<HomeItems>, var context: Context): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder (ItemView: View):RecyclerView.ViewHolder(ItemView){
        val name: TextView = itemView.findViewById(R.id.name)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val text: TextView = itemView.findViewById(R.id.textView)
        val price:TextView = itemView.findViewById(R.id.priceTV)
        val btnZakaz:Button = itemView.findViewById(R.id.btnZakaz)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = list[position]
        holder.name.text = items.name
        holder.text.text = items.text
        holder.price.text = items.price
        items.image?.let { holder.imageView.setImageResource(it)
        holder.btnZakaz.setOnClickListener(View.OnClickListener {
            val mAuth = FirebaseAuth.getInstance();
            val currentUser: FirebaseUser? =  mAuth.getCurrentUser()
            val userName: String? = currentUser?.displayName
            val db = FirebaseFirestore.getInstance()
            val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault())
            val currentDateandTime: String = sdf.format(Date())

            val dbConference = db.collection("Trinity")
            val data = ZakazItems(items.name, userName.toString(), currentDateandTime)
            dbConference.add(data).addOnSuccessListener {
                Toast.makeText(context, "Заказ принят!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { e ->
                Toast.makeText(context,"Fail to add course \n$e", Toast.LENGTH_SHORT).show()
            }

        })}
    }

    override fun getItemCount(): Int {
        return list.size
    }
}