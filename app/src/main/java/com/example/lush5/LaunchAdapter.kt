package com.example.lush5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

// https://guides.codepath.com/android/Using-the-RecyclerView

class LaunchAdapter(private val mLaunches: Array<Launch?>?):
    RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    // (Preferable to use data binding rather than findViewById as more efficient)
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.launch_name)
        val dateTextView = itemView.findViewById<TextView>(R.id.launch_date)
        val successTextView = itemView.findViewById<TextView>(R.id.launch_success)
        val launchImage = itemView.findViewById<ImageView>(R.id.launch_image)
    }


    override fun getItemCount() =
        if (this.mLaunches != null) this.mLaunches.size else 0

    override fun onBindViewHolder(holder: LaunchAdapter.ViewHolder, position: Int) {
        if (this.mLaunches == null)
            return
        // Load data into row of Recycler View
        val item: Launch? = this.mLaunches.get(position)
        if (item != null) {
            holder.nameTextView.text = item.name
            // todo - format date
            holder.dateTextView.text = item.date_utc
            // todo - add to language resource files:
            holder.successTextView.text = if (item.success) "Mission Successful" else "Mission Unsuccessful"
            // Image (could do some uri validation first)
            if ((item.links != null) && (item.links!!.patch != null) && (item.links!!.patch!!.small != null))
                Picasso.get().load(item.links?.patch?.small).into(holder.launchImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.recycler_view_row, parent, false)
        return ViewHolder(view)
    }

}