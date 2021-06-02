package com.example.formkotlinpj

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class ImageAdpater(private var dataSet: ArrayList<Uri>) :
    RecyclerView.Adapter<ImageAdpater.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivDynamicImage: ImageView = view.findViewById(R.id.ivDynamicImage)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_image, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Log.d("insideadapter", "onBindViewHolder:$position")

            viewHolder.ivDynamicImage.setImageURI(dataSet[position])
        //= dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = dataSet.size

    fun updateUI( dataSet: ArrayList<Uri>)
    {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)

        Log.d("updateUI", "updateUI: "+dataSet.size+" ")
        notifyDataSetChanged()
    }

}
