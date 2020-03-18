package com.example.rockpaperscissors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_game.view.*
import kotlinx.android.synthetic.main.game_item.view.*

class RPSAdapter(private val matches: List<RPS>) : RecyclerView.Adapter<RPSAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.game_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matches[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context: Context = itemView.context.applicationContext
        fun bind(match: RPS) {
            when (match.result) {
                RPS.Result.LOSS ->
                    itemView.tvWinner.text = context.getString(R.string.computerWin)
                RPS.Result.DRAW ->
                    itemView.tvWinner.text = context.getString(R.string.draw)
                RPS.Result.WIN ->
                    itemView.tvWinner.text = context.getString(R.string.playerWins)
            }
            itemView.tvPlayDate.text = match.date.toString()
            itemView.ivComputerHistory.setImageDrawable(context.getDrawable(match.computerChoice))
            itemView.ivPlayerHistory.setImageDrawable(context.getDrawable(match.playerChoice))
        }
    }
}