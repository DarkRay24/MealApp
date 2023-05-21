package com.example.mealapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mealapp.MainActivity
import com.example.mealapp.R
import com.example.mealapp.adapter.MealsAdapter
import com.example.mealapp.databinding.FragmentMealsBinding
import com.example.mealapp.model.MealModel
import com.example.mealapp.viewmodel.MainViewModel


class MealsFragment : Fragment(R.layout.fragment_meals) {

    private var _binding: FragmentMealsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mealsAdapter: MealsAdapter
    private lateinit var mealList: ArrayList<MealModel>

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).mainViewModel

        mealList = createMealList()
        val bundle = arguments
        val args = MealsFragmentArgs.fromBundle(bundle!!)

        mainViewModel.updateTitle(args.category.name)
        setUpMealsRecyclerView(args.category.id)
    }

    private fun setUpMealsRecyclerView(id: Int) {
        val updatedMealList = updateMealList(id)
        mealsAdapter = MealsAdapter(updatedMealList, mainViewModel)

        binding.rvMeals.apply {
            layoutManager = StaggeredGridLayoutManager(
                1,
                StaggeredGridLayoutManager.VERTICAL,
            )
            setHasFixedSize(true)
            adapter = mealsAdapter
        }
    }

    private fun updateMealList(id: Int): ArrayList<MealModel> {
        val updatedMealList: ArrayList<MealModel> = ArrayList()
        for (item in mealList) {
            for (categoryId in item.categories) {
                if (categoryId == id &&
                    (checkFilter(item.isGlutenFree, mainViewModel.isGlutenFree.value!!)) &&
                    (checkFilter(item.isLactoseFree, mainViewModel.isLactoseFree.value!!)) &&
                    (checkFilter(item.isVegetarian, mainViewModel.isVegetarian.value!!)) &&
                    (checkFilter(item.isVegan, mainViewModel.isVegan.value!!))
                ) {
                    updatedMealList.add(item)
                    break;
                }
            }
        }
        return updatedMealList
    }

    private fun checkFilter(itemValue : Boolean, value : Boolean): Boolean {
        return if (value)
            itemValue
        else true
    }


        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }


        private fun createMealList(): ArrayList<MealModel> {
            val mealList: ArrayList<MealModel> = ArrayList()

            val meal1 = MealModel(
                1,
                "Spaghetti with Tomato Sauce",
                listOf(1, 2, 5, 6, 7),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Spaghetti" +
                        "_Bolognese_mit_Parmesan_oder_Grana_Padano.jpg/800px-Spaghetti_Bolognese" +
                        "_mit_Parmesan_oder_Grana_Padano.jpg",
                listOf(
                    "4 Tomatoes",
                    "1 Tablespoon of Olive Oil",
                    "1 Onion",
                    "250g Spaghetti",
                    "Spices",
                    "Cheese (optional)"
                ),
                listOf(
                    "Cut the tomatoes and the onion into small pieces.",
                    "Boil some water - add salt to it once it boils.",
                    "Put the spaghetti into the boiling water - they should be done in about 10 to 12 minutes.",
                    "In the meantime, heaten up some olive oil and add the cut onion.",
                    "After 2 minutes, add the tomato pieces, salt, pepper and your other spices.",
                    "The sauce will be done once the spaghetti are.",
                    "Feel free to add some cheese on top of the finished dish."
                ),
                20,
                "Simple",
                "Affordable",
                false,
                true,
                true,
                true
            )

            val meal2 = MealModel(
                2,
                "Toast Hawaii",
                listOf(2, 3, 6, 7, 8),
                "https://cdn.pixabay.com/photo/2018/07/11/21/51/toast-3532016_1280.jpg",
                listOf(
                    "1 Slice White Bread",
                    "1 Slice Ham",
                    "1 Slice Pineapple",
                    "1-2 Slices of Cheese",
                    "Butter"
                ),
                listOf(
                    "Butter one side of the white bread",
                    "Layer ham, the pineapple and cheese on the white bread",
                    "Bake the toast for round about 10 minutes in the oven at 200°C"
                ),
                10,
                "Simple",
                "Pricey",
                false,
                false,
                false,
                false
            )

            val meal3 = MealModel(
                3,
                "Classic Hamburger",
                listOf(3, 10),
                "https://cdn.pixabay.com/photo/2014/10/23/18/05/burger-500054_1280.jpg",
                listOf(
                    "300g Cattle Hack",
                    "1 Tomato",
                    "1 Cucumber",
                    "1 Onion",
                    "Ketchup",
                    "2 Burger Buns"
                ),
                listOf(
                    "Form 2 patties",
                    "Fry the patties for c. 4 minutes on each side",
                    "Quickly fry the buns for c. 1 minute on each side",
                    "Bruch buns with ketchup",
                    "Serve burger with tomato, cucumber and onion"
                ),
                45,
                "Simple",
                "Pricey",
                false,
                true,
                false,
                false
            )

            val meal4 = MealModel(
                4,
                "Wiener Schnitzel",
                listOf(4, 6),
                "https://cdn.pixabay.com/photo/2018/03/31/19/29/schnitzel-3279045_1280.jpg",
                listOf(
                    "8 Veal Cutlets",
                    "4 Eggs",
                    "200g Bread Crumbs",
                    "100g Flour",
                    "300ml Butter",
                    "100g Vegetable Oil",
                    "Salt",
                    "Lemon Slices"
                ),
                listOf(
                    "Tenderize the veal to about 2–4mm, and salt on both sides.",
                    "On a flat plate, stir the eggs briefly with a fork.",
                    "Lightly coat the cutlets in flour then dip into the egg, and finally, coat in breadcrumbs.",
                    "Heat the butter and oil in a large pan (allow the fat to get very hot) and fry the schnitzels until golden brown on both sides.",
                    "Make sure to toss the pan regularly so that the schnitzels are surrounded by oil and the crumbing becomes ‘fluffy’.",
                    "Remove, and drain on kitchen paper. Fry the parsley in the remaining oil and drain.",
                    "Place the schnitzels on a warmed plate and serve garnished with parsley and slices of lemon."
                ),
                60,
                "Challenging",
                "Luxurious",
                false,
                false,
                false,
                false
            )

            val meal5 = MealModel(
                5,
                "Salad with Smoked Salmon",
                listOf(6, 7, 10),
                "https://cdn.pixabay.com/photo/2016/10/25/13/29/smoked-salmon-salad-1768890_1280.jpg",
                listOf(
                    "Arugula",
                    "Lamb\'s Lettuce",
                    "Parsley",
                    "Fennel",
                    "200g Smoked Salmon",
                    "Mustard",
                    "Balsamic Vinegar",
                    "Olive Oil",
                    "Salt and Pepper"
                ),
                listOf(
                    "Wash and cut salad and herbs",
                    "Dice the salmon",
                    "Process mustard, vinegar and olive oil into a dessing",
                    "Prepare the salad",
                    "Add salmon cubes and dressing"
                ),
                15,
                "Simple",
                "Luxurious",
                true,
                true,
                false,
                false
            )

            val meal6 = MealModel(
                6,
                "Delicious Orange Mousse",
                listOf(6, 9, 10),
                "https://cdn.pixabay.com/photo/2017/05/01/05/18/pastry-2274750_1280.jpg",
                listOf(
                    "4 Sheets of Gelatine",
                    "150ml Orange Juice",
                    "80g Sugar",
                    "300g Yoghurt",
                    "200g Cream",
                    "Orange Peel",
                ),
                listOf(
                    "Dissolve gelatine in pot",
                    "Add orange juice and sugar",
                    "Take pot off the stove",
                    "Add 2 tablespoons of yoghurt",
                    "Stir gelatin under remaining yoghurt",
                    "Cool everything down in the refrigerator",
                    "Whip the cream and lift it under die orange mass",
                    "Cool down again for at least 4 hours",
                    "Serve with orange peel",
                ),
                240,
                "Hard",
                "Affordable",
                true,
                false,
                false,
                true
            )

            val meal7 = MealModel(
                7,
                "Pancakes",
                listOf(2, 7, 8),
                "https://cdn.pixabay.com/photo/2018/07/10/21/23/pancake-3529653_1280.jpg",
                listOf(
                    "1 1/2 Cups all-purpose Flour",
                    "3 1/2 Teaspoons Baking Powder",
                    "1 Teaspoon Salt",
                    "1 Tablespoon White Sugar",
                    "1 1/4 cups Milk",
                    "1 Egg",
                    "3 Tablespoons Butter, melted",
                ),
                listOf(
                    "In a large bowl, sift together the flour, baking powder, salt and sugar.",
                    "Make a well in the center and pour in the milk, egg and melted butter; mix until smooth.",
                    "Heat a lightly oiled griddle or frying pan over medium high heat.",
                    "Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake. Brown on both sides and serve hot"
                ),
                20,
                "Simple",
                "Affordable",
                true,
                false,
                false,
                true
            )

            val meal8 = MealModel(
                8,
                "Creamy Indian Chicken Curry",
                listOf(6, 8),
                "https://cdn.pixabay.com/photo/2018/06/18/16/05/indian-food-3482749_1280.jpg",
                listOf(
                    "4 Chicken Breasts",
                    "1 Onion",
                    "2 Cloves of Garlic",
                    "1 Piece of Ginger",
                    "4 Tablespoons Almonds",
                    "1 Teaspoon Cayenne Pepper",
                    "500ml Coconut Milk",
                ),
                listOf(
                    "Slice and fry the chicken breast",
                    "Process onion, garlic and ginger into paste and sauté everything",
                    "Add spices and stir fry",
                    "Add chicken breast + 250ml of water and cook everything for 10 minutes",
                    "Add coconut milk",
                    "Serve with rice"
                ),
                45,
                "Challenging",
                "Pricey",
                true,
                true,
                false,
                false
            )

            val meal9 = MealModel(
                9,
                "Chocolate Souffle",
                listOf(5, 6, 9),
                "https://cdn.pixabay.com/photo/2014/08/07/21/07/souffle-412785_1280.jpg",
                listOf(
                    "1 Teaspoon melted Butter",
                    "2 Tablespoons white Sugar",
                    "2 Ounces 70% dark Chocolate, broken into pieces",
                    "1 Tablespoon Butter",
                    "1 Tablespoon all-purpose Flour",
                    "4 1/3 tablespoons cold Milk",
                    "1 Pinch Salt",
                    "1 Pinch Cayenne Pepper",
                    "1 Large Egg Yolk",
                    "2 Large Egg Whites",
                    "1 Pinch Cream of Tartar",
                    "1 Tablespoon white Sugar",
                ),
                listOf(
                    "Preheat oven to 190°C. Line a rimmed baking sheet with parchment paper.",
                    "Brush bottom and sides of 2 ramekins lightly with 1 teaspoon melted butter; cover bottom and sides right up to the rim.",
                    "Add 1 tablespoon white sugar to ramekins. Rotate ramekins until sugar coats all surfaces.",
                    "Place chocolate pieces in a metal mixing bowl.",
                    "Place bowl over a pan of about 3 cups hot water over low heat.",
                    "Melt 1 tablespoon butter in a skillet over medium heat. Sprinkle in flour. Whisk until flour is incorporated into butter and mixture thickens.",
                    "Whisk in cold milk until mixture becomes smooth and thickens. Transfer mixture to bowl with melted chocolate.",
                    "Add salt and cayenne pepper. Mix together thoroughly. Add egg yolk and mix to combine.",
                    "Leave bowl above the hot (not simmering) water to keep chocolate warm while you whip the egg whites.",
                    "Place 2 egg whites in a mixing bowl; add cream of tartar. Whisk until mixture begins to thicken and a drizzle from the whisk stays on the surface about 1 second before disappearing into the mix.",
                    "Add 1/3 of sugar and whisk in. Whisk in a bit more sugar about 15 seconds.",
                    "whisk in the rest of the sugar. Continue whisking until mixture is about as thick as shaving cream and holds soft peaks, 3 to 5 minutes.",
                    "Transfer a little less than half of egg whites to chocolate.",
                    "Mix until egg whites are thoroughly incorporated into the chocolate.",
                    "Add the rest of the egg whites; gently fold into the chocolate with a spatula, lifting from the bottom and folding over.",
                    "Stop mixing after the egg white disappears. Divide mixture between 2 prepared ramekins. Place ramekins on prepared baking sheet.",
                    "Bake in preheated oven until scuffles are puffed and have risen above the top of the rims, 12 to 15 minutes.",
                ),
                60,
                "Hard",
                "Affordable",
                true,
                false,
                false,
                true
            )

            val meal10 = MealModel(
                10,
                "Asparagus Salad with Cherry Tomatoes",
                listOf(2, 5, 6, 10),
                "https://cdn.pixabay.com/photo/2018/04/09/18/26/asparagus-3304997_1280.jpg",
                listOf(
                    "White and Green Asparagus",
                    "30g Pine Nuts",
                    "300g Cherry Tomatoes",
                    "Salad",
                    "Salt, Pepper and Olive Oil",
                ),
                listOf(
                    "Wash, peel and cut the asparagus",
                    "Cook in salted water",
                    "Salt and pepper the asparagus",
                    "Roast the pine nuts",
                    "Halve the tomatoes",
                    "Mix with asparagus, salad and dressing",
                    "Serve with Baguette",
                ),
                30,
                "Simple",
                "Luxurious",
                true,
                true,
                true,
                true
            )


            mealList.add(meal1); mealList.add(meal2); mealList.add(meal3); mealList.add(meal4);
            mealList.add(meal5); mealList.add(meal6); mealList.add(meal7); mealList.add(meal8);
            mealList.add(meal9); mealList.add(meal10);

            return mealList
        }

    }