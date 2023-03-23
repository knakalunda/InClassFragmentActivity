package edu.temple.inclassactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        // Fetch images into IntArray called imageArray
        val typedArray = resources.obtainTypedArray(R.array.image_ids)
        val imageArray = IntArray(typedArray.length()) {typedArray.getResourceId(it, 0)}
        typedArray.recycle()

        mainViewModel.setImageIds(imageArray)

        // Attach an instance of ImageDisplayFragment using factory method


        /*supportFragmentManager
         .beginTransaction()
          .add(R.id.fragmentContainerView, newInstance(imageArray))
           .commit()*/

        if(supportFragmentManager.findFragmentById(R.id.fragmentContainerView) !is ImageDisplayFragment) {//checks the fragment on the top of the container
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, ImageDisplayFragment())
                .addToBackStack(null) //Remember this transaction happened so it can be reversed
                .commit()
        }
        /*else{
            supportFragmentManager.popBackStack()
        }*/

}
}