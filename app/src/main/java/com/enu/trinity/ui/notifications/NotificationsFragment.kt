package com.enu.trinity.ui.notifications

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enu.trinity.databinding.FragmentNotificationsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.fab.setOnClickListener{
            FirebaseDatabase.getInstance()
                .reference
                .push()
                .setValue(ChatMessage(binding.input.text.toString(), FirebaseAuth.getInstance().currentUser?.displayName.toString(), 54545451254))
            binding.input.setText("")
        }

        displayChatMessage()
        return root
    }

    private lateinit var database: DatabaseReference
    private val TAG = "ChatMessage"
    fun displayChatMessage(){
        database = FirebaseDatabase.getInstance().reference
        database.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<ChatMessage>()
                Log.d(TAG, "Value is: " + value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}