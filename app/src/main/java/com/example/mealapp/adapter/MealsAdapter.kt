package com.example.mealapp.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapp.R
import com.example.mealapp.databinding.MealLayoutBinding
import com.example.mealapp.model.MealModel
import com.example.mealapp.viewmodel.MainViewModel
import java.util.concurrent.Executors
import android.os.Handler
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.findNavController
import com.example.mealapp.fragments.MealsFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsAdapter(private val updatedMealList : ArrayList<MealModel>, private val mainViewModel: MainViewModel)
    : RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {

        class MealsViewHolder(val itemBinding : MealLayoutBinding)
            :RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        return MealsViewHolder(
            MealLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return updatedMealList.size
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.itemBinding.apply {
//            val imgId = mealsImage[updatedMealList[position].id]
//            backgroundImage.setBackgroundResource(R.drawable.ic_launcher_background)

            CoroutineScope(Dispatchers.IO).launch {
                val imageURL = updatedMealList[position].imageUrl
                setBackgroundImage(imageURL, imageView)
            }

            tvMealTitle.text = updatedMealList[position].name
            tvTime.text = updatedMealList[position].duration.toString().plus("min")
            tvComplexity.text = updatedMealList[position].complexity
            tvAffordability.text = updatedMealList[position].affordability
        }

        holder.itemView.setOnClickListener {
            mainViewModel.updateTitle(updatedMealList[position].name)
            val direction = MealsFragmentDirections
                .actionMealsFragmentToRecipeFragment(updatedMealList[position])
            it.findNavController().navigate(direction)
        }
    }


    private fun setBackgroundImage(imageURL : String, imageView : ImageView){
        // Declaring executor to parse the URL
        val executor = Executors.newSingleThreadExecutor()

        // Once the executor parses the URL
        // and receives the image, handler will load it
        // in the ImageView
        val handler = android.os.Handler(Looper.getMainLooper())

        // Initializing the image
        var image: Bitmap? = null

        // Only for Background process (can take time depending on the Internet speed)
        executor.execute {

            // Tries to get the image and post it in the ImageView
            // with the help of Handler
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)

                // Only for making changes in UI
                handler.post {
                    imageView.setImageBitmap(image)
                }
            }
            // If the URL does not point to
            // image or any other kind of failure
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}