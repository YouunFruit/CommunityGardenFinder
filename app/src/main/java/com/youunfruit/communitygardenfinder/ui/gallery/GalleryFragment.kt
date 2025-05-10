package com.youunfruit.communitygardenfinder.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.youunfruit.communitygardenfinder.GardenInfo
import com.youunfruit.communitygardenfinder.R
import com.youunfruit.communitygardenfinder.databinding.FragmentGalleryBinding
import com.youunfruit.communitygardenfinder.network.RetrofitInstance
import com.youunfruit.communitygardenfinder.objects.Garden

class GalleryFragment : Fragment() {


    private lateinit var gardenAdapter: GardenAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.joinedGardenList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize Adapter and Set Data
        gardenAdapter = GardenAdapter { garden ->
            onGardenClicked(garden)
        }
        recyclerView.adapter = gardenAdapter

        // Load data (you can replace this with real data fetching logic)
        loadGardens()

        return view
    }

    private suspend fun loadGardens() {
        // Dummy data for demonstration
        val gardens = RetrofitInstance.api.getJoinedGardens()
        gardenAdapter.submitList(gardens)
    }

    private fun onGardenClicked(garden: Garden) {
        Toast.makeText(requireContext(), "Clicked: ${garden.name}", Toast.LENGTH_SHORT).show()

        // Navigate to another activity
        val intent = Intent(requireContext(), GardenInfo::class.java)
        intent.putExtra("GARDEN_NAME", garden.name)
        startActivity(intent)
    }
}