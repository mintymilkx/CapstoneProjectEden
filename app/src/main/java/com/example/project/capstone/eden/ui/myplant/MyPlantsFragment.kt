package com.example.project.capstone.eden.ui.myplant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.capstone.eden.R
import com.example.project.capstone.eden.databinding.FragmentDonasiBinding
import com.example.project.capstone.eden.databinding.FragmentMyPlantsBinding
import com.example.project.capstone.eden.ui.donasi.DonasiAdapter
import com.example.project.capstone.eden.ui.donasi.DonasiModel
import com.google.firebase.firestore.*

class MyPlantsFragment : Fragment() {
    private lateinit var binding: FragmentMyPlantsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var plantArrayList: ArrayList<PlantsModel>
    private lateinit var plantAdapter: PlantAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMyPlantsBinding.inflate(inflater, container, false)

        recyclerView = binding.rvListPlantsInMyPlants

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }

        plantArrayList = arrayListOf()

        plantAdapter = PlantAdapter(requireActivity(), plantArrayList)

        recyclerView.adapter = plantAdapter

        fetchDataMyPlants()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun fetchDataMyPlants(){
        db = FirebaseFirestore.getInstance()
        db.collection("myPlants")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?

                ) {
                    if (error != null){
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for (doc: DocumentChange in value?.documentChanges!!){
                        if(doc.type == DocumentChange.Type.ADDED){
                            plantArrayList.add(doc.document.toObject(PlantsModel::class.java))
                        }
                    }
                    plantAdapter.notifyDataSetChanged()
                }
            })
    }

}