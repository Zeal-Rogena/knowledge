package com.example.knowledge

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.knowledge.databinding.ActivityMain4Binding
import com.google.firebase.database.FirebaseDatabase

class MainActivity4 : AppCompatActivity() {
        lateinit var binding: ActivityMain4Binding
        lateinit var quizModelList : MutableList<QuizModel>
        lateinit var adapter: QuizListAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMain4Binding.inflate(layoutInflater)
            setContentView(binding.root)

            quizModelList = mutableListOf()
            getDataFromFirebase()


        }

        private fun setupRecyclerView(){
            binding.progressBar.visibility = View.GONE
            adapter = QuizListAdapter(quizModelList)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adapter
        }

        private fun getDataFromFirebase() {
            binding.progressBar.visibility = View.VISIBLE
            FirebaseDatabase.getInstance().reference
                .get()
                .addOnSuccessListener { dataSnapshot ->
                    if (dataSnapshot.exists()) {
                        for (snapshot in dataSnapshot.children) {
                            val quizModel = snapshot.getValue(QuizModel::class.java)
                            if (quizModel != null) {
                                quizModelList.add(quizModel)
                            }
                        }
                    }
                    setupRecyclerView()
                }
        }
        }
