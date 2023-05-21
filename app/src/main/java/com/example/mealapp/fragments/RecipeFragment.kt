package com.example.mealapp.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.mealapp.MainActivity
import com.example.mealapp.R
import com.example.mealapp.databinding.FragmentRecipeBinding
import com.example.mealapp.viewmodel.MainViewModel
import java.util.concurrent.Executors

class RecipeFragment : Fragment(R.layout.fragment_recipe) {

    private var _binding : FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = (activity as MainActivity).mainViewModel
        // Getting Arguments from Meal Fragment
        val bundle = arguments
        val args = RecipeFragmentArgs.fromBundle(bundle!!)

        mainViewModel.updateTitle(args.meal.name)

        // Setting up view for Recipe Fragment
        setBackgroundImage(args.meal.imageUrl, binding.imgMealRecipe)
        var ingredientList : String = String()
        for (ingredients in args.meal.ingredients){
            ingredientList += ("-> ").plus(ingredients).plus("\n")
        }

        var steps : String = String()
        for (step in args.meal.steps){
            steps += "\n\n-> ".plus(step)
        }
        binding.apply {
            tvIngredientsList.text = ingredientList.trim()
            tvStepsList.text = steps.trim()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.recipe_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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