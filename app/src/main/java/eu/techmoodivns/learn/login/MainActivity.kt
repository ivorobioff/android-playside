package eu.techmoodivns.learn.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import eu.techmoodivns.learn.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val form = ViewModelProvider(this).get(FormModel::class.java).apply {
            addField(ProfileFields.FIRST_NAME)
            addField(ProfileFields.LAST_NAME)
        }

        binding.form = form

        binding.lifecycleOwner = this
    }
}