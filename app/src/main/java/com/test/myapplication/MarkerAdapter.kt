package com.test.myapplication
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.Marker


class MarkerAdapter(private val markers: List<Marker>) : RecyclerView.Adapter<MarkerAdapter.MarkerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.marker_list_item, parent, false)
        return MarkerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        val marker = markers[position]
        holder.titleTextView.text = marker.title
        holder.snippetTextView.text = marker.snippet
    }

    override fun getItemCount(): Int = markers.size

    class MarkerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.marker_title)
        val snippetTextView: TextView = itemView.findViewById(R.id.marker_snippet)
    }
}