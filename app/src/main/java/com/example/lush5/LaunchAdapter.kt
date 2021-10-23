package com.example.lush5

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class LaunchAdapter(private val mLaunches: Array<Launch?>?):
    RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    val data = mLaunches

    // https://guides.codepath.com/android/Using-the-RecyclerView
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.launch_name)
        val dateTextView = itemView.findViewById<TextView>(R.id.launch_date)
        val successTextView = itemView.findViewById<TextView>(R.id.launch_success)
        val launchImage = itemView.findViewById<ImageView>(R.id.launch_image)
    }


    override fun getItemCount() =
        if (data != null) data.size else 0

    //@RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: LaunchAdapter.ViewHolder, position: Int) {
        if (data == null)
            return

        val item: Launch? = data.get(position)
        if (item != null) {
            holder.nameTextView.text = item.name
            holder.dateTextView.text = item.date_utc
            // todo - add to language resource files:
            holder.successTextView.text = if (item.success) "Mission Successful" else "Mission Unsuccessful"
            // todo:
            if ((item.links != null) && (item.links!!.patch != null) && (item.links!!.patch!!.small != null))
                Picasso.get().load(item.links?.patch?.small).into(holder.launchImage)
            //Picasso.get().load("https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png").into(holder.launchImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            //   .inflate(R.layout.text_item_view, parent, false) as TextView
            .inflate(R.layout.recycler_view_row, parent, false)
        return ViewHolder(view)
    }

}