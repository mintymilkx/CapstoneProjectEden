package com.example.project.capstone.eden.ui.donasi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.capstone.eden.R
import com.example.project.capstone.eden.databinding.FragmentDonasiBinding
import com.google.firebase.firestore.*

class DonasiFragment : Fragment(), DonasiAdapter.OnDonasiClickListener {
    private lateinit var binding: FragmentDonasiBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var donasiArrayList: ArrayList<DonasiModel>
    private lateinit var donasiAdapter: DonasiAdapter
    private lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDonasiBinding.inflate(inflater, container, false)

        recyclerView = binding.rvDonasi

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }

        donasiArrayList = arrayListOf()
        //menambah context
        donasiAdapter = DonasiAdapter(requireActivity(), donasiArrayList, this)

        recyclerView.adapter = donasiAdapter

        fetchDataDonations()

        // Inflate the layout for this fragment
        return binding.root

    }

    //function to fetch the data from firestore
    private fun fetchDataDonations(){
        db = FirebaseFirestore.getInstance()
        db.collection("donations")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?

                ) {
                    if (error != null){
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for (doc: DocumentChange in value?.documentChanges!!){
                        if(doc.type == DocumentChange.Type.ADDED){
                            donasiArrayList.add(doc.document.toObject(DonasiModel::class.java))
                        }
                    }
                    donasiAdapter.notifyDataSetChanged()
                }
            })
    }

    override fun onDonasiItemClicked(position: Int) {
        val intent = Intent(requireContext(), DetailDonasiActivity::class.java)
        intent.putExtra("name", donasiArrayList[position].title)
        intent.putExtra("amount", donasiArrayList[position].amount)
        intent.putExtra("image", donasiArrayList[position].photoUrl)
        intent.putExtra("organization", donasiArrayList[position].organizationName).toString()
        startActivity(intent)
    }

}